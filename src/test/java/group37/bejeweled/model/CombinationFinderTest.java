package test.java.group37.bejeweled.model;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.group37.bejeweled.Board.Board;
import main.java.group37.bejeweled.Board.Tile;
import main.java.group37.bejeweled.model.CombinationFinder;

/**
 * simple input/output tests for the CombinationFinder class
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
  public void setGetBoardTest(){
    Tile[][] tiles = new Tile[4][4];
    Board board = new Board(tiles);
    CombinationFinder cf = new  CombinationFinder(board);
    assertEquals(cf.getBoard(), board);
    
    Tile[][] newTiles = new Tile[5][4];
    Board newBoard = new Board(newTiles);
    cf.setBoard(newBoard);
    assertEquals(cf.getBoard(), newBoard);
  }
  
  

}
