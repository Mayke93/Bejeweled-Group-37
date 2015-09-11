package nl.group37.bejeweled.model;

import java.awt.Image;
import java.awt.Point;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * Class for initialising a gem on the board.
 * @author Group 37
 *
 */
public class Tile {

  private Random random;
  private int index;
  private ImageIcon image;
  private Point loc;
  public Point translation;
  public int size;
  private int level;
  public boolean remove;
  public boolean delete;

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
    this.delete = false;
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
   * @param obj an objet to be compared
   * @return a boolean, true iff the two objects are the same.
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Tile)) {
      return false;
    }
    Tile tile = (Tile)obj;
    return (this.index == tile.index && tile.getX() == this.getX() && tile.getY() == this.getY());
  }
  
  /**
   * Returns true iff the compared with object is a Tile and has the same color.
   * @param obj an object to be compared
   * @return a boolean, true iff the two objects have the same color.
   */
  public boolean equalsColor(Object obj) {
    if (!(obj instanceof Tile)) {
      return false;
    }
    Tile tile = (Tile)obj;
    return (this.index == tile.index);
  }

  /**
   * overrides standard hashcode, because we have overwritten the equals method.
   * @return an integer hashcode
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((image == null) ? 0 : image.hashCode());
    result = prime * result + index;
    result = prime * result + level;
    result = prime * result + ((loc == null) ? 0 : loc.hashCode());
    result = prime * result + ((random == null) ? 0 : random.hashCode());
    result = prime * result + (remove ? 1231 : 1237);
    result = prime * result + size;
    result = prime * result + ((state == null) ? 0 : state.hashCode());
    result = prime * result
        + ((translation == null) ? 0 : translation.hashCode());
    return result;
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

  /**
   * get X value from the location of the tile.
   * @return integer value of X coordinate.
   */
  public int getX() { 
    return this.loc.x; 
  }
  
  /**
   * get Y value from the location of the tile.
   * @return integer value of Y coordinate.
   */
  public int getY() { 
    return this.loc.y; 
  }

  /**
   * gets the location of a tile.
   * @return a point that is the location
   */
  public Point getLoc() {
    return this.loc;
  }

  /**
   * sets the location of a tile.
   */
  public void setLoc(Point loc) {
    this.loc = loc;
  }

  /**
   * set the location of a tile, with coordinates.
   */
  public void setLoc(int coordinateX, int coordinateY) {
    this.loc = new Point(coordinateX,coordinateY);
  }

  /**
   * get how many levels the tile should drop.
   * @return an integer that represents the level
   */
  public int getLevel() {
    return level; 
  }

  /**
   * set how many levels the tile should drop.
   */
  public void setLevel(int level) {
    this.level = level; 
  }

  /**
   *  increases set how many levels the tile should drop.
   */
  public void increaseLevel() { 
    this.level++; 
  }

  /**
   * get the state of the tile.
   * @return the state as a State
   */
  public State getState() {
    return this.state; 
  }

  /**
   * sets the state of the tile.
   * @param state State of a tile
   */
  public void setState(State state) {
    this.state = state; 
  }

  /**
   * Method to representthe llcation and the color of a tile in a string.
   * @return a string witn the loaction and the color
   */
  public String toString() {
    return "(" + Integer.toString(this.loc.x) + "," 
           + Integer.toString(this.loc.y) + ") " + colors[this.index];
  }

  /**
   * gets the color of a tile.
   * @return the color of a tile in a string
   */
  public String getColor() {
    String color = colors[index];
    return color;
  }
  
  /**
   * get the traslation of a tile.
   * @return the translation of a tile as a Point
   */
  public Point getTranslation() {
    return translation;
  }
  
  
}