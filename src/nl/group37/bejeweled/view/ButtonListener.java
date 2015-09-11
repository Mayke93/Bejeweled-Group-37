package nl.group37.bejeweled.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonListener implements ActionListener{

  ButtonListener() {

  }

  @Override
  public void actionPerformed(ActionEvent event){
    System.out.println("Button1 has been clicked!");
  }

}
