package main.java.group37.bejeweled.model;

import java.io.File;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;  
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SavedGame {
  public Game game;
  
//  public static void main(String[] args){
//    Game g = new Game(null, null);
//    g.setScore(500);
//    save(g);
//  }
  
  //{"score":0,"level":1, "board": [[1,2,3,4,5,6,7,8],[1,2,3],]}
  
  public SavedGame(Game game) {
    this.game = game;
  }
  
  public static void save(Game game){

    JSONObject obj = new JSONObject();
    obj.put("score", game.getScore());
    obj.put("level", game.getLevel());

    FileWriter file;
    try {
      file = new FileWriter("/Users/Kiran/Desktop/values.json");
      file.write(obj.toJSONString());
      file.flush();
      file.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public File load(){
    
//    Object obj = parser.parse(new FileReader("c:\\test.json"));
    
    JSONParser parser = new JSONParser();
    
    return null;
  }

}
