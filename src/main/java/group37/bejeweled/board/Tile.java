package main.java.group37.bejeweled.board;

import main.java.group37.bejeweled.combination.Combination.Type;
import main.java.group37.bejeweled.model.Game;
import main.java.group37.bejeweled.view.Main;

import java.awt.Point;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.tools.DocumentationTool.Location;

/**
 * Class for initialising a gem on the board.
 * @author Group 37
 */
public abstract class Tile implements IDrawable {
   
  public static final int SIZE = 8;
  protected Game game;
  protected ImageIcon image;
  public Point loc;
  public Point translation;
  public int size;
  private int level;
  public boolean remove;
  public boolean delete;
  //index is a number that point to the place in de paths and colors array
  protected int index;
  public String[] paths = {"src/img/gemBlue.png", "src/img/gemGreen.png",
                           "src/img/gemOrange.png", "src/img/gemPurple.png",
                           "src/img/gemRed.png", "src/img/gemWhite.png",
                           "src/img/gemYellow.png"};
  protected Type nextType;
  public static int spaceX = 65;
  public static int spaceY = 65;
  private Point focus = null;

  public static final String[] colors = {"Blue", "Green", "Orange", "Purple",
    "Red", "White", "Yellow"};


  /**
   * Initalize tile with location and a random color and set state to NORMAL.
   * @param transX location of Tile on the board.
   * @param transY location of Tile on the board.
   */
  public Tile(int transX, int transY) {
    //game = new Game(this);
    this.loc = new Point(transX,transY);
    this.translation = new Point(0,0);
    this.level = 0;
    this.remove = false;
    this.delete = false;
    this.size = 0;
    this.nextType = Type.NORMAL;
  }
 /*
   * Paints the tiles on the board
   * 
   */
  public void paintComponent(Graphics graphics, int positionX, int positionY) {
    if (this.remove) {
      return;
    }
    graphics.drawImage(this.getImage().getImage(),
             positionX + this.translation.x ,
             positionY + this.translation.y ,
             spaceX - this.size,
             spaceY - this.size,
             null); 
  }

  //logic for tiles used in other classes
  
  /**
   * update the translation of the position of the tile.
   * @param dx x translation.
   * @param dy y translation.
   */
  public void updateTranslation(int dx, int dy) {
    int sx = this.translation.x + dx;
    sx = saturateCoordinate(sx);
    int sy = this.translation.y + dy;
    sy = saturateCoordinate(sy);
    this.translation.setLocation(sx,sy);
  }
  
  private int saturateCoordinate(int coord) {
    if (coord == 64) {
      return 65;
    }
    if (coord == -64) {
      return -65;
    }
    return coord;
  }
  
  /**
   * Reset the translation.
   */
  public void resetD() {
    this.translation = new Point(0,0);
  }
  
  /**
   *  increases set how many levels the tile should drop.
   */
  public void increaseLevel() { 
    this.level++; 
  }
  
  //getters and setters for the attributes of tiles
  
  /**
   * gets the image of the tile.
   * @return the Image image.
   */
  public ImageIcon getImage() {
    return image;
  }
  
  /**
   * set the image of the tile.
   * @return the Image image.
   */
  public void setImage(ImageIcon im) {
    this.image = im;
  }
  
  /**
   * Return index of the tile.
   * @return the index
   */
  public int getIndex() {
    return this.index;
  }
  
  /**
   * set index of the tile.
   * @param xi the new index for the til
   */
  public void setIndex(int xi) {
    this.index = xi;
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
   * get the traslation of a tile.
   * @return the translation of a tile as a Point
   */
  public Point getTranslation() {
    return translation;
  }
  
  //equals methods and hascode
  
  /**
   * Returns true iff the compared with object is a Tile and has the same color.
   * @param obj an object to be compared
   * @return a boolean, true iff the two objects have the same color.
   */
  public boolean equalsColor(Object obj) {
    if (!(obj instanceof Tile) || obj == null) {
      return false;
    }
    Tile tile = (Tile)obj;
    return (this.index == tile.index);
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
    result = prime * result + (remove ? 1231 : 1237);
    result = prime * result + size;
    result = prime * result
        + ((translation == null) ? 0 : translation.hashCode());
    return result;
  }
  /**
   * 
   * @param graphics used to colour the tiles.
   */
  public void paintTile(Graphics graphics) {

    Board board = game.getBoard();
    ImageIcon boardImage = new ImageIcon();
    graphics.drawImage(boardImage.getImage(), 0, 0, this.getX(), this.getY(), null);
    int ix = this.getX();
    int iy = this.getY();
       Tile tile = null;
       
    for (int i = 0, x = ix, y = iy; i < SIZE; i++) {
      x = ix;
      for (int j = 0; j < SIZE; j++, x += spaceX) {
        IDrawable draw = board.getTileAt(j, i);
        draw.paintComponent(graphics, x, y);
      }
      y += spaceY;
    }
       
    if (focus != null) {
      ImageIcon focusImage = new ImageIcon("src/img/focus.png");
      graphics.drawImage(focusImage.getImage(), focus.x, focus.y,spaceX,spaceY, null);
    }
	   
   }
  
  /**
   * Create clone of this object.
   * @param coordinateX col index.
   * @param coordinateY row index.
   * @return clone of this.
   */
  public abstract Tile clone(int coordinateX, int coordinateY);

  //tostring methods, only for printing!!

  /**
   * Method to represent the location and the color of a tile in a string.
   * @return a string witn the loaction and the color
   */
  public String toString() {
    return "(" + Integer.toString(this.loc.x) + "," 
           + Integer.toString(this.loc.y) + ") " + colors[this.index] + "\n"
           + "\tDelete: " + this.delete + "\n"
           + "\tRemove: " + this.remove + "\n"
           + "\tNextType: " + this.nextType + "\n"
           + "\tType: " + getType();
  }

  public Type getNextType() {
    return nextType;
  }

  public void setNextType(Type nextType) {
    this.nextType = nextType;
  }
  
  public abstract String getType();

  public abstract int getScore();
}