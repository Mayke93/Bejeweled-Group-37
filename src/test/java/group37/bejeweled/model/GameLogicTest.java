package test.java.group37.bejeweled.model;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.Test;

//import main.java.group37.bejeweled.board.Board;
//import main.java.group37.bejeweled.board.Tile;
//import main.java.group37.bejeweled.model.CombinationFinder;
import main.java.group37.bejeweled.model.Game;
//import main.java.group37.bejeweled.model.GameLogic;
import main.java.group37.bejeweled.view.Main;
import main.java.group37.bejeweled.view.StatusPanel;

public class GameLogicTest {

//  /**
//   * makes a game.
//   * @return a game object
//   */
//  public Game gameMaker() {
//    StatusPanel sp = new StatusPanel();
//    JFrame frame = new JFrame();
//    Main main = new Main(frame, sp);
//    Game game = new Game(main, sp);
//    return game;
//  }

//  /**
//   * test for the constructor of gameLogic.
//   */
//  @Test
//  public void gameLogicConstrTest() {
//    GameLogic gl = new GameLogic(gameMaker());
//    assertNotNull(gl);
//  }
  
//  /**
//   * test getters and setters in gamelogic.
//   */
//  @Test
//  public void settergetterTest() {
//    Tile[][] tiles = new Tile[1][1];
//    Board board = new Board(tiles);
//    CombinationFinder cf = new CombinationFinder(board);
//    GameLogic gl = new GameLogic(gameMaker());
//    gl.setFinder(cf);
//    assertEquals(gl.getFinder(), cf);
//    gl.setBoard(board);
//    assertEquals(gl.getBoard(), board);
//    
//    StatusPanel sp = new StatusPanel();
//    JFrame frame = new JFrame();
//    Main main = new Main(frame, sp);
//    gl.setBoardPanel(main);
//    assertEquals(gl.getBoardPanel(), main);   
//  }
  
//  /**
//   * test for deletechains method.
//   */
//  @Test
//  public void deleteChainsTest() {
//    Tile[][] tiles = new Tile[3][2];
//    Tile t0 = new Tile(0,0);
//    t0.setIndex(2);
//    Tile t1 = new Tile(1,0);
//    t1.setIndex(2);
//    Tile t2 = new Tile(2,0);
//    t2.setIndex(2);
//    Tile t3 = new Tile(0,1);
//    t3.setIndex(1);
//    Board board = new Board(tiles);
//    board.setTileAt(t0, 0, 0);
//    board.setTileAt(t1, 1, 0);
//    board.setTileAt(t2, 2, 0);
//    board.setTileAt(t3, 0, 1);
//    board.setTileAt(t2, 1, 1);
//    board.setTileAt(t3, 2, 1);
//    
//    GameLogic gl = new GameLogic(gameMaker());
//   
//    gl.setBoard(board);
//    gl.deleteChains();
//    CombinationFinder cf = new CombinationFinder(board);
//    assertTrue(cf.getAllCombinationsOnBoard().isEmpty());
//  }

}
