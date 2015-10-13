package main.java.group37.bejeweled.model;

import main.java.group37.bejeweled.board.Board;
import main.java.group37.bejeweled.board.FlameTile;
import main.java.group37.bejeweled.board.NormalTile;
//import main.java.group37.bejeweled.board.HypercubeTile;
import main.java.group37.bejeweled.board.StarTile;
import main.java.group37.bejeweled.board.Tile;
import main.java.group37.bejeweled.combination.Combination;
import main.java.group37.bejeweled.combination.Combination.Type;
import main.java.group37.bejeweled.combination.CombinationFinder;
import main.java.group37.bejeweled.view.Animation;
import main.java.group37.bejeweled.view.Main;

import java.util.ArrayList;
import java.util.List;


public class GameLogic {
  public Score score;
  public Level level;
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

  /**
   * gets the combinationfinder.
   * @return a CombinationFinder object
   */
  public CombinationFinder getFinder() {
    return this.finder;
  }
  
  /**
   * Set the board.
   * @param board the board to be set.
   */
  public void setBoard(Board board) {
    this.board = board;
  }
  
  /**
   * gets the board.
   * @return a Board object
   */
  public Board getBoard() {
    return this.board;
  }

  /**
   * Set the current boardpanel.
   * @param boardPanel the panel to be set to. 
   */
  public void setBoardPanel(Main boardPanel) {
    this.boardPanel = boardPanel;
  }
  
  /**
   * gets the main.
   * @return a main object
   */
  public Main getBoardPanel() {
    return this.boardPanel;
  }

  /**
   * Delete all combinations found on the board.
   */
  public void deleteChains() {
    List<Combination> chains = finder.getAllCombinationsOnBoard();
    List<Tile> tiles = new ArrayList<Tile>();

    for (Combination comb: chains) {
      addTiles(comb.getTiles(), tiles);
      if (comb.containsSpecialGem() == null) {
        score.updateScore(comb);         //update normal score
      }
      Logger.log("Comb type: " + comb.getType());
      Logger.log("containsSpecialGem: " + comb.containsSpecialGem());
      
      if (comb.containsSpecialGem() != null) {
        Logger.log("Special gem in combination: " + comb.getType());
        List<Tile> gemTiles = getTilesToDeleteSpecialGem(comb);
        score.updateScoreSpecialGem(comb, gemTiles); //update score for detonating special gem
        addTiles(gemTiles,tiles);
        Logger.log("Delete " + gemTiles.size() + " additional tiles");
      }

      if (comb.isSpecialCombination()) {          //als er speciale combi is
        generateSpecialGem(comb);                 //maak dan een special gem
      }
    }
    level.updateLevel(score.getScore());
    deleteTiles(tiles);
  }
  
  private void addTiles(List<Tile> tilesToAdd, List<Tile> list) {
    for (Tile tile : tilesToAdd) {
      if (!list.contains(tile)) {
        list.add(tile);
      }
    }
  }

  /**
   * Delete all the tiles in 'tiles' from the board.
   * @param tiles list of tiles to delete.
   */
  public void deleteTiles(List<Tile> tiles) {
    for (Tile tile: tiles) {
      board.getTileAt(tile.getX(), tile.getY()).delete = true;
      Logger.log("Delete Tile: " + tile);
      if (tile.getNextType() == Type.NORMAL) {
        for (int i = tile.getY() - 1; i >= 0; i--) {
          board.getTileAt(tile.getX(), i).increaseLevel();
        }
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
          board.getTileAt(col, row).setNextType(Type.NORMAL);

          board.getTileAt(col, row + level).delete = false;
          
          board.getTileAt(col, row).setLevel(0);
        }
      }
    }
    Tile tile = new NormalTile(0,0);
    for (int row = SIZE - 1; row >= 0; row--) {
      for (int col = 0; col < SIZE; col++) {
        tile = board.getTileAt(col, row);
        if (tile.delete || tile.getNextType() != Type.NORMAL) {
          if (tile.getNextType() == Type.NORMAL) {
            board.setTileAt(game.setRandomTile(col,row), col, row);
          } else if (!(tile.getNextType() == Type.NORMAL) && !(tile.getNextType() == null)) {
            board.setTileAt(game.setSpecialTile(col,row,tile.getNextType()), col, row);
          } 
          tile = board.getTileAt(col,row);
          tile.setNextType(Type.NORMAL);
          tile.delete = false;
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

  /**
   * Finds the type of the special combination and calls the method to generate this special gem.
   * @param combi the combination to find the type of.
   */
  public void generateSpecialGem(Combination combi) {
    Logger.log("Generate special gem");
    combi.setNextType();
  }

  /**
   * Get list of tiles to delete in case of a special gem.
   * @param combi original tiles from the combination.
   * @return list of all tiles.
   */
  public List<Tile> getTilesToDeleteSpecialGem(Combination combi) {
    List<Tile> tiles = new ArrayList<Tile>();

    if (combi.containsSpecialGem() instanceof FlameTile) {
      tiles = board.getTilesToDeleteFlame(combi.containsSpecialGem());
    }
    if (combi.containsSpecialGem() instanceof StarTile) {
      tiles = board.getTilesToDeleteStar(combi.containsSpecialGem());
    }
//    if (combi.containsSpecialGem() instanceof HypercubeTile) {
//      tiles = board.getTilesToDeleteHypercube(combi.containsSpecialGem());
//    }

    return tiles;
  }
  
  public Score getScore() {
    return score;
  }

  public Level getLevel() {
    return level;
  }
  
}