package main.java.group37.bejeweled.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for making a combination of the type of an combi and the tiles which make this combination.
 * @author group37
 */
public class Combination {
  //public Tile.State type;
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
  public Combination(Type ty, List<Tile> tiles) {
    this.type = ty;
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
  

}
