package main.java.group37.bejeweled.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SavesList {
  
  private static List<SavedGame> savesList;
  
  /**
   * .
   */
  public static void makeSavesList() {
    savesList = new ArrayList<SavedGame>();
    
    File f0 = new File("SavedGames");
    ArrayList<File> files = new ArrayList<File>(Arrays.asList(f0.listFiles()));
    for (int i = 0; i < files.size(); i++) {
      
    }
    
  }
  
  public static void add(SavedGame sg) {
    savesList.add(sg);
  }
  
  public static int getSize() {
    return savesList.size();
  }

}
