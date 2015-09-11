package nl.group37.bejeweled.test;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.group37.bejeweled.view.StatusPanel;

public class StatusPanelTest {

  StatusPanel test = new StatusPanel();


  @Test
  public void testSetScore() {
    test.setScore(50);
    assertEquals("Score: 50", test.getScore());

    test.setScore(100);
    assertEquals("Score: 100", test.getScore());

  }

  @Test
  public void testSetLevel() {

    test.setLevel(2);
    assertEquals("Level: 2", test.getLevel());
    
  }

}
