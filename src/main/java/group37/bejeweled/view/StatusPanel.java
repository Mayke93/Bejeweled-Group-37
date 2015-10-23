package main.java.group37.bejeweled.view;

import java.util.Observer;

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
    hide();
  }

  @Override
  public void hide() {
    hcb.hide(this.timeLabel);
  }

}