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
import java.util.Iterator;


public class SavedGame {
  public static Game game;
  public static final int SIZE = 8;
  public Board board;

  public SavedGame(Game game) {
    this.game = game;
  }

  /**
   * The status of the game gets saved in JSON format.
   */
  public static void save() {
    Board board = game.getBoard();
    JSONObject obj = new JSONObject();
    obj.put("score", game.getScore());
    obj.put("level", game.getLevel());

    JSONArray list2 = new JSONArray();

    for (int q = 0; q < SIZE; q++) {
      JSONArray list = new JSONArray();
      for (int j = 0; j < SIZE; j++) {
        list.add(board.getTileAt(j, q).getIndex());
      }
      list2.add(list);
    }
    obj.put("board", list2);
    writeToFile(obj);
  }
  
  private static void writeToFile(JSONObject obj) {
    FileWriter file;
    try {
      file = new FileWriter("values.json");
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
  public static void jsonReader() {
    JSONParser parser = new JSONParser();
    Tile[][] bd = new Tile[SIZE][SIZE];
    try {
      Object obj = parser.parse(new FileReader("values.json"));
      JSONObject jsonObject = (JSONObject) obj;

      getScore(jsonObject);
      getLevel(jsonObject);
      getBoard(jsonObject,bd);

      game.getBoard().board = bd;
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }

  }
  
  private static void getBoard(JSONObject obj,Tile[][] bd) {
    JSONArray tiles = (JSONArray) obj.get("board");
    for (int row = 0; row < SIZE; row++) {
      JSONArray rowJ = (JSONArray) tiles.get(row);
      for (int col = 0; col < SIZE; col++) {
        bd[col][row] = new Tile(col,row);
        bd[col][row].setIndex(((Long)rowJ.get(col)).intValue());
      }
    }
  }
  
  private static int getScore(JSONObject obj) {
    Long score = (Long) obj.get("score");
    Integer score1 = new Integer(score.intValue());
    game.setScore(score1);
    System.out.println("Score: " + score);
    return score1;
  }
  
  private static int getLevel(JSONObject obj) {
    Long level = (Long) obj.get("level");
    Integer level1 = new Integer(level.intValue());
    game.setLevel(level1);
    System.out.println("Level: " + level);
    return level1;
  }
  
}