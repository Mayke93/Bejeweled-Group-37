package main.java.group37.bejeweled.combination;

import main.java.group37.bejeweled.board.Tile;
import main.java.group37.bejeweled.combination.Combination.Type;

import java.util.List;

public class FlameCombination extends Combination{

  /**
   * Make a flame combination.
   * @param tiles the tiles the combination consists of.
   */
  public FlameCombination() {
    this.setType(Type.FLAME);
  }
  
  /**
   * equals method for a combination
   * @return true iff the type and the tiles are the same.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof FlameCombination) {
      Combination that = (Combination) obj;
      if (that.getTiles().size() == this.getTiles().size() 
          && that.getTiles().containsAll(this.getTiles()) && that.getType() == this.getType()) {
        return true;
      }
    }
    return false;
  }
  
}
