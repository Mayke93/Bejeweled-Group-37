package bejeweled37.test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import bejeweled37.Tile;

/**
 * Simple test class for Tile
 * as if 08-09-2015 not complete as tile class 
 * may be split or changed.
 * @author Mayke Kloppenburg
 */
public class TileTest {

  /**
   * Test getY and getX methods.
   */
  @Test
  public void testgetXandY() {
    Tile t1 = new Tile(1,1);
    assertEquals(t1.getX(), 1);
    assertEquals(t1.getY(), 1);
  }
  
  /**
   * Test for the updateTranslation method.
   */
  @Test
  public void testUpdateTranslation() {
    Tile t1 = new Tile(1,1);
    t1.updateTranslation(2, 2);
    Point p1 = new Point(2, 2);
    assertEquals(t1.d, p1);
  }

  /**
   * Test for the resetD method.
   */
  @Test
  public void testResetD() {
    Tile t1 = new Tile(1,1);
    t1.updateTranslation(2, 2);
    Point p1 = new Point(2, 2);
    assertEquals(t1.d, p1);
    Point q1 = new Point(0, 0);
    t1.resetD();
    assertEquals(t1.d, q1);

  }

  //TODO test randomtiles, getimage and equalscolor
  //TODO test for getColor
  
  /* 
   * @Test
  public void testsetRandomTile() {

  }
   */

  /*No setter (yet)?
   * @Test
  public void testGetImage() {
   fail("Not yet implemented");
  }*/

  /*This needs to be tested when Tile class has been changed.
   * As if now, all tiles are random colors.
   * 
   * @Test
  public void testEqualsColor() {
    fail("Not yet implemented");
  }
   */

  /**
   * Test for the getLoc method.
   */
  @Test
  public void testGetLoc() {
    Tile t1 = new Tile(1,1);
    Point q1 = new Point(1, 1);
    assertEquals(t1.getLoc(), q1);
  }

  /**
   * Test for the setLoc method.
   */
  @Test
  public void testSetLoc() {
    Tile t1 = new Tile(1,1);
    Point q1 = new Point(1, 1);
    assertEquals(t1.getLoc(), q1);
    Point p1 = new Point(2, 2);
    t1.setLoc(p1);
    assertEquals(t1.getLoc(), p1);
  }

  /**
   * Test for the getLevel method.
   */
  @Test
  public void testGetLevel() {
    Tile t1 = new Tile(1,1);
    assertEquals(t1.getLevel(),0);
  }

  /**
   * Test for the setLevel method.
   */
  @Test
  public void testSetLevel() {
    Tile t1 = new Tile(1,1);
    assertEquals(t1.getLevel(),0);
    t1.setLevel(2);
    assertEquals(t1.getLevel(), 2);
  }

  /**
   * Test for the increaseLevel method.
   */
  @Test
  public void testIncreaseLevel() {
    Tile t1 = new Tile(1,1);
    assertEquals(t1.getLevel(),0);
    t1.increaseLevel();
    assertEquals(t1.getLevel(), 1);
  }

  /**
   * Test for the getState method.
   */
  @Test
  public void testGetState() {
    Tile t1 = new Tile(1,1);
    assertEquals(t1.getState(), Tile.State.NORMAL);
  }


  /**
   * Test for the setSate method.
   */
  @Test
  public void testSetState() {
    Tile t1 = new Tile(1,1);
    assertEquals(t1.getState(), Tile.State.NORMAL);
    t1.setState(Tile.State.FLAME);
    assertEquals(t1.getState(), Tile.State.FLAME);
  }

  /**
   * Test for the testToString method.
   */
  @Test
  public void testToString() {
    Tile t1 = new Tile(1,1);
    assertEquals(t1.toString(), "(1,1) " + t1.getColor());
  }

}
