package test.java.group37.bejeweled;

import static org.junit.Assert.*;

import main.java.group37.bejeweled.Board.Tile;

import org.junit.Test;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;


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
  public void increseLevelTest() {
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
    ImageIcon image = new ImageIcon(this.getClass().getResource("img/gemOrange.png"));
    System.out.println(tile.getImage());
    assertEquals(tile.getImage(), image);
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
}
