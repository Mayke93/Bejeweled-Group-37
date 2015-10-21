package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.Launcher;
import main.java.group37.bejeweled.model.Logger;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class StatusPanelTime extends Panel {
  
  private int time;  
  private Timer timer;
  
  /**
   * Statuspanel for time mode.
   */
  public StatusPanelTime() {
    time = 60;
    saveGame.setVisible(false);
    timer = new Timer();
    timer.schedule(tm,1000,1000);
  }

  TimerTask tm = new TimerTask(){
    @Override
    public void run() {
      updateTime();
      timeLabel.setText("Time: " + time);
      if (time == 0) {
        Panel.gameover = true;
        endGameTimer();
        tm.cancel();
      }
    }
  };

  /**
   * Update the timer (-1 second).
   */
  public void updateTime() {
    if (time > 0) {
      time = time - 1;
    }
  }
  
  /**
   * End game if timer is 0.
   */
  public void endGameTimer() {
    Logger.log("End Game");

    JLabel label = new JLabel("<html>You are out of time!<br>Press Quit</html>", JLabel.CENTER);
    label.setForeground(Color.WHITE);
    label.setVerticalTextPosition(JLabel.TOP);
    label.setHorizontalTextPosition(JLabel.CENTER);
    label.setFont(new Font("Euphemia UCAS",Font.PLAIN,40)); 
    label.setOpaque(true);
    label.setBackground(Color.BLACK);
    setAlignmentX(Component.CENTER_ALIGNMENT);
    add(label);
    hint.setVisible(false);
    
    this.main.repaint();
    this.repaint();

    Launcher.launcher.getContentPane().validate();
    Launcher.launcher.getContentPane().repaint();
    
    Logger.log("End game - out of time");
  }

}
