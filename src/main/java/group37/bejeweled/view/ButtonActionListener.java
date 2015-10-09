package main.java.group37.bejeweled.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import main.java.group37.bejeweled.Launcher;
import main.java.group37.bejeweled.model.Logger;
import main.java.group37.bejeweled.model.SavedGame;
import main.java.group37.bejeweled.model.SavesList;

public class ButtonActionListener implements ActionListener{
  private StatusPanel panel;
  private StartScreen startscreen;
  private Launcher launcher;
  private final JFileChooser fc = new JFileChooser();
  private static String current = null;

  /**
   * constructor for buttonactionlistener.
   * @param panel statuspanel object
   * @param start startscreen object
   * @param launch launcher object
   */
  public ButtonActionListener(StatusPanel panel, StartScreen start, Launcher launch) {
    this.panel = panel;
    this.startscreen = start;
    this.launcher = launch;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == panel.saveGame) {
      Logger.log("Save Game clicked");
      
      String path = null;
      System.out.println("currr " + current);
      if (!(current == null)) {
        path = current;
      } else {
        path = getCurrentDate() + ".json";
      }
      
      SavedGame.getInstance().saveGame(path);
      
      Logger.log(path + " in SavedGames");  
    }
    if (event.getSource() == panel.button) {
      Logger.log("Quit Game clicked");
      
      launcher.getContentPane().remove(panel.main);
      launcher.getContentPane().add(startscreen);
    }
    if (event.getSource() == startscreen.newGame) {
      Logger.log("New Game clicked");
      
      launcher.getContentPane().remove(startscreen);

      panel = new StatusPanel(launcher, startscreen);
      Main main = new Main(launcher,panel);
      panel.setMain(main);
      main.setLayout(new BorderLayout());     
      main.add(panel,BorderLayout.WEST);

      launcher.getContentPane().add(main);
       
      panel.game.generateRandomBoard();
      panel.game.logic.level.setLevel(1);
      panel.game.logic.score.setScore(0);
      panel.setScore(0);
      panel.setLevel(1);
      panel.main.repaint();
      panel.repaint();
 
    }
    if (event.getSource() == startscreen.loadGame) {
   Logger.log("Load Game clicked");
      
      int result = fc.showOpenDialog(launcher);

      if (result == JFileChooser.APPROVE_OPTION) {  
       
        launcher.getContentPane().remove(startscreen);
        
        panel = new StatusPanel(launcher, startscreen);
        Main main = new Main(launcher,panel);
        panel.setMain(main);
        main.setLayout(new BorderLayout());     
        main.add(panel,BorderLayout.WEST);

        launcher.getContentPane().add(main);
        
        SavedGame.getInstance().loadGame(fc.getSelectedFile().getName());
        SavedGame.getInstance().setGame(panel.main.game);
             
        panel.main.repaint();
        panel.repaint();
       
      } else if (result == JFileChooser.CANCEL_OPTION) {
       Logger.log("Cancel was selected");
       
      }
    
    }
    
    launcher.getContentPane().validate();
    launcher.getContentPane().repaint();
  }
  
  /**
   * .
   * @return string with date and time.
   */
  public static String getCurrentDate() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date now = new Date();
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat time = new SimpleDateFormat("HH-mm-ss");
    String strDate = sdf.format(now) + " " + time.format(cal.getTime());
    return strDate;
  }
}
