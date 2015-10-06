package main.java.group37.bejeweled.combination;

import main.java.group37.bejeweled.board.FlameTile;
import main.java.group37.bejeweled.board.HypercubeTile;
import main.java.group37.bejeweled.board.StarTile;
import main.java.group37.bejeweled.board.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for making a combination of the type of an combi and the tiles which make this combination.
 * @author group37
 */
public abstract class Combination {
  
  public Type type;
  public List<Tile> tiles;
  
  /**
   * enum Type tells how many stones are in de combination.
   * @author group 37
   *
   */
  public enum Type {
    NORMAL, STAR, FLAME, HYPERCUBE;
  }

  /**
   * Constructor for the Combination.
   * @param type
   *              the type of the combination
   * @param tiles
   *              a list of tiles which are included in the combination
   */
  public Combination(List<Tile> tiles) {
    //this.type = ty;
    this.tiles = tiles;
  }

  /**
   * Empty constructor for Combination.
   */
  public Combination() {
    this.type = null;
    this.tiles = new ArrayList<Tile>();
  }

  /**
   * Change the type of the combination to.
   * @param type new type for the combination
    */
  public void setType(Type ty) {
    this.type = ty;
  }
  /**
   * Gets the state (type) of the combination.
   * @return type, the type of the combination.
   */
  public Type getType() {
    return type;
  }
 
  /**
   * Set the list of tiles for the combination to:
   * @param tiles, the list of tiles.
   */
  public void setTiles(List<Tile> tiles) {
    this.tiles = tiles;
  }

  /**
   * Gets the list of tiles that are included in the combination.
   * @return tiles, the list of tiles.
   */
  public List<Tile> getTiles() {
    return tiles;
  }
  
  /** Checks whether there is a special gem in a combination.
   * @return true iff there is a special gem in the combination.
   */
  public Tile containsSpecialGem() {
    for (Tile t1 : this.getTiles()) {
      if (t1 instanceof FlameTile || t1 instanceof HypercubeTile || t1 instanceof StarTile) {
        return t1;
      }
    }
    return null;
  }
  
  /**
   * Checks whether the combination should create a special gem.
   * @return true iff a special gem should be created.
   */
  public boolean isSpecialCombination() {
    boolean res = false;
    if (!(this.getType() == Type.NORMAL)) {
      res = true;
    }
    return res;
  }

  /**
   * Hashcode for a combination object.
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((tiles == null) ? 0 : tiles.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    return result;
  }
  
  /**
   * equals method for a combination
   * @return true iff the type and the tiles are the same.
   */
  public boolean equals(Object obj) {
    if (obj instanceof Combination) {
      Combination that = (Combination) obj;
      if (that.getTiles().size() == this.getTiles().size() 
          && that.getTiles().containsAll(this.getTiles()) && that.getType() == this.getType()) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Get the score for making a combination.
   * @return the score
   */
  public abstract int score();
  
  /**
   * Set the next type for the gem.
   */
  public abstract void setNextType();

}
