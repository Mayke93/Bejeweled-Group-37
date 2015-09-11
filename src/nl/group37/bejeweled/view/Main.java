package nl.group37.bejeweled.view;

import nl.group37.bejeweled.model.Game;
import nl.group37.bejeweled.model.Tile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


@SuppressWarnings("serial")
public class Main extends JPanel {

  public static final int SIZE = 8; //Board size is 8x8
  public static final Point LOCATION = new Point(241,40);
  public static final int SPACE_X = 65;
  public static final int SPACE_Y = 65;
  private static ImageIcon boardImage  = new ImageIcon("src/img/board.png");
  private static ImageIcon focusImage = new ImageIcon("src/img/focus.png");
  private Point focus = null;

  private final JFrame frame;
  public Animation animations;

  //Panel with score label and level label
  private StatusPanel panel;

  private Game game;

  /**
   * Initialize the board and create the mouse event listeners.
   * @param frame JFrame of the game
   * @param panel JPanel with the labels to display the status of the game
   */
  public Main(final JFrame frame,StatusPanel panel) {
    this.frame = frame;
    this.panel = panel;
    game = new Game(this,panel);
    animations = new Animation(game,this);
    setOpaque(true);

    this.addMouseListener(new MouseListener(this));
    this.addMouseMotionListener(new MouseMotionListener(this));
  }

  public Game getGame() {
    return game;
  }

  public void swapTiles(List<Tile> swapTiles) {
    animations.setType(Animation.Type.SWAP);
    animations.swap(swapTiles.get(0), swapTiles.get(1));
  }

  /**
   * Check if index x is within the boundaries of the board.
   * @param x index to check
   * @return true iff x is inside the boundaries.
   */
  public static boolean withinBoundaries(int ix) {
    return (ix >= 0 && ix < SIZE);
  }

  /**
   * Get the index of the focused jewel based on the coordinated of the mouse event.
   * @param loc location of the mouse event
   */
  public void setFocus(Point loc) {
    int ix = loc.x * SPACE_X + LOCATION.x;
    int iy = loc.y * SPACE_Y + LOCATION.y;
    focus = new Point(ix,iy);
    repaint();
  }

  /**
   * Get the col and row index based on the coordinates on the screen.
   * @param ix x-coordinate of the mouse event
   * @param iy y-coordinate of the mouse event
   * @return point calculated based on the x,y coordinates from x and y.
   */
  public static Point getColAndRow(int ix,int iy) {
    int col = (ix - LOCATION.x) / SPACE_X;
    int row = (iy - LOCATION.y) / SPACE_Y;
    return new Point(col,row);
  }
   
  /**
   * This shows the text that will end the game.
   * 
   */
  public void endGame() {
    this.frame.setLayout(new BorderLayout());
    JPanel pl = new JPanel(new GridLayout(4,1));
    pl.setBackground(Color.BLACK);

    JButton button = new JButton();
    //Border thickBorder = new LineBorder(Color.BLACK, 12);
    button.setText("New Game");
    button.setSize(400, 50);
    button.setMinimumSize(new Dimension(400,200));
    button.setFont(new Font("Serif",Font.BOLD, 45)); 
    button.setForeground(Color.BLACK);
    button.addActionListener(new ButtonListener());

    JLabel label = new JLabel("Game over frown-emoticon",JLabel.CENTER);
    label.setForeground(Color.WHITE);
    label.setVerticalTextPosition(JLabel.TOP);
    label.setHorizontalTextPosition(JLabel.CENTER);
    label.setFont(new Font("Serif",Font.BOLD, 45)); 

    pl.setAlignmentX(Component.CENTER_ALIGNMENT);
    pl.add(label);
    pl.add(button);
    this.frame.setContentPane(pl);
    this.frame.setVisible(true);
  }

  /**
   * Draw board on the screen.
   */
  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);

    Tile[][] board = game.getBoard();
    graphics.drawImage(boardImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    int ix = LOCATION.x;
    int iy = LOCATION.y;
    Tile tile = null;

    for (int i = 0,x = ix, y = iy; i < SIZE; i++) {
      x = ix;
      for (int j = 0; j < SIZE; j++, x += SPACE_X) {
        tile = board[j][i];
        if (tile.remove) {
          continue;
        }
        graphics.drawImage(tile.getImage(), x + tile.translation.x , 
            y + tile.translation.y,SPACE_X - tile.size,SPACE_Y - tile.size, null);
      }
      y += SPACE_Y;
    }

    if (focus != null) {
      graphics.drawImage(focusImage.getImage(), focus.x, focus.y,SPACE_X,SPACE_Y, null);
    }
  }
}