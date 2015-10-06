package main.java.group37.bejeweled.board;

import main.java.group37.bejeweled.model.Combination.Type;

public class TileFactory {
  
  public TileFactory() {
   
  }
  
  /**
   * Generate a tile of the correct type.
   * @param type the type of the tile
   * @return the correct tile
   */
  public static Tile generateTile(Type type) {
    if (type == Type.NORMAL) {
      return (Tile) new NormalTile(0,0);
    } else if (type == Type.HYPERCUBE) {
      return (Tile) new HypercubeTile(0,0);
    } else if (type == Type.STAR) {
      return (Tile) new StarTile(0,0);
    } else if (type == Type.FLAME) {
      return (Tile) new FlameTile(0,0);
    }
    return null;
  }
  
}
