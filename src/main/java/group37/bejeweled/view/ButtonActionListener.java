package main.java.group37.bejeweled.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.group37.bejeweled.model.Game;

public class ButtonActionListener implements ActionListener{
  private StatusPanel panel;

  public ButtonActionListener(StatusPanel panel) {
    this.panel = panel;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    panel.game.generateRandomBoard();
    panel.game.setLevel(1);
    panel.game.setScore(0);
    panel.setScore(0);
    panel.setLevel(1);
    panel.main.repaint();
    panel.repaint();
  }
}
