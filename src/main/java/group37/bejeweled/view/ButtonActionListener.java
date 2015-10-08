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
  
//
//  public ButtonActionListener(StartScreen start) {
//    this.startscreen = start;
//  }
//  
  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == panel.button) {
      launcher.getContentPane().remove(panel);
      launcher.getContentPane().add(startscreen);
    }
    if (event.getSource() == startscreen.newGame) {
      launcher.getContentPane().remove(startscreen);

      panel = new StatusPanel();
      Main main = new Main(launcher,panel);
      panel.setMain(main);
      main.setLayout(new BorderLayout());     
      main.add(panel,BorderLayout.WEST);

      launcher.getContentPane().add(main);
       
      panel.game.generateRandomBoard();
      panel.game.setLevel(1);
      panel.game.setScore(0);
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
