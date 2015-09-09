package nl.group37.bejeweled.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Application extends JFrame {
  public StatusPanel statusPanel;

  public Application() {
    initUi();
  }

  private void initUi() {
    setLayout(new BorderLayout());

    statusPanel = new StatusPanel();
    Board board = new Board(this,statusPanel);
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