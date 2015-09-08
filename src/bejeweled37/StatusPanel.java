package bejeweled37;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
/**
 * Class for diplaying the status of the game.
 * @author samuelsital
 *
 */
class StatusPanel extends JPanel{
	private JLabel scoreLabel = new JLabel("Score: ");
	private JLabel levelLabel = new JLabel("Level:");

	private static final Font font = new Font("Serif", Font.BOLD, 35);

	public StatusPanel(){
		setLayout(new GridBagLayout());
    	setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 10));
		setOpaque(false);

		scoreLabel.setFont(font);
		scoreLabel.setForeground(Color.white);

		levelLabel.setFont(font);
		levelLabel.setForeground(Color.white);

    	Box box = Box.createVerticalBox();
    	box.add(Box.createVerticalGlue());
    	box.add(scoreLabel);
    	box.add(levelLabel);
    	box.add(Box.createVerticalGlue());
    	add(box);

    	setScore(0);
    	setLevel(1);
	}
	
	public void setScore(int score){
		this.scoreLabel.setText("Score: " + Integer.toString(score));
	}
	public void setLevel(int level){
		this.levelLabel.setText("Level: " + Integer.toString(level));
	}
}