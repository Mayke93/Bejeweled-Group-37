package main.java.group37.bejeweled.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import main.java.group37.bejeweled.Launcher;

public class ButtonActionListener implements ActionListener{
  private StatusPanel panel;
  private StartScreen startscreen;
  private Launcher launcher;

  public ButtonActionListener(StatusPanel panel, StartScreen start, Launcher launch) {
    this.panel = panel;
    this.startscreen = start;
    this.launcher = launch;
  }

  
  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == panel.saveGame) {
      //code for saving the game.
    }
    if (event.getSource() == panel.button) {
      launcher.getContentPane().remove(panel.main);
      launcher.getContentPane().add(startscreen);
    }
    if (event.getSource() == startscreen.newGame) {
      launcher.getContentPane().remove(startscreen);

      panel = new StatusPanel(launcher, startscreen);
      Main main = new Main(launcher,panel);
      panel.setMain(main);
      main.setLayout(new BorderLayout());     
      main.add(panel,BorderLayout.WEST);

      launcher.getContentPane().add(main);
       
      panel.game.generateRandomBoard();
      panel.game.logic.level.setLevel(1);
      panel.game.logic.score.setScore(0);
      panel.setScore(0);
      panel.setLevel(1);
      panel.main.repaint();
      panel.repaint();
 
    }
    if (event.getSource() == startscreen.loadGame) {
      //code for load game button
    }
    
    launcher.getContentPane().validate();
    launcher.getContentPane().repaint();
  }
}
