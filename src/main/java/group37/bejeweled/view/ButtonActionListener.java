package main.java.group37.bejeweled.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActionListener implements ActionListener{
  private StatusPanel panel;

  public ButtonActionListener(StatusPanel panel) {
    this.panel = panel;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    panel.game.generateRandomBoard();
    panel.game.logic.level.setLevel(1);
    panel.game.logic.score.setScore(0);
    panel.setScore(0);
    panel.setLevel(1);
    panel.main.repaint();
    panel.repaint();
  }
}
