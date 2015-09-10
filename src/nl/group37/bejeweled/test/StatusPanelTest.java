package nl.group37.bejeweled.test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;

import org.junit.Test;

public class StatusPanelTest {

	private JLabel scoreLabel = new JLabel("Score:");
	private JLabel levelLabel = new JLabel("Level:");

	
	@Test
	public void testStatusPanel() {
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 35));
		scoreLabel.setForeground(Color.white);
    
	}
	
	@Test
	public void testSetScore() {
		assertEquals("Score:", scoreLabel.getText());
	}

	
	

	@Test
	public void testSetLevel() {
		assertEquals("Level:", levelLabel.getText());
	}
	
	
	
	
	
	

}
