package main.java.group37.bejeweled.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import main.java.group37.bejeweled.Board.Board;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SavedGame {
  public Game game;
  public static final int SIZE = 8;
  public Board board;

  public SavedGame(Game game) {
    this.game = game;
  }
  /**
   * The status of the game gets saved in JSON format.
   * @param game.
   */
  public static void save(Game game) {
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
    obj.put("board",list2);

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
   * 
   * @author Group 37
   *     Reads the tiles on our  board and prints, 
   *     an array with the index of each tile on the console.
   *
   */
  public static class JsonReader{
/**
 * the reader method.
 * @param args parameters passed to this method.
 */
    public static void main(String[] args) {
      JSONParser parser = new JSONParser();
      try {
        Object obj = parser.parse(new FileReader("values.json"));
        JSONObject jsonObject = (JSONObject) obj;
        Long score  = (Long) jsonObject.get("score");
        System.out.println("Score: " + score);
        Long level = (Long) jsonObject.get("Level");
        System.out.println("Level: " + level);
        JSONArray tiles = (JSONArray) jsonObject.get("board");
        Iterator<JSONArray> iter = tiles.iterator();
        while (iter.hasNext()) {
          JSONArray list3 = iter.next();
          Iterator<JSONArray> listiter = list3.iterator();
          System.out.println(list3);
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
  }
}
