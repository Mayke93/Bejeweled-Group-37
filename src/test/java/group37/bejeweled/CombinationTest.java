package test.java.group37.bejeweled;


import org.junit.Test;

import main.java.group37.bejeweled.model.Combination;
import main.java.group37.bejeweled.model.Combination.Type;
import main.java.group37.bejeweled.Board.Tile;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Simple basic tests for class Combination. 
 * Not all methods tested yet, because random colors are assigned to Tiles.
 * @author Mayke Kloppenburg
 */
public class CombinationTest {

  /**
   * Method to make a combination so the tests are more compact.
   * @return a combination
   */
  public Combination combinationMaker() {
    ArrayList<Tile> tiles = new ArrayList<Tile>();
    Tile a1 = new Tile(1, 1);
    Tile b1 = new Tile(1, 2);
    Tile c1 = new Tile(1, 3);
    tiles.add(a1);
    tiles.add(b1);
    tiles.add(c1);
    Combination x1 = new Combination(Type.NORMAL, tiles);
    return x1;
  }

  /** 
   * Test for the constructor of Combination. 
   */
  @Test
  public void testCombination() {
    Combination x1 = new Combination();
    assertNotNull(x1);
  }

  /**
   * Test the getter and the setter for Type.
   */
  @Test
  public void getSetTypeTest() {
    Combination x1 = combinationMaker();
    assertEquals(Type.NORMAL, x1.getType());
    x1.setType(Type.FLAME);
    assertEquals(Type.FLAME, x1.getType());
  }


  /**
   * test setter and getter for tiles attribute.
   */
  @Test
  public void getSetTilesTest() {
    Combination x1 = combinationMaker();
    ArrayList<Tile> tiles = new ArrayList<Tile>();
    Tile a1 = new Tile(1, 1);
    tiles.add(a1);
    x1.setTiles(tiles);
    assertEquals(tiles, x1.getTiles());
    assertTrue(x1.getTiles().contains(a1));
  }

}
