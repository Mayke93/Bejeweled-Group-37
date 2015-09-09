/**
 * 
 */
package bejeweled37.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import bejeweled37.Tile;

/**
 * @author Mayke
 * Simple test class for Tile
 * as if 08-09-2015 not complete as tile class 
 * may be split or changed.
 */
public class TileTest {

  @Test
  public void testgetXY() {
    Tile t = new Tile(1,1);
    assertEquals(t.getX(), 1);
    assertEquals(t.getY(), 1);
  }

  @Test
  public void testUpdateTranslation() {
    Tile t = new Tile(1,1);
    t.updateTranslation(2, 2);
    Point p = new Point(2, 2);
    assertEquals(t.translation, p);
  }

  @Test
  public void testResetD() {
    Tile t = new Tile(1,1);
    t.updateTranslation(2, 2);
    Point p = new Point(2, 2);
    assertEquals(t.translation, p);
    Point q = new Point(0, 0);
    t.resetD();
    assertEquals(t.translation, q);

  }

  /*Not possible? Maybe later
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

  @Test
  public void testGetLoc() {
    Tile t = new Tile(1,1);
    Point q = new Point(1, 1);
    assertEquals(t.getLoc(), q);
  }

  @Test
  public void testSetLoc() {
    Tile t = new Tile(1,1);
    Point q = new Point(1, 1);
    assertEquals(t.getLoc(), q);
    Point p = new Point(2, 2);
    t.setLoc(p);
    assertEquals(t.getLoc(), p);
  }

  @Test
  public void testGetLevel() {
    Tile t = new Tile(1,1);
    assertEquals(t.getLevel(),0);		
  }

  @Test
  public void testSetLevel() {
    Tile t = new Tile(1,1);
    assertEquals(t.getLevel(),0);
    t.setLevel(2);
    assertEquals(t.getLevel(), 2);
  }

  @Test
  public void testIncreaseLevel() {
    Tile t = new Tile(1,1);
    assertEquals(t.getLevel(),0);
    t.increaseLevel();
    assertEquals(t.getLevel(), 1);
  }

  @Test
  public void testGetState() {
    Tile t = new Tile(1,1);
    assertEquals(t.getState(), Tile.State.NORMAL);
  }


  @Test
  public void testSetState() {
    Tile t = new Tile(1,1);
    assertEquals(t.getState(), Tile.State.NORMAL);
    t.setState(Tile.State.FLAME);
    assertEquals(t.getState(), Tile.State.FLAME);
  }

  @Test
  public void testToString() {
    Tile t = new Tile(1,1);
    assertEquals(t.toString(), "(1,1) " + t.getColor());
  }

}
