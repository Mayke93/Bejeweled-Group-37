package test.java.group37.bejeweled.board;

import static org.junit.Assert.*;


import org.junit.Test;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import main.java.group37.bejeweled.board.Tile;



/**
 * Simple test class for Tile.
 * @author group37
 */
public class TileTest {

  /**
   * Simple input/output test for the constructor.
   */
  @Test
  public void tileConstrTest() {
    Tile tile = new Tile(1, 1);
    assertNotNull(tile);
  }
  
  /**
   * Simple input/output test for updateTranslationMethod.
   */
  @Test
  public void updateTranslationTest() {
    Tile tile = new Tile(1, 1);
    Point point = new Point(0,0);
    assertEquals(tile.getTranslation(), point);
    tile.updateTranslation(1, 1);
    Point newPoint = new Point(1,1);
    assertEquals(tile.getLoc(), newPoint);
  }
  
  /**
   * Simple input/output test for resetD method.
   */
  @Test
  public void resetDTest() {
    Tile tile = new Tile(1, 1);
    Point point = new Point(0,0);
    assertEquals(tile.getTranslation(), point);
    tile.updateTranslation(1, 1);
    Point newPoint = new Point(1,1);
    assertEquals(tile.getLoc(), newPoint);
    tile.resetD();
    assertEquals(tile.getTranslation(), point);
  }
  
  /**
   * Simple input/output test for clone method.
   */
  @Test
  public void cloneTest() {
    Tile tile = new Tile(1,1);
    tile.setIndex(2);
    tile.setImage(new ImageIcon(tile.paths[tile.getIndex()]));
    Tile clonedTile = tile.clone(1,1);
    assertTrue(tile.equals(clonedTile));
  }
  
  /**
   * Simple input/output test for increaselevel method.
   */
  @Test
  public void increaseLevelTest() {
    Tile tile = new Tile(1,1);
    assertEquals(tile.getLevel(), 0);
    tile.increaseLevel();
    assertEquals(tile.getLevel(), 1);
  }
  
  /**
   * Simple input/output test for getter and setter of image attribute.
   */
  @Test
  public void getSetImage() {
    Tile tile = new Tile(1,1);
    tile.setIndex(2);
    tile.setImage(new ImageIcon(tile.paths[tile.getIndex()]));
    ImageIcon image = new ImageIcon("src/img/gemOrange.png");
    System.out.println(tile.getImage());
    assertEquals(tile.getImage().getDescription(), image.getDescription());
  }
  
  /**
   * Simple input/output test for getter and setter of index attribute.
   */
  @Test
  public void getSetIndexTest() {
    Tile tile = new Tile(1,1);
    tile.setIndex(3);
    assertEquals(tile.getIndex(), 3);
  }
  
  /**
   * Simple input/output test for getter of X and Y coordinates.
   */
  @Test
  public void getXyTest() {
    Tile tile = new Tile(3,5);
    assertEquals(tile.getX(), 3);
    assertEquals(tile.getY(), 5);
  }
  
  /**
   * Simple input/output test for getter and setter of location attribute.
   */
  @Test
  public void getSetLocTest() {
    Tile tile = new Tile(6,2);
    Point point = new Point(6,2);
    assertEquals(tile.getLoc(), point);
    Point newPoint = new Point(3,2);
    tile.setLoc(3,2);
    assertEquals(tile.getLoc(), newPoint);
  }
  
  /**
   * Simple input/output test for getter and setter of level attribute.
   */
  @Test
  public void getSetLevel() {
    Tile tile = new Tile(6,2);
    assertEquals(tile.getLevel(), 0);
    tile.setLevel(4);
    assertEquals(tile.getLevel(), 4);
  }
  
  /**
   * Simple input/output test for equals color method.
   */
  @Test
  public void equalsColorTest() {
    Tile tile = new Tile(3,3);
    Tile tile2 = new Tile(1,6);
    tile.setIndex(5);
    tile2.setIndex(5);
    assertTrue(tile.equalsColor(tile2));
  }
  
  /**
   * Simple input/output test for equals method.
   */
  @Test
  public void equalsTrueTest() {
    Tile tile = new Tile(1,6);
    Tile tile2 = new Tile(1,6);
    tile.setIndex(5);
    tile2.setIndex(5);
    assertTrue(tile.equals(tile2));
  }
  
  /**
   * Simple input/output test for equals method.
   */
  @Test
  public void equalsFalseTest() {
    Tile tile = new Tile(6,1);
    Tile tile2 = new Tile(1,6);
    tile.setIndex(5);
    tile2.setIndex(5);
    assertFalse(tile.equals(tile2));
  }
  

  /**
   * Simple input/output test for hash method.
   */
  @Test
  public void hashCodeTest() {
    Tile tile = new Tile(1,1);
    tile.setIndex(2);
    Tile tile2 = new Tile(1,1);
    tile2.setIndex(2);
    assertTrue(tile.hashCode() == tile2.hashCode());
  }
  
  /**
   * Simple input/output test for getType method.
   */
  @Test
  public void getTypeTest() {
    Tile tile = new Tile(1,1);
    assertTrue(tile.getType().equals("Normal"));
  }
  
  /**
   * Simple input/output test for toString method.
   */
  @Test
  public void toStringTest() {
    Tile tile = new Tile(1,1);
    tile.setIndex(0);
    assertTrue(tile.toString().equals("(1,1) Blue"));
  }
  
}
