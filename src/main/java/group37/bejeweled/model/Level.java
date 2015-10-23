package main.java.group37.bejeweled.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import main.java.group37.bejeweled.view.Panel;

public class Level extends Observable {
  
  public int level;
  private ArrayList<Observer> obs = new ArrayList<Observer>();
  
  /**
   * Represent the level object for a game.
   */
  public Level() {
    super();
    this.level = 1;
  }
  
  public void registerObserver(Panel panel) {
    obs.add(panel);    
  }

  public void removeObserver(Observer observer) {
    obs.remove(observer);    
  }
  
  /**
   * Increase the levelnumber when a certain score is reached.
   */
  public void updateLevel(int score) {
    int oldlevel = level;
    if (score >= 1000 && score < 3500) {
      level = 2;
    }
    if (score >= 3500 && score < 5500) {
      level = 3;
    }
    if (score >= 5500 && score < 8000) {
      level = 4;
    }
    if (score >= 8000 && score < 11000) {
      level = 5;
    }
    if (!(level == oldlevel)) {
      System.out.println("level changed!");
      notifyObservers(this,level);
    }
  }
  
  /**
   * Notify the observers when the score has changed.
   * @param observable the observable
   * @param level the level to be set
   */
  public void notifyObservers(Observable observable, int level) {
    for (Observer ob : obs) {
      ob.update(observable, this);
    }
  }

  public int getLevel() {
    return this.level;
  }

  public void setLevel(Integer level) {
    this.level = level;
    notifyObservers(this,level);
  }
  
}
