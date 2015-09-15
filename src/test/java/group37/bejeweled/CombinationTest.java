package test.java.group37.bejeweled;


import org.junit.Test;

import main.java.group37.bejeweled.model.Combination;
import main.java.group37.bejeweled.model.Tile;

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
    Combination x1 = new Combination(Tile.State.NORMAL, tiles);
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
   * Test getState method.
   */
  @Test
  public void testGetState() {
    Combination x1 = combinationMaker();
    assertEquals(Tile.State.NORMAL, x1.getState());
  }

  /**
   * Test setState method.
   */
  @Test
  public void testSetState() {
    Combination x1 = combinationMaker();
    assertEquals(Tile.State.NORMAL, x1.getState());
    x1.setState(Tile.State.FLAME);
    assertEquals(Tile.State.FLAME, x1.getState());
  }

  //TODO test for getTiles method
  /*
   * This needs to be tested when Tile class has been changed. As if now, all
   * tiles are random colors.
   * 
   * @Test public void testGetTiles() { Combination x = combinationMaker();
   * ArrayList<Tile> tiles = new ArrayList<Tile>(); Tile a = new Tile(1, 1);
   * Tile b = new Tile(1, 2); Tile c = new Tile(1, 3); tiles.add(a);
   * tiles.add(b); tiles.add(c); assertEquals(tiles, x.getTiles());
   * 
   * }
   */

  /**
   * test setTiles method.
   */
  @Test
  public void testSetTiles() {
    Combination x1 = combinationMaker();
    ArrayList<Tile> tiles = new ArrayList<Tile>();
    Tile a1 = new Tile(1, 1);
    tiles.add(a1);
    x1.setTiles(tiles);
    assertEquals(tiles, x1.getTiles());
  }

}
