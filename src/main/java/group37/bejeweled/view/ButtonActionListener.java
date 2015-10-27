package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.Launcher;
import main.java.group37.bejeweled.board.Tile;
import main.java.group37.bejeweled.model.GameLogic;
import main.java.group37.bejeweled.model.Logger;
import main.java.group37.bejeweled.model.SavedGame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFileChooser;

public class ButtonActionListener implements ActionListener{
  private Panel panel;
  private final JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
  private static String current = null;

  /**
   * constructor for buttonactionlistener.
   * @param panel2 statuspanel object
   * @param start startscreen object
   * @param launch launcher object
   */
  public ButtonActionListener(Panel panel2) {
    this.panel = panel2;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == panel.saveGame) {
      Logger.log("Save Game clicked");
      handleSaveGame();
    }
    if (event.getSource() == panel.quit) {
      Logger.log("Quit Game clicked");
      handleQuitGame();
    }
    if (event.getSource() == Launcher.startscreen.newGame) {
      Logger.log("New Game clicked");
      handleNewGame();
    }
    if (event.getSource() == Launcher.startscreen.timeMode) {
      Logger.log("New Time Mode Game clicked");
      handleNewTimeGame();
    }
    if (event.getSource() == Launcher.startscreen.loadGame) {
      Logger.log("Load Game clicked");
      handleLoadGame();
    }
    if (event.getSource() == panel.hint) {
      Logger.log("Hint clicked");
      handleHint();
    }
    
    Launcher.launcher.getContentPane().validate();
    Launcher.launcher.getContentPane().repaint();
  }
  
  /**
   * This method handles the action taken when the hint button is clicked.
   */
  public void handleHint() {
    ArrayList<Tile> hint = GameLogic.getHint();
    if (!(hint == null)) {
      panel.main.setFocusHint(hint.get(0).getLoc(), hint.get(1).getLoc());
    }
  }
  
  /**
   * This method handles the action taken when the save game button is clicked.
   */
  public void handleSaveGame() {
    String path = null;
    System.out.println("currr " + current);
    
    if (!(current == null)) {
      path = current;
    } else {
      path = getCurrentDate() + ".json";
    }
    
    SavedGame.getInstance().saveGame(path);
    
    Logger.log("Saved in: " + path + " in SavedGames");  
  }
  
  /**
   * This method handles the action taken when the quit game button is clicked.
   */
  public void handleQuitGame() {
    if (panel instanceof StatusPanelTime) {
      ((StatusPanelTime) panel).getTimer().cancel();
    }
    Launcher.launcher.getContentPane().remove(panel.main);
    Launcher.launcher.getContentPane().add(Launcher.startscreen);
  }
  
  /**
   * handles the actions of the button new game.
   */
  public void handleNewGame() {
    current = null;
    
    Launcher.launcher.getContentPane().remove(Launcher.startscreen);

    panel = new StatusPanel();
    Main main = new Main(panel);
    panel.setMain(main);
    main.setLayout(new BorderLayout());     
    main.add(panel,BorderLayout.WEST);

    Launcher.launcher.getContentPane().add(main);

    panel.main.repaint();
    panel.repaint();
  }
  
  /**
   * handles the actions of the button new game.
   */
  public void handleNewTimeGame() {
    current = null;
    
    Launcher.launcher.getContentPane().remove(Launcher.startscreen);

    panel = new StatusPanelTime();
    Main main = new Main(panel);
    panel.setMain(main);
    main.setLayout(new BorderLayout());     
    main.add(panel,BorderLayout.WEST);

    Launcher.launcher.getContentPane().add(main);

    panel.main.repaint();
    panel.repaint();
  }
  
  /**
   * handles actiond for load game.
   */
  public void handleLoadGame() {
    
    int result = fc.showOpenDialog(Launcher.launcher);

    if (result == JFileChooser.APPROVE_OPTION) {  
     
      Launcher.launcher.getContentPane().remove(Launcher.startscreen);
      
      panel = new StatusPanel();
      Main main = new Main(panel);
      panel.setMain(main);
      main.setLayout(new BorderLayout());     
      main.add(panel,BorderLayout.WEST);

      Launcher.launcher.getContentPane().add(main);
      
      SavedGame.getInstance().loadGame(fc.getSelectedFile().getName());
      SavedGame.getInstance().setGame(panel.main.game);
      current = fc.getSelectedFile().getName();
           
      panel.main.repaint();
      panel.repaint();
     
    } else if (result == JFileChooser.CANCEL_OPTION) {
      Logger.log("Cancel was selected");
     
    }
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
