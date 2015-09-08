package bejeweled37;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Application extends JFrame {
  public StatusPanel sPanel;

  public Application() {
    initUI();
  }

  private void initUI() {
    setLayout(new BorderLayout());

    sPanel = new StatusPanel();
    Board board = new Board(this,sPanel);
    board.setLayout(new BorderLayout());

    board.add(sPanel,BorderLayout.WEST);
    add(board);

    setSize(800, 619);

    setTitle("Bejeweled");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
  }    

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