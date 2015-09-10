package nl.group37.bejeweled.model;

import java.util.ArrayList;
import java.util.List;

public class Combination {
  public Tile.State type;
  public List<Tile> tiles;

  public Combination(Tile.State type, List<Tile> tiles) {
    this.type = type;
    this.tiles = tiles;
  }

  public Combination() {
    this.type = null;
    this.tiles = new ArrayList<Tile>();
  }

  public void setState(Tile.State state) {
    this.type = state;
  }

  public void setTiles(List<Tile> tiles) {
    this.tiles = tiles;
  }

  public Tile.State getState() {
    return type;
  }

  public List<Tile> getTiles() {
    return tiles;
  }
}
