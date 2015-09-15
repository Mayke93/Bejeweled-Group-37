package nl.group37.bejeweled.main.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that sees if the button is clicked.
 * @author group37
 */
class ButtonListener implements ActionListener{

  ButtonListener() {

  }

  /**
   * Method to carry out when the action is performed.
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    System.out.println("Button1 has been clicked!");
  }

}
