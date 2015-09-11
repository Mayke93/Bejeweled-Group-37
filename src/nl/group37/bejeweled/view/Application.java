package nl.group37.bejeweled.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

@SuppressWarnings("serial")

/**
 * Class that is responsible for setting up the application.
 * @author group37
 */
public class Application extends JFrame {
  public StatusPanel statusPanel;

  /**
   * Constructor of the application.
   * Starts the setting up of the GUI.
   */
  public Application() {
    initUi();
  }

  /**
   * Setting up the GUI.
   */
  private void initUi() {
    setLayout(new BorderLayout());

    statusPanel = new StatusPanel();
    Main board = new Main(this,statusPanel);
    board.setLayout(new BorderLayout());

    board.add(statusPanel,BorderLayout.WEST);
    add(board);

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
      //  @Override
      public void run() {
        Application ex = new Application();
        ex.setVisible(true);
      }
    });
  }
}