package test.java.group37.bejeweled.model;

import static org.junit.Assert.*;

import java.awt.Point;

import javax.swing.JFrame;

import org.junit.Test;

import main.java.group37.bejeweled.board.Board;
import main.java.group37.bejeweled.board.Tile;
import main.java.group37.bejeweled.combination.CombinationFinder;
import main.java.group37.bejeweled.combination.Combination.Type;
import main.java.group37.bejeweled.model.Game;
import main.java.group37.bejeweled.view.Main;
import main.java.group37.bejeweled.view.StatusPanel;

//import org.junit.Test;


/**
 * Simple basic test for class Game.
 * @author Mayke Kloppenburg
 * cannot be fully tested yet
 */
public class GameTest {

  /**
   * makes a game.
   * @return a game object
   */
  public Game gameMaker() {
    StatusPanel sp = new StatusPanel();
    JFrame frame = new JFrame();
    Main main = new Main(frame, sp);
    Game game = new Game(main, sp);
    return game;
  }
//
//  /**
//   * test for the constructor of game.
//   */
//  @Test
//  public void gameConstrTest() {
//    StatusPanel sp = new StatusPanel();
//    JFrame frame = new JFrame();
//    Main main = new Main(frame, sp);
//    Game game = new Game(main, sp);
//    assertNotNull(game);
//  }

//    /**
//     * test for the addtile method.
//     */
//    @Test
//    public void addTileTest() {
//      Game game = gameMaker();
//      Tile[][] tiles = new Tile[2][1];
//      game.addTile(new Point(1,1));
//      
//    }

//  /**
//   * test for the updatescore method.
//   */
//  @Test
//  public void updateScoreTest() {
//    Game game = gameMaker();
//    game.updateScore(Type.NORMAL);
//    assertEquals(game.getScore(), 50);
//    game.setScore(0);
//    game.updateScore(Type.FLAME);
//    assertEquals(game.getScore(), 150);
//    game.setScore(0);
//    game.updateScore(Type.HYPERCUBE);
//    assertEquals(game.getScore(), 500);
//    game.setScore(0);
//    game.updateScore(Type.STAR);
//    assertEquals(game.getScore(), 150);
//  }
//  
//  /**
//   * test for the updatelevel an getLevel method.
//   */
//  @Test
//  public void updateLevelGetLevelTest() {
//    Game game = gameMaker();
//    assertEquals(game.getLevel(), 1);
////    TODO update level cannot be tested yet as it is called by canSwap
//    // and it is private, so cannot be called
////    game.setScore(1000);
////    assertEquals(game.getLevel(), 2);
////    game.setScore(3500);
////    assertEquals(game.getLevel(), 3);
////    game.setScore(8000);
////    assertEquals(game.getLevel(), 4);
//  }
//  
//  /**
//   * test for the reset method.
//   */
//  @Test
//  public void resetTest() {
//    Game game = gameMaker();
//    game.setScore(1000);
//    assertEquals(game.getScore(), 1000);
//    game.reset();
//    assertEquals(game.getScore(), 0);
//  }
//  
////  /**
////   * test for the reset method.
////   */
////  @Test
////  public void setGetBoardTest() {
////    Game game = gameMaker();
////    Tile[][] tile = new Tile[1][1];
////    Board board = new Board(tile);
////    game.setBoard(board);
////    assertEquals(game.getBoard(), board);
////  }
//  
//  /**
//   * test for the getter and setter from the score.
//   */
//  @Test
//  public void getSetScoreTest() {
//    Game game = gameMaker();
//    game.setScore(1000);
//    assertEquals(game.getScore(), 1000);
//  }
//  
//  /**
//   * test for the getter and setter from the finder.
//   */
//  @Test
//  public void getSetFinderTest() {
//    Game game = gameMaker();
//    Tile[][] tile = new Tile[1][1];
//    Board board = new Board(tile);
//    CombinationFinder cf = new CombinationFinder(board);
//    game.setFinder(cf);
//    assertEquals(game.getFinder(), cf);
//  }
}
