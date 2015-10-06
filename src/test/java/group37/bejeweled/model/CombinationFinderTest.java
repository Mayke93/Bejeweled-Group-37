package test.java.group37.bejeweled.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import main.java.group37.bejeweled.board.Board;
import main.java.group37.bejeweled.board.NormalTile;
import main.java.group37.bejeweled.board.Tile;
import main.java.group37.bejeweled.combination.Combination;
import main.java.group37.bejeweled.combination.CombinationFinder;
import main.java.group37.bejeweled.combination.Combination.Type;
import main.java.group37.bejeweled.combination.NormalCombination;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * simple input/output tests for the CombinationFinder class.
 * @author group 37
 *
 */
public class CombinationFinderTest {

  /**
   * test for the constructor.
   */
  @Test
  public void combinationFinderConstrtest() {
    Tile[][] tiles = new Tile[4][4];
    Board board = new Board(tiles);
    CombinationFinder cf = new  CombinationFinder(board);
    assertNotNull(cf);
  }  

  /**
   * test for setboard and getboard method.
   */
  @Test
  public void setGetBoardTest() {
    Tile[][] tiles = new Tile[4][4];
    Board board = new Board(tiles);
    CombinationFinder cf = new  CombinationFinder(board);
    assertEquals(cf.getBoard(), board);
    
    Tile[][] newTiles = new Tile[5][4];
    Board newBoard = new Board(newTiles);
    cf.setBoard(newBoard);
    assertEquals(cf.getBoard(), newBoard);
  }
  
  /**
   * Method to make a combination so the tests are more compact.
   * @return a combination
   */
  public Combination combinationMaker() {
    Tile t0 = new NormalTile(0,0);
    t0.setIndex(2);
    Tile t1 = new NormalTile(1,0);
    t1.setIndex(2);
    Tile t2 = new NormalTile(2,0);
    t2.setIndex(2);
    ArrayList<Tile> tiles = new ArrayList<Tile>();
    tiles.add(t0);
    tiles.add(t1);
    tiles.add(t2);
    Combination x1 = new NormalCombination();
    x1.setTiles(tiles);
    return x1;
  }
  
  /**
   * test for getAllCombinationsOnBoard method.
   */
  @Test
  public void getAllCombinationsOnBoardTest() {
    Tile t0 = new NormalTile(0,0);
    t0.setIndex(2);
    Tile t1 = new NormalTile(1,0);
    t1.setIndex(2);
    Tile t2 = new NormalTile(2,0);
    t2.setIndex(2);
    Tile t3 = new NormalTile(0,1);
    t3.setIndex(1);
    Tile[][] tiles = new Tile[3][2];
    Board board = new Board(tiles);
    board.setTileAt(t0, 0, 0);
    board.setTileAt(t1, 1, 0);
    board.setTileAt(t2, 2, 0);
    board.setTileAt(t3, 0, 1);
    board.setTileAt(t2, 1, 1);
    board.setTileAt(t3, 2, 1);
    Combination x1 = combinationMaker();
    List<Combination> list = new ArrayList<Combination>();
    list.add(x1);
    CombinationFinder cf = new CombinationFinder(board);
    assertTrue(list.containsAll(cf.getAllCombinationsOnBoard()));
    assertTrue(cf.getAllCombinationsOnBoard().size() == list.size());
  }
  
}
