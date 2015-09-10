package nl.group37.bejeweled.model;

import java.awt.Image;
import java.awt.Point;
import java.util.Random;

import javax.swing.ImageIcon;

public class Tile {

  private Random random;
  private int index;
  private ImageIcon image;
  private Point loc;
  public Point translation;
  public int size;
  private int level;
  public boolean remove;

  public static enum State{
    DEFAULT,NORMAL,FLAME,HYPERCUBE,STAR;
  }

  private State state;

  private static final String[] paths = {"src/img/gemBlue.png", "src/img/gemGreen.png",
    "src/img/gemOrange.png", "src/img/gemPurple.png",
    "src/img/gemRed.png", "src/img/gemWhite.png",
    "src/img/gemYellow.png"};
  public static final String[] colors = {"Blue", "Green", "Orange", "Purple",
    "Red", "White", "Yellow"};


  /**
   * Initalize tile with location and a random color and set state to NORMAL.
   * @param transX location of Tile on the board.
   * @param transY location of Tile on the board.
   */
  public Tile(int transX, int transY) {
    setRandomTile();
    this.state = State.NORMAL;
    this.loc = new Point(transX,transY);
    this.translation = new Point(0,0);
    this.level = 0;
    this.remove = false;
    this.size = 0;
  }

  /**
   * update the translation of the position of the tile.
   * @param dx x translation.
   * @param dy y translation.
   */
  public void updateTranslation(int dx, int dy) {
    int sx = this.translation.x + dx;
    if (sx == 64) {
      sx = 65;
    }
    if (sx == -64) {
      sx = -65;
    }

    int sy = this.translation.y + dy;
    if (sy == 64) {
      sy = 65;
    }
    if (sy == -64) {
      sy = -65;
    }
    this.translation.setLocation(sx,sy);
  }
  
  /**
   * Reset the translation.
   */
  public void resetD() {
    this.translation = new Point(0,0);
  }

  /**
   * Give tile random color.
   */
  public void setRandomTile() { 
    this.random = new Random();
    this.state = State.NORMAL;
    this.index = random.nextInt(paths.length);
    this.image = new ImageIcon(paths[index]);
  }

  /**
   * Return image of the tile.
   * @return the Image image.
   */
  public Image getImage() {
    return image.getImage();
  }

  /**
   * Return index of the tile.
   * @return the index
   */
  public int getIndex() {
    return this.index;
  }

  /**
   * Returns true iff the compared with object is a tile
   * and has the same index, X-co and Y-co.
   */
  public boolean equals(Object obj) {
    if (!(obj instanceof Tile)) {
      return false;
    }
    Tile tile = (Tile)obj;
    return (this.index == tile.index && tile.getX() == this.getX() && tile.getY() == this.getY());
  }

  /**
   * Returns true iff the compared with object is a Tile and has the same color.
   */
  public boolean equalsColor(Object obj) {
    if (!(obj instanceof Tile)) {
      return false;
    }
    Tile tile = (Tile)obj;
    return (this.index == tile.index);
  }

  /**
   * Create clone of this object.
   * @param coordinateX col index.
   * @param coordinateY row index.
   * @return clone of this.
   */
  public Tile clone(int coordinateX, int coordinateY) {
    Tile tile = new Tile(coordinateX,coordinateY);
    tile.state = State.NORMAL;
    tile.index = this.index;
    tile.image = new ImageIcon(paths[index]);
    return tile;
  }

  public int getX() { 
    return this.loc.x; 
  }

  public int getY() { 
    return this.loc.y; 
  }

  public Point getLoc() {
    return this.loc;
  }

  public void setLoc(Point loc) {
    this.loc = loc;
  }

  public void setLoc(int coordinateX, int coordinateY) {
    this.loc = new Point(coordinateX,coordinateY);
  }

  public int getLevel() {
    return level; 
  }

  public void setLevel(int level) {
    this.level = level; 
  }

  public void increaseLevel() { 
    this.level++; 
  }

  public State getState() {
    return this.state; 
  }

  public void setState(State state) {
    this.state = state; 
  }

  public String toString() {
    return "(" + Integer.toString(this.loc.x) + "," 
           + Integer.toString(this.loc.y) + ") " + colors[this.index];
  }

  public String getColor() {
    String color = colors[index];
    return color;
  }
  
  public Point getTranslation() {
    return translation;
  }
}