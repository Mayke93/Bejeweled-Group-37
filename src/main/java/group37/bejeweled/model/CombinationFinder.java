package main.java.group37.bejeweled.model;

import java.util.ArrayList;
import java.util.List;

import main.java.group37.bejeweled.Board.Board;
import main.java.group37.bejeweled.Board.Tile;
import main.java.group37.bejeweled.model.Combination.Type;
import main.java.group37.bejeweled.view.Main;

/**
 * This class can be used to find the type and tiles of combinations on the board
 * at that specific moment.
 * @author group37
 */
public class CombinationFinder {
  private static final int SIZE = Main.SIZE;
  private Board board;

  /**
   * The constructor for the combinationfinder.
   * @param board, the current board.
   */
  public CombinationFinder(Board board) {
    this.board = board;
  }
  
  /**
   * This method provides the possibility to set the board.
   * @param board, the board to be set.
   */
  public void setBoard(Board board) {
    this.board = board;
  }
  
  /**
   * Returns a list, which contains lists with 2 objects: (State,List of tiles),
   * eg. (Tile.State.NORMAL,List(t1,t2,t3)).
   * This is a list of all the valid combinations on the board at this time.
   */
  public List<Combination> getAllCombinationsOnBoard() {
    List<Combination> allcombinations = new ArrayList<Combination>();
    Combination tile = null;
    for (int i = 0; i < SIZE; i++) {      //for every tile on the board
      for (int j = 0; j < SIZE; j++) {
        tile = getSingleCombinationX(board.getTileAt(i,j));
        if (!tile.getTiles().isEmpty()) {
          if (!sameCombination(allcombinations, tile)) {
            allcombinations.add(tile);
          }
        }

        tile = getSingleCombinationY(board.getTileAt(i,j));
        if (!tile.getTiles().isEmpty()) {
          if (!sameCombination(allcombinations, tile)) {
            allcombinations.add(tile);
          }
        }
      }
    }
    return allcombinations;
  }
  
  /**
   * Sees whether 
   * @param tile
   * makes a valid combination in x direction on the board,
   * @return a list with first the state of the combination,
   *      and second the list of tiles in de combi.
   */
  public Combination getSingleCombinationX(Tile tile) {
    Combination combi = new Combination();
    List<Tile> tiles = new ArrayList<Tile>();

    //check x direction
    for (int q = tile.getX() + 1; q < SIZE; q++) { //check to the right
      if (tile.equalsColor(board.getTileAt(q, tile.getY()))) {
        tiles.add(board.getTileAt(q, tile.getY()));
      } else {
        break;
      }
    }
    for (int q = tile.getX() - 1; q >= 0; q--) {    //check to the left
      if (tile.equalsColor(board.getTileAt(q, tile.getY()))) {
        tiles.add(board.getTileAt(q, tile.getY()));
      } else {
        break;
      }
    }
    
    if (tiles.size() < 2) {                   //less than 3 in a row
      tiles.clear();
    } else {
      tiles.add(tile);
      if (tiles.size() == 3) {
        List<Tile> specialShape = findLTshapeX(tiles);   // check for T and L shapes
        if (specialShape.isEmpty()) {
          combi.setType(Type.NORMAL);
        } else {
          tiles.addAll(specialShape);
          combi.setType(Type.STAR);
        }
      } else if (tiles.size() == 4) {
        combi.setType(Type.FLAME);
      } else if (tiles.size() == 5) {
        combi.setType(Type.HYPERCUBE);
      }
      combi.setTiles(tiles);
    }
    return combi;
  }

  /**
   * Sees whether 
   * @param tile
   * makes a valid combination in y direction on the board,
   * @return a list with first the state of the combination,
   *     and second the list of tiles in de combi.
   */
  public Combination getSingleCombinationY(Tile tile) {
    Combination combi = new Combination();
    List<Tile> tiles = new ArrayList<Tile>();

    //check y direction
    for (int q = tile.getY() + 1; q < SIZE; q++) {   //check down
      if (tile.equalsColor(board.getTileAt(tile.getX(),q))) {
        tiles.add(board.getTileAt(tile.getX(),q));
      } else {
        break;
      }
    }
    for (int q = tile.getY() - 1; q >= 0; q--) {      //check up
      if (tile.equalsColor(board.getTileAt(tile.getX(),q))) {
        tiles.add(board.getTileAt(tile.getX(),q));
      } else {
        break;
      }
    }
    if (tiles.size() < 2) {           //less than 3 in a row
      tiles.clear();
    } else {
      tiles.add(tile);
      if (tiles.size() == 3) {
        List<Tile> specialShape = findLTshapeY(tiles);   // check for T and L shapes
        if (specialShape.isEmpty()) {
          combi.setType(Type.NORMAL);
        } else {
          tiles.addAll(specialShape);
          combi.setType(Type.STAR);
        }
      } else if (tiles.size() == 4) {
        combi.setType(Type.FLAME);
      } else if (tiles.size() == 5) {
        combi.setType(Type.HYPERCUBE);
      }
      combi.setTiles(tiles);
    }
    return combi;
  } 
  
  /**
   * Checks is combination already exists.
   * @param allcombinations : All combinations already in the list
   * @param singlecombination : Combination to be compared
   * @return true if singlecombination is already in allcombinations
   */
  public boolean sameCombination(List<Combination> allcombinations, Combination singlecombination) {
    boolean same = false;

    for (Combination combi : allcombinations) {
      if (combi.getTiles().containsAll(singlecombination.getTiles())) {
        same = true;
      }
    } 
    return same;
  }

  

  /**
   * Given
   * @param tiles three tiles of the same color in a row in x direction
   *        the method looks wheter these three tiles are part of an L or T shape.
   * @return the list of tiles which are added if an L or T shape is present.
   */
  public List<Tile> findLTshapeX(List<Tile> tiles) {
    List<Tile> newtiles = new ArrayList<Tile>();
    for (Tile t : tiles) {
      if (t.getY() + 1 < 8 && t.equalsColor(board.getTileAt(t.getX(),t.getY() + 1))) {
        if (t.getY() + 2 < 8 && t.equalsColor(board.getTileAt(t.getX(),t.getY() + 2))) { //2 erboven
          newtiles.add(board.getTileAt(t.getX(),t.getY() + 1));
          newtiles.add(board.getTileAt(t.getX(),t.getY() + 2));
          break;
        } else if (t.getY() - 1 >= 0 && t.equalsColor(board.getTileAt(t.getX(),t.getY() - 1))) {
          // 1 erboven, 1 beneden
          newtiles.add(board.getTileAt(t.getX(),t.getY() + 1));
          newtiles.add(board.getTileAt(t.getX(),t.getY() - 1));
          break;
        }
      } else if (t.getY() - 2 >= 0 && t.equalsColor(board.getTileAt(t.getX(),t.getY() - 1)) 
          && t.equalsColor( board.getTileAt(t.getX(),t.getY() - 2))) {    // 2 beneden
        newtiles.add(board.getTileAt(t.getX(),t.getY() - 1));
        newtiles.add(board.getTileAt(t.getX(),t.getY() - 2));
        break;
      }
    }
    return newtiles;
  }

  /**
   * Given
   * @param tiles three tiles of the same color in a row in y direction
   *      the method looks wheter these three tiles are part of an L or T shape.
   * @return the list of tiles which are added if an L or T shape is present.
   */
  public List<Tile> findLTshapeY(List<Tile> tiles) {
    List<Tile> newtiles = new ArrayList<Tile>();
    for (Tile t : tiles) {
      if (t.getX() + 1 < 8 && board.getTileAt(t.getX() + 1, t.getY()).equalsColor(t)) {
        if (t.getX() + 2 < 8 && board.getTileAt(t.getX() + 2, t.getY()).equalsColor(t)) {    //2 r
          newtiles.add(board.getTileAt(t.getX() + 1, t.getY()));
          newtiles.add(board.getTileAt(t.getX() + 2, t.getY()));
          break;
        } else if (t.getX() - 1 >= 0 
            && board.getTileAt(t.getX() - 1, t.getY()).equalsColor(t)) {     // 1 rechts, 1 links
          newtiles.add(board.getTileAt(t.getX() + 1, t.getY()));
          newtiles.add(board.getTileAt(t.getX() - 1, t.getY()));
          break;
        }
      } else if (t.getX() - 2 >= 0 && board.getTileAt(t.getX() - 1, t.getY()).equalsColor(t) 
          && board.getTileAt(t.getX() - 2, t.getY()).equalsColor(t)) {      // 2 links
        newtiles.add(board.getTileAt(t.getX() - 1, t.getY()));
        newtiles.add(board.getTileAt(t.getX() - 2, t.getY()));
        break;
      }
    }
    return newtiles;
  }
}
