package nl.group37.bejeweled.main.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for making a combination of the type of an combi and the tiles which make this combination.
 * @author group37
 */
public class Combination {
  public Tile.State type;
  public List<Tile> tiles;

  /**
   * Constructor for the Combination.
   * @param type
   *              the type of the combination
   * @param tiles
   *              a list of tiles which are included in the combination
   */
  public Combination(Tile.State type, List<Tile> tiles) {
    this.type = type;
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
   * Change the state of the combination to:
   * @param state.
   */
  public void setState(Tile.State state) {
    this.type = state;
  }

  /**
   * Set the list of tiles for the combination to:
   * @param tiles, the list of tiles.
   */
  public void setTiles(List<Tile> tiles) {
    this.tiles = tiles;
  }

  /**
   * Gets the state (type) of the combination.
   * @return type, the type of the combination.
   */
  public Tile.State getState() {
    return type;
  }

  /**
   * Gets the list of tiles that are included in the combination.
   * @return tiles, the list of tiles.
   */
  public List<Tile> getTiles() {
    return tiles;
  }
}
