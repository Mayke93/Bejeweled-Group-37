package test.java.group37.bejeweled.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import main.java.group37.bejeweled.board.Board;
import main.java.group37.bejeweled.board.HypercubeTile;
import main.java.group37.bejeweled.board.Tile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple basic test for the board class.
 * @author Group 37
 *
 */
public class BoardTest {

  /**
   * Test for the constructor of board.
   */
  @Test
  public void boardContrtest() {
    Tile[][] tiles = new Tile[8][8];
    Board board = new Board(tiles);
    assertNotNull(board);
  }
  

  /**
   * Test for the getters of width and height.
   */
  @Test
  public void getWidthHeightTest() {
    Tile[][] tiles = new Tile[8][6];
    Board board = new Board(tiles);
    assertEquals(board.getWidth(), 8);
    assertEquals(board.getHeight(), 6);
  }
  

  /**
   * Test for the getter and setter of a tile on the board.
   */
  @Test
  public void getSetTileAtTest() {
    Tile[][] tiles = new Tile[8][6];
    Board board = new Board(tiles);
    Tile tile = new Tile(3,2);
    board.setTileAt(tile, 3, 2);
    assertEquals(board.getTileAt(3,2), tile);
  }


  /**
   * Test true for the method validborders.
   */
  @Test
  public void validBordersTrueTest() {
    Tile[][] tiles = new Tile[8][6];
    Board board = new Board(tiles);
    assertTrue(board.validBorders(1,3));
  }
  
  /**
   * Test false for the method validborders.
   */
  @Test
  public void validBordersFalseTest() {
    Tile[][] tiles = new Tile[8][6];
    Board board = new Board(tiles);
    assertFalse(board.validBorders(8,6));
  }
  
  /**
   * Test false with invalid borders and valid borders for the method isEmpty.
   */
  @Test
  public void isEmptyFalseUnvalidTest() {
    Tile[][] tiles = new Tile[8][6];
    Board board = new Board(tiles);
    Tile tile = new Tile(3,2);
    board.setTileAt(tile, 3, 2);
    assertFalse(board.isEmpty(8, 6));
    assertFalse(board.isEmpty(3, 2));   
  }
  
  /**
   * Test true with invalid borders and valid borders for the method isEmpty.
   */
  @Test
  public void isEmptyTrueTest() {
    Tile[][] tiles = new Tile[8][6];
    Board board = new Board(tiles);
    Tile tile = new Tile(3,2);
    board.setTileAt(tile, 3, 2);
    assertTrue(board.isEmpty(3, 3));   
  }
  
  /**
   * Test clear method.
   */
  @Test
  public void clearTest() {
    Tile[][] tiles = new Tile[8][6];
    Board board = new Board(tiles);
    Tile tile = new Tile(3,2);
    board.setTileAt(tile, 3, 2);
    assertEquals(board.getTileAt(3, 2), tile); 
    board.clear(3, 2);
    assertNull(board.getTileAt(3, 2));
  }
  
  
  /**
   * Test getTilesToDeleteFlame method.
   */
  @Test
 // @SuppressWarnings("OneStatementPerLine")
  public void getTilesToDeleteFlameTest() {
    Tile[][] tiles = new Tile[3][3];
    Board board = new Board(tiles);
    Tile tile = new Tile(1,1);
    board.setTileAt(tile, 0, 0); board.setTileAt(tile, 0, 1); board.setTileAt(tile, 0, 2);
    board.setTileAt(tile, 1, 0); board.setTileAt(tile, 1, 1); board.setTileAt(tile, 1, 2);
    board.setTileAt(tile, 2, 0); board.setTileAt(tile, 2, 1); board.setTileAt(tile, 2, 2);
   
    List<Tile> newList = new ArrayList<Tile>();
    newList.add(tile); newList.add(tile); newList.add(tile);
    newList.add(tile); newList.add(tile); newList.add(tile);
    List<Tile> list = board.getTilesToDeleteFlame(tile);
    assertFalse(list.equals(newList));
    newList.add(tile); newList.add(tile); newList.add(tile);
    assertTrue(list.equals(newList));
  }
  /**
   * Test getTilesToDeleteFlame method, with the flame tile in a corner.
   */
  @Test
  public void getTilesToDeleteInCornerFlameTest() {
    Tile[][] tiles = new Tile[3][3];
    Board board = new Board(tiles);
    Tile tile = new Tile(0,0);
    board.setTileAt(tile, 0, 0); board.setTileAt(tile, 0, 1);
    board.setTileAt(tile, 1, 0); board.setTileAt(tile, 1, 1);
   
    List<Tile> newList = new ArrayList<Tile>();
    newList.add(tile); 
    newList.add(tile); 
    List<Tile> list = board.getTilesToDeleteFlame(tile);
    assertFalse(list.equals(newList));
    newList.add(tile); newList.add(tile);
    assertTrue(list.equals(newList));
  }
  
  /**
   * Test getTilesToDeleteHypercube method.
   */
  @Test
  public void getTilesToDeleteHypercubeTest() {
    Tile[][] tiles = new Tile[3][3];
    Board board = new Board(tiles);
    Tile htile = new HypercubeTile(1,1);
    Tile tile = new Tile(1,1);
    tile.setIndex(1);
    board.setTileAt(htile, 0, 0); board.setTileAt(tile, 0, 1); board.setTileAt(tile, 0, 2);
    board.setTileAt(tile, 1, 0); board.setTileAt(tile, 1, 1); board.setTileAt(tile, 1, 2);
    board.setTileAt(tile, 2, 0); board.setTileAt(tile, 2, 1); board.setTileAt(tile, 2, 2);
   
    List<Tile> newList = new ArrayList<Tile>();
    newList.add(htile); newList.add(tile); newList.add(tile);
    newList.add(tile); newList.add(tile); newList.add(tile);
    List<Tile> list = board.getTilesToDeleteHypercube(tile, htile);
    assertFalse(list.equals(newList));
    newList.add(tile); newList.add(tile); newList.add(tile);
    assertTrue(list.equals(newList));
  }
  
  /**
   * Test getTilesToDeleteStar method.
   */
  @Test
  public void getTilesToDeleteStarTest() {
    Tile[][] tiles = new Tile[2][2];
    Board board = new Board(tiles);
    Tile tile = new Tile(1,1);
    Tile tile2 = new Tile(0,1);
    Tile tile3 = new Tile(0,0);
    Tile tile4 = new Tile(1,0);
    board.setTileAt(tile3, 0, 0); 
    board.setTileAt(tile2, 0, 1);
    board.setTileAt(tile4, 1, 0); 
    board.setTileAt(tile, 1, 1); 
   
    List<Tile> newList = new ArrayList<Tile>();
    List<Tile> list = board.getTilesToDeleteStar(tile);
    assertFalse(list.equals(newList));
    newList.add(tile); 
    newList.add(tile2); 
    newList.add(tile4);
    assertTrue(list.equals(newList));
  }
  
  /**
   * test for the equals method, with a board that only contains null tiles.
   * one for true and false.
   */
  @Test
  public void equalsOnlyNullTilesTest() {
    Tile[][] t0 = new Tile[2][2];
    Board b0 = new Board(t0);
    Tile[][] t1 = new Tile[2][2];
    Board b1 = new Board(t1);
    assertTrue(b0.equals(b1));
    Tile[][] t2 = new Tile[2][1];
    Board b2 = new Board(t2);
    assertFalse(b1.equals(b2));
  }
  
  
  /**
   * test for the equals method, with a board that contains tiles that are not null.
   * one for true and false. The tiles are the same.
   */
  @Test
  public void equalsWithTilesTest() {
    Tile[][] t0 = new Tile[2][2];
    Tile x0 = new Tile(1,1);
    Board b0 = new Board(t0);
    b0.setTileAt(x0, 1, 1);
    
    Tile[][] t1 = new Tile[2][2];
    Tile x1 = new Tile(1,1);
    Board b1 = new Board(t1);
    b1.setTileAt(x1, 1, 1);
    assertTrue(b0.equals(b1));
    
    Tile[][] t2 = new Tile[2][2];
    Board b2 = new Board(t2);
    b2.setTileAt(x0, 0, 0);
    assertFalse(b1.equals(b2));
    assertFalse(b2.equals(b1));
  }
  
  /**
   * test for the equals method, with a board that contains tiles that are not null.
   * one for true and false. The tiles are not equal
   */
  @Test
  public void equalsWithDifferentTilesTest() {
    Tile[][] t0 = new Tile[2][2];
    Tile x0 = new Tile(1,1);
    Board b0 = new Board(t0);
    b0.setTileAt(x0, 1, 1);
    
    Tile[][] t1 = new Tile[2][2];
    Tile x1 = new Tile(2,1);
    Board b1 = new Board(t1);
    b1.setTileAt(x1, 1, 1);
    assertFalse(b0.equals(b1));
    
  }
  
  /**
   * Test for equals method, with an object that is not a board.
   */
  @Test
  public void equalsWithInvalidObjectTest() {
    Tile[][] t0 = new Tile[2][2];
    Board b0 = new Board(t0);
    Object obj = new Object();
    assertFalse(b0.equals(obj));
  }
  
  /**
   * test for the hashcode of board.
   */
  @Test
  public void hashCodeTest() {
    Tile[][] t0 = new Tile[1][1];
    Board b0 = new Board(t0);
    Board b1 = new Board(t0);
    assertTrue(b0.hashCode() == b1.hashCode());
  }
  
}
