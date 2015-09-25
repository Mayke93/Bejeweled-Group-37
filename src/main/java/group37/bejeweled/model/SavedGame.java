package main.java.group37.bejeweled.model;

import main.java.group37.bejeweled.board.Board;
import main.java.group37.bejeweled.board.Tile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;


public class SavedGame {
  public Game game;
  public static final int SIZE = 8;
  private static final String path = "values.json";
  public Board board;
  
  private static SavedGame sg = new SavedGame();

  private SavedGame() {

  }
  
  public static SavedGame getInstance() {
    return sg;
  }
  
  public void setGame(Game game) {
    this.game = game;
  }

  /**
   * The status of the game gets saved in JSON format.
   */
  @SuppressWarnings("unchecked")
  public void saveGame() {
    Board board = game.getBoard();
    JSONObject obj = new JSONObject();
    obj.put("score", game.getScore());
    obj.put("level", game.getLevel());

    JSONArray list2 = new JSONArray();

    for (int row = 0; row < SIZE; row++) {
      JSONArray list = new JSONArray();
      for (int col = 0; col < SIZE; col++) {
        list.add(board.getTileAt(col, row).getIndex());
      }
      list2.add(list);
    }
    obj.put("board", list2);
    writeToFile(obj);
  }
  
  private void writeToFile(JSONObject obj) {
    FileWriter file;
    try {
      file = new FileWriter(path);
      file.write(obj.toJSONString());
      file.flush();
      file.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * The reader method.
   */
  public void loadGame() {
    JSONObject obj = parseJsonFromFile();
    if (obj == null ) {
      return;
    }
    getScore(obj);
    getLevel(obj);

    Tile[][] newBoard = new Tile[SIZE][SIZE];
    if (getBoard(obj,newBoard)) {
      game.getBoard().board = newBoard;
    } else {
      game.generateRandomBoard();
    }
  }
  
  /**
   * Load JSON datd from file and parse the data into a JSON object.
   * @return object with parsed data from JSON file.
   */
  private JSONObject parseJsonFromFile() {
    JSONParser parser = new JSONParser();
    JSONObject obj = null;
    try {
      obj = (JSONObject) parser.parse(new FileReader(path));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return obj;
  }
  
  private boolean getBoard(JSONObject obj,Tile[][] bd) {
    JSONArray tiles = (JSONArray) obj.get("board");
    if (tiles == null) {
      return false;
    }
    int index = 0;
    for (int row = 0; row < SIZE; row++) {
      JSONArray rowJ = (JSONArray) tiles.get(row);
      for (int col = 0; col < SIZE; col++) {
        bd[col][row] = new Tile(col,row);
        index = ((Long)rowJ.get(col)).intValue();
        bd[col][row].setIndex(index);
        bd[col][row].setImage(new ImageIcon(bd[col][row].paths[index]));
      }
    }
    return true;
  }
  
  private int getScore(JSONObject obj) {
    Long score = (Long) obj.get("score");
    Integer score1 = new Integer(score.intValue());
    game.setScore(score1);
    Logger.log(" Read Score: " + score);
    return score1;
  }
  
  private int getLevel(JSONObject obj) {
    Long level = (Long) obj.get("level");
    Integer level1 = new Integer(level.intValue());
    game.setLevel(level1);
    Logger.log("Read Level: " + level);
    return level1;
  }
}