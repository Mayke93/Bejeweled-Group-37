package bejeweled37;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;


//import Tile.State;

public class Game {
  private Tile[][] board;
  public List<Tile> swapTiles;
  private int score = 0;
  private Board boardPanel;
  private StatusPanel panel;
  private CombinationFinder finder;
  private static final int SIZE = Board.SIZE;

  /**
   * Create game object.
   * @param boardPanel object for GUI.
   * @param panel object for updating the labels.
   */
  public Game(Board boardPanel,StatusPanel panel) {
    this.boardPanel = boardPanel;
    this.panel = panel;
    this.finder = new CombinationFinder(board);
    swapTiles = new ArrayList<Tile>();
    generateRandomBoard();
  }

  /**
   * Add tile to swapTiles bases on location from the mouseEvent.
   * @param loc location of tile
   */
  public void addTile(Point loc) {
    int col = loc.x;
    int row = loc.y;
    if (!swapTiles.contains(board[col][row])) {
      //System.out.println("Mouse Dragged: (" + col + ", " + row + ")");
      swapTiles.add(board[col][row]);
      boardPanel.setFocus(loc);
      if (swapTiles.size() == 2 && canSwap()) {              
        boardPanel.swapTiles(swapTiles);
        swapTiles.clear();
      }
    }
  }

  /**
   * Prints the combinations obtained by getAllCombinationsOnBoard().
   */
  public void printCombinations() {
    List<Combination> res = finder.getAllCombinationsOnBoard();
    System.out.println("chains: " + res.size());
    for (Combination combi : res) {
      System.out.println("\tType: " + combi.getState());
      System.out.println("\t" + combi.getTiles());
    }
  }

  /**
   * Delete all tiles that form a combination on the current board.
   */
  public void deleteTiles() {
    List<Combination> chains = finder.getAllCombinationsOnBoard();
    List<Tile> tiles = new ArrayList<Tile>();
    for (int row = SIZE - 1; row >= 0; row-- ) {
      for (int col = 0; col < SIZE; col++) {

        if (containsTile(board[col][row], chains)) {

          tiles.add(board[col][row]);
          for (int i = row - 1; i >= 0; i--) {
            board[col][i].increaseLevel();
          }
        }

      }
    }
    boardPanel.animations.setType(Animation.Type.REMOVE);
    boardPanel.animations.startRemove(tiles);
    printCombinations();

    /*Tile r = null;
    for(int i= 0; i < SIZE; i++){
       for(int j = 0; j < SIZE; j++){
       r = board[j][i];
       System.out.print(r.getLevel() + " ");
      }
      System.out.println();
    }*/


    //dropTiles(tiles);
    //boardPanel.repaint();
  }

  /**
   * If there are empty spaces, this method 'drops' the tile above this space into this space.
   */
  public void dropTiles() {
    int level = 0;
    for (int row = SIZE - 1; row >= 0; row--) {
      for (int col = 0; col < SIZE; col++) {
        level = board[col][row].getLevel();
        if (level > 0) {
          board[col][row + level] = board[col][row].clone(col, row + level);
          board[col][row + level].setLevel(0);
          board[col][row].setLevel(0);
          board[col][row].setState(Tile.State.DEFAULT);
        }
      }
    }
    for (int row = SIZE - 1; row >= 0; row--) {
      for (int col = 0; col < SIZE; col++) {
        if (board[col][row].getState() == Tile.State.DEFAULT) {
          board[col][row].setRandomTile();
        }
        if (board[col][row].remove) {
          board[col][row].remove = false;
        }
      }
    }
    boardPanel.repaint();

  }

  /**
   * Check if chains contains the tile t.
   * @param tile tile to check for in chains.
   * @param chains this list of combinatians.
   * @return true if chains contains tile.
   */
  private boolean containsTile(Tile tile, List<Combination> chains) {
    for (Combination c: chains) {
      if (c.getTiles().contains(tile)) {
        return true;
      }
    }
    return false;
  }


  /**
   * Create a board of random jewels without a sequence of 3 or more tiles with the same color.
   */
  public void generateRandomBoard() {
    board = new Tile[Board.SIZE][Board.SIZE]; 
    for (int i = 0; i < Board.SIZE; i++) {
      for (int j = 0; j < Board.SIZE; j++) {
        board[i][j] = new Tile(i,j);
      }

      //Redo column if a sequence has been detected
      if (hasSequence(i)) {
        i--;
      }
    }
    finder.setBoard(this.board);
  }

  /**
   * Checks if column i that just has been added doesn't create a sequence of 3 or more colours.
   * @param row column to check for sequences.
   * @return true iff there is found a sequence of three or more jewels.
   */
  private boolean hasSequence(int row) {
    int sum = 0;
    //Find sequence in row i
    for (int j = 1; j < Board.SIZE; j++) {
      if (board[row][j].equalsColor(board[row][j - 1])) {
        sum++;
      } else {
        sum = 0;
      }

      if (sum >= 2) {
        return true;
      }
    }

    //If there are only 1 or 2 rows created, don't check for horizontal sequences
    if (row <= 1) {
      return false; 
    }

    //Find horizonal sequences
    for (int j = 0; j < Board.SIZE; j++) {
      sum = 0;
      sum += (board[row - 1][j].equalsColor(board[row][j]) ? 1 : 0);
      sum += (board[row - 2][j].equalsColor(board[row][j]) ? 1 : 0);

      if (sum == 2) {
        System.out.println("i,j: " + row + "," + j);
        return true;
      }
    }
    return false;
  }

  /**
   * Check if two tiles can be swapped and
   * what kind of jewel should be created based on the size of the found sequence.
   * @param t0 first tile to swap
   * @param t1 second tile to swap
   * @return true iff swapping tiles t0 and t1 results in a valid combination.
   */
  public Tile.State checktype(Tile t0, Tile t1) {
    Tile.State res = null;
    String c1 = Tile.colors[board[t0.getX()][t0.getY()].getIndex()];
    String c2 = Tile.colors[board[t1.getX()][t1.getY()].getIndex()];
    Tile tile = null;
    String color = null;
    //swap tiles to look in the rows where the tile will be in case it can be switched
    swapTiles(t0,t1);

    for (int i = 1; i < 3; i++) {

      if (i == 1) {
        tile = t0;
        color = c1;
      }
      if (i == 2) {
        tile = t1;
        color = c2;
      }

      //check x direction
      int sum = 1;
      for (int q = tile.getX() + 1; q < SIZE; q++) {
        if (Tile.colors[board[q][tile.getY()].getIndex()].equals(color)) {
          sum++;
          System.out.println("1e " + sum);
        } else {
          break;
        }
      }
      for (int q = tile.getX() - 1; q >= 0; q--) {
        if (Tile.colors[board[q][tile.getY()].getIndex()].equals(color)) {
          sum++;
          System.out.println("2e " + sum);
        } else {
          break;
        }
      }

      if (sum == 3) {
        res = Tile.State.NORMAL;
      }
      if (sum == 4) {
        res = Tile.State.FLAME;
      }
      if (sum == 5) {
        res = Tile.State.HYPERCUBE;
      }

      //check y direction
      sum = 1;
      for (int q = tile.getY() + 1; q < SIZE; q++) {
        if (Tile.colors[board[tile.getX()][q].getIndex()].equals(color)) {
          sum++;
          System.out.println("3e " + sum);
        } else {
          break;
        }
      }
      for (int q = tile.getY() - 1; q >= 0; q--) {
        if (Tile.colors[board[tile.getX()][q].getIndex()].equals(color)) {
          sum++;
          System.out.println("4e " + sum);
        } else {
          break;
        }
      }

      if (sum == 3) {
        res = Tile.State.NORMAL;
      }
      if (sum == 4) {
        res = Tile.State.FLAME;
      }
      if (sum == 5) {
        res = Tile.State.HYPERCUBE;
      }
    }
    //swap the tiles back to original position
    swapTiles(t0,t1);
    System.out.println(res);
    return res;
  }

  /**
   * Method to check whether there are possible moves left in the game
   * @return true if there are possible moves, false if there are none.
   */
  public boolean possibleMove() {
    boolean possiblemove = false;
    Tile t0 = null;
    Tile t1 = null;

    //check combinations in x direction
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < 7; j++) {
        t0 = board[j][i];
        t1 = board[j + 1][i];
        if (!(checktype(t0,t1) == null)) {
          possiblemove = true;
        }
      }
    }

    //check combinations in y direction
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < 7; j++) {
        t0 = board[i][j];
        t1 = board[i][j + 1];
        if (!(checktype(t0,t1) == null)) {
          possiblemove = true;
        }
      }
    }
    return possiblemove;
  }

  

  

  /**
   * Switch tile t0 and t1 on the board.
   * @param t0 first tile to swap
   * @param t1 second tile to swap
   */
  public void swapTiles(Tile t0, Tile t1) {
    Tile temp = board[t0.getX()][t0.getY()];
    board[t0.getX()][t0.getY()] = board[t1.getX()][t1.getY()];
    board[t1.getX()][t1.getY()] = temp;

    Point tiles = (Point) t0.getLoc().clone();
    t0.setLoc(t1.getX(),t1.getY());
    t1.setLoc(tiles);
  }

  /**
   * Swap two tiles if it result in a sequence of 3 of more tiles with the same color.
   */
  public boolean canSwap() {
    /*System.out.println(swapTiles.get(0).getX() + "," + swapTiles.get(0).getY());
      System.out.println(swapTiles.get(1).getX() + "," + swapTiles.get(1).getY());*/

    Tile t0 = board[swapTiles.get(0).getX()][swapTiles.get(0).getY()];
    Tile t1 = board[swapTiles.get(1).getX()][swapTiles.get(1).getY()];

    swapTiles(t0,t1);
    Combination l1 = finder.getSingleCombinationX(t0);
    Combination l2 = finder.getSingleCombinationX(t1);
    Combination l3 = finder.getSingleCombinationY(t0);
    Combination l4 = finder.getSingleCombinationY(t1);
    swapTiles(t0,t1);

    Tile.State type = null;
    if (!l1.getTiles().isEmpty()) {
      type = l1.getState();
      System.out.println("in1");
    } else if (!l2.getTiles().isEmpty()) {
      type = l2.getState();
      System.out.println("in2");
    } else if (!l3.getTiles().isEmpty()) {
      type = l3.getState();
      System.out.println("in3");
    } else if (!l4.getTiles().isEmpty()) {
      type = l4.getState();
      System.out.println("in4");
    }

    //Tile.State type = checktype(t0,t1);

    if (type == null) {
      return false;
    }

    if (!isNeighbour(t0,t1)) {
      System.out.println("t0 and t1 are no neighbours.");
      return false;
    }
    System.out.println(type);
    updateScore(type);
    updateLevel();
    return true;
  }

  /**
   * Return true if t0 and t1 are neighbours.
   * @param t0 tile 1.
   * @param t1 tile 2.
   * @return true if t0 and t1 are next to each other.
   */
  public boolean isNeighbour(Tile t0, Tile t1) {
    /*System.out.println(t0.getX() + "," + t0.getY());
      System.out.println(t1.getX() + "," + t1.getY());*/
    if (Math.abs(t0.getX() - t1.getX()) == 1 && Math.abs(t0.getY() - t1.getY()) == 0) {
      return true;
    }
    if (Math.abs(t0.getX() - t1.getX()) == 0 && Math.abs(t0.getY() - t1.getY()) == 1) {
      return true;
    }
    return false;
  }

  /**
   * Update score in the view.
   * @param type change score based on the value of type.
   */
  private void updateScore(Tile.State type) {
    int score = 0;
    switch (type) {
      case NORMAL:
        score = 50;
        break;
      case FLAME:
        score = 150;
        break;
      case HYPERCUBE:
        score = 500;
        break;
      case STAR:
        score = 150;
        break;
      default:
        break;
    }
    this.score += score;
    panel.setScore(this.score);
  }

  /**
   * Increase the levelnumber when a certain score is reached.
   */
  private void updateLevel() {
    if (this.score >= 1000 && this.score < 3500) {
      panel.setLevel(2);
    }
    if (this.score >= 3500 && this.score < 5500) {
      panel.setLevel(3);
    }
    if (this.score >= 5500 && this.score < 8000) {
      panel.setLevel(4);
    }
    if (this.score >= 8000 && this.score < 11000) {
      panel.setLevel(5);
    }
  }
  
  /**
   * End game if there is no possible combination.
   */
  public void endGame() {
    JLabel label1 = new JLabel("No possible combination",JLabel.CENTER);
    label1.setVerticalTextPosition(JLabel.TOP);
    label1.setHorizontalTextPosition(JLabel.CENTER);
    if (!(possibleMove())) {
      this.boardPanel.add(label1);
      return;
    }
  }

  /**
   * Reset game.
   */
  public void reset() {
    this.score = 0;
    swapTiles = new ArrayList<Tile>();
    generateRandomBoard();
    boardPanel.repaint();
  }

  /**
   * Get board object.
   * @return the board
   */
  public Tile[][] getBoard() {
    return this.board;
  }

  /**
   * Get the list with the selected tiles to swap.
   * @return the list swapTiles
   */
  public List<Tile> getSwaptiles() {
    return this.swapTiles;
  }

}