package main.java.group37.bejeweled.combination;

import main.java.group37.bejeweled.combination.Combination.Type;

public class CombinationFactory {

  public CombinationFactory() {}
  
  /**
   * Decide which type of combination is made.
   * @param type the type of the combi to be  made
   * @return the new combination
   */
  public static Combination makeCombination(Type type) {
    if (type == Type.NORMAL) {
      return new NormalCombination();
    } else if (type == Type.FLAME) {
      return new FlameCombination();
    } else if (type == Type.STAR) {
      return new StarCombination();
    } else if (type == Type.HYPERCUBE) {
      return new HypercubeCombination();
    } 
    return null;
  }
  
}
