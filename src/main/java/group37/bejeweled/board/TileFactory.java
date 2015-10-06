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
  public static Tile generateTile(Type type, int xi, int yi) {
    if (type == Type.NORMAL) {
      return (Tile) new NormalTile(xi,yi);
    } else if (type == Type.HYPERCUBE) {
      return (Tile) new HypercubeTile(xi,yi);
    } else if (type == Type.STAR) {
      return (Tile) new StarTile(xi,yi);
    } else if (type == Type.FLAME) {
      return (Tile) new FlameTile(xi,yi);
    }
    return null;
  }
  
}
