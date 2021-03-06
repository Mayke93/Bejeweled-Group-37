package main.java.group37.bejeweled.combination;

public class HypercubeCombination extends Combination {

  /**
   * Make a normal combination.
   * @param tiles the tiles the combination consists of.
   */
  public HypercubeCombination() {
    this.setType(Type.HYPERCUBE);
  }
  
  /**
   * equals method for a combination
   * @return true iff the type and the tiles are the same.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof HypercubeCombination) {
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
    return 500;
  }
  
  /**
   * Set the next type for the first tile in this combination.
   */
  public void setNextType() {
    this.getTiles().get(0).setNextType(Type.HYPERCUBE);
  }
  
}
