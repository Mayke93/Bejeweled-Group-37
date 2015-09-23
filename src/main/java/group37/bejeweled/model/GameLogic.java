package main.java.group37.bejeweled.model;

import java.util.ArrayList;
import java.util.List;

import main.java.group37.bejeweled.Board.Board;
import main.java.group37.bejeweled.Board.Tile;
import main.java.group37.bejeweled.view.Animation;
import main.java.group37.bejeweled.view.Main;

public class GameLogic {
  private static final int SIZE = Game.SIZE;
  private Board board;
  private Main boardPanel;
  private CombinationFinder finder;
  private Game game;

  public GameLogic(Game game) {
    this.game = game;
  }
  
  public void setFinder(CombinationFinder finder) {
    this.finder = finder;
  }

  public void setBoard(Board board) {
    this.board = board;
  }
  
  public void setBoardPanel(Main boardPanel) {
    this.boardPanel = boardPanel;
  }
  /**
   * Delete all combinations found on the board.
   */
  public void deleteChains() {
    List<Combination> chains = finder.getAllCombinationsOnBoard();
    List<Tile> tiles = new ArrayList<Tile>();
    
    for (Combination comb: chains) {
      game.updateScore(comb.getType());
      tiles.addAll(comb.getTiles());
    }
    
    deleteTiles(tiles);
  }
  
  /**
   * Delete all the tiles in 'tiles' from the board.
   * @param tiles list of tiles to delete.
   */
  public void deleteTiles(List<Tile> tiles) {
    for (Tile tile: tiles) {
      board.getTileAt(tile.getX(), tile.getY()).delete = true;
      Logger.log("Delete Tile: " + tile);
      for (int i = tile.getY() - 1; i >= 0; i--) {
        board.getTileAt(tile.getX(), i).increaseLevel();
      }
    }

    boardPanel.animations.setType(Animation.Type.REMOVE);
    boardPanel.animations.startRemove(tiles);
  }


  /**
   * If there are empty spaces, this method 'drops' the tile above this space into this space.
   */
  public void dropTiles() {    
    int level = 0;
    for (int row = SIZE - 1; row >= 0; row--) {
      for (int col = 0; col < SIZE; col++) {
        level = board.getTileAt(col, row).getLevel();
     
        if (level > 0) {
          board.setTileAt(board.getTileAt(col, row).clone(col, row + level), col, row + level);
          board.getTileAt(col, row + level).setLevel(0);
          board.getTileAt(col, row).delete = true;
          board.getTileAt(col, row + level).delete = false;
          board.getTileAt(col, row).setLevel(0);
        }
      }
    }
    for (int row = SIZE - 1; row >= 0; row--) {
      for (int col = 0; col < SIZE; col++) {
        if (board.getTileAt(col, row).delete) {
          board.setTileAt(game.setRandomTile(col,row), col, row);
          board.getTileAt(col, row).delete = false;
        }
        if (board.getTileAt(col, row).remove) {
          board.getTileAt(col, row).remove = false;
        }
      }
    }
    boardPanel.repaint();
    
    List<Combination> chains = finder.getAllCombinationsOnBoard();
    if (chains.size() != 0) {
      deleteChains();
    }
  }

}
