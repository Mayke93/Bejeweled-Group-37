package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.model.Game;

import java.awt.Component;
import java.util.Observer;

import javax.swing.JButton;

@SuppressWarnings("serial")
/**
 * Class for diplaying the status of the game.
 * @author group37
 *
 */
public class StatusPanel extends Panel implements Observer{


  /**
   * Create labels for displaying the status of the game.
   */
  public StatusPanel() {
    hcb = new HideLabelBehaviour();
    //timeLabel.setVisible(false);
    hide();
  }

  @Override
  public void hide() {
    hcb.hide(this.timeLabel);
    
  }

}