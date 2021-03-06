package test.java.group37.bejeweled.model;

import static org.junit.Assert.assertEquals;

import main.java.group37.bejeweled.view.StatusPanel;

import org.junit.Before;

import org.junit.Test;

/**
 * Test the StatusPanel class.
 * @author group37
 */
public class StatusPanelTest {

  StatusPanel test;
  
  @Before
  public void init() {
    test = new StatusPanel();
  }

  /**
   * Test the setScore method.
   */
  @Test
  public void testSetScore() {
    test.setScore(50);
    assertEquals("Score: 50", test.getScoreLabel().getText());

    test.setScore(100);
    assertEquals("Score: 100", test.getScoreLabel().getText());
  }

  /**
   * Test the setLevel method.
   */
  @Test
  public void testSetLevel() {
    test.setLevel(2);
    assertEquals("Level: 2", test.getLevelLabel().getText());
  }

}
