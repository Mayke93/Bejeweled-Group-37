package main.java.group37.bejeweled;

import main.java.group37.bejeweled.model.Logger;
import main.java.group37.bejeweled.model.SavedGame;
import main.java.group37.bejeweled.view.Main;
import main.java.group37.bejeweled.view.StartScreen;
import main.java.group37.bejeweled.view.StatusPanel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

@SuppressWarnings("serial")

/**
 * Class that is responsible for setting up the application.
 * @author group37
 */
public class Launcher extends JFrame {
  public StatusPanel statusPanel;
  public StartScreen startscreen;

  /**
   * Constructor of the application.
   * Starts the setting up of the GUI.
   */
  public Launcher() {
    initUi();
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(WindowEvent winEvt) {
        Logger.log("# Exit Game");
        Logger.close();
        SavedGame.getInstance().saveGame();
        System.exit(0);
      }
    });
  }

  /**
   * Setting up the GUI and initialize logger.
   */
  private void initUi() {
    setLayout(new BorderLayout());
    Logger.init();
    startscreen = new StartScreen(this);
    add(startscreen);

    setSize(800, 619);

    setTitle("Bejeweled");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
  }    

  /**
   * Start application.
   * @param args parameters passed to this application.
   */
  public static void main(String[] args) {

    EventQueue.invokeLater(new Runnable() {
      public void run() {
        Launcher ex = new Launcher();
        ex.setVisible(true);
      }
    });
  }

}