package main.java.group37.bejeweled.combination;

import main.java.group37.bejeweled.board.Tile;

import java.util.List;

public class NormalCombination extends Combination {

  /**
   * Make a normal combination.
   * @param tiles the tiles the combination consists of.
   */
  public NormalCombination() {
    this.setType(Type.NORMAL);
  }
  
  /**
   * equals method for a combination
   * @return true iff the type and the tiles are the same.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof NormalCombination) {
      Combination that = (Combination) obj;
      if (that.getTiles().size() == this.getTiles().size() 
          && that.getTiles().containsAll(this.getTiles()) && that.getType() == this.getType()) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Get the score for making this type of combination.
   * @return the score
   */
  public int score() {
    return 50;
  }
  
}
