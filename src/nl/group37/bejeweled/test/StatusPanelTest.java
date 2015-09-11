package nl.group37.bejeweled.test;

import static org.junit.Assert.assertEquals;

import nl.group37.bejeweled.view.StatusPanel;

//import javax.swing.JLabel;

import org.junit.Test;

public class StatusPanelTest {

  StatusPanel test = new StatusPanel();


  @Test
  public void testSetScore() {
    test.setScore(50);
    assertEquals("Score: 50", test.getScoreLabel().getText());

    test.setScore(100);
    assertEquals("Score: 100", test.getScoreLabel().getText());
  }

  @Test
  public void testSetLevel() {
    test.setLevel(2);
    assertEquals("Level: 2", test.getLevelLabel().getText());
  }

}
