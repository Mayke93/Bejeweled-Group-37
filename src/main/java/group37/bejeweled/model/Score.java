package main.java.group37.bejeweled.model;

import main.java.group37.bejeweled.board.Tile;
import main.java.group37.bejeweled.combination.Combination;
import main.java.group37.bejeweled.view.Panel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Score extends Observable {

  public int score;
  private ArrayList<Observer> obs = new ArrayList<Observer>();
  
  /**
   * Represent the score object for a game.
   */
  public Score() {
    super();
    this.score = 0;
  }

  public void registerObserver(Panel panel) {
    obs.add(panel);    
  }

  public void removeObserver(Observer observer) {
    obs.remove(observer);    
  }

  /**
   * Notify the observers when the score has changed.
   * @param observable the observable
   * @param score the score to be set
   */
  public void notifyObservers(Observable observable, int score) {
   // this.score = score;
    for (Observer ob : obs) {
      ob.update(observable, this);
    }
  }
  
  public int getScore() {
    return this.score;
  }
  
  public void setScore(int sc) {
    this.score = sc;
    notifyObservers(this, score);
  }
  
  /**
   * Update score in the view.
   * @param combi change score based on the type of combination.
   */
  public void updateScore(Combination combi) {
    this.score += combi.score();
    Logger.log("Add score: " + combi.score());
    Logger.log("Total Score: " + this.score);
    notifyObservers(this, score);
  }
  
  /**
   * Update the score in the view for points obtained by special gems.
   * @param combi the combination containing the special gem
   * @param tiles all the tiles deleted by the special gem
   */
  public void updateScoreSpecialGem(Combination combi, List<Tile> tiles) {
    for (int i = 0; i < tiles.size(); i++) {
      this.score += combi.containsSpecialGem().getScore();
    }
    Logger.log("Add score: " + tiles.size() + "*" + combi.containsSpecialGem().getScore());
    Logger.log("Total Score: " + this.score);
    notifyObservers(this, score);
  }
  
  /**
   * Resets the score of the game to 0.
   */
  public void resetScore() {
    this.score = 0;
    notifyObservers(this, score);
  }

}
