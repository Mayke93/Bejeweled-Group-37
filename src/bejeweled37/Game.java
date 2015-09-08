package bejeweled37;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


//import Tile.State;

public class Game {
  private Tile[][] board;
  public List<Tile> swapTiles;
  private int score = 0;
  private Board boardPanel;
  private StatusPanel panel;
  private static final int SIZE = Board.SIZE;

  public Game(Board boardPanel,StatusPanel panel){
    this.boardPanel = boardPanel;
    this.panel = panel;
    swapTiles = new ArrayList<Tile>();
    generateRandomBoard();
  }

  /**
   * Add tile to swapTiles bases on location from the mouseEvent.
   * @param loc location of tile
   */
  public void addTile(Point loc){
    int col = loc.x, row = loc.y;
    if(!swapTiles.contains(board[col][row])){
      //System.out.println("Mouse Dragged: (" + col + ", " + row + ")");
      swapTiles.add(board[col][row]);
      boardPanel.setFocus(loc);
      if(swapTiles.size() == 2 && canSwap()){                		
        boardPanel.swapTiles(swapTiles);
        swapTiles.clear();
      }
    }
  }

  /**
   * Prints the combinations obtained by getAllCombinationsOnBoard()
   */
  public void printCombinations() {
    List<Combination> res = getAllCombinationsOnBoard();
    System.out.println("chains: " + res.size());
    for(Combination combi : res) {
      System.out.println("\tType: " + combi.getState());
      System.out.println("\t" + combi.getTiles());
    }
  }

  public void deleteTiles(){
    List<Combination> chains = getAllCombinationsOnBoard();
    List<Tile> tiles = new ArrayList<Tile>();
    for(int row = SIZE-1; row >= 0; row--){
      for(int col = 0; col < SIZE; col++){

        if(containsTile(board[col][row],chains)) {

          tiles.add(board[col][row]);
          for(int i = row-1; i >= 0; i--){
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

  public void dropTiles(){
    int level = 0;
    for(int row = SIZE-1; row >= 0; row--){
      for(int col = 0; col < SIZE; col++){
        level = board[col][row].getLevel();
        if(level > 0){
          board[col][row+level] = board[col][row].clone(col,row+level);
          board[col][row+level].setLevel(0);
          board[col][row].setLevel(0);
          board[col][row].setState(Tile.State.DEFAULT);
        }
      }
    }
    for(int row = SIZE-1; row >= 0; row--){
      for(int col = 0; col < SIZE; col++){
        if(board[col][row].getState() == Tile.State.DEFAULT){
          board[col][row].setRandomTile();
        }
        if(board[col][row].remove)
          board[col][row].remove = false;
      }
    }
    boardPanel.repaint();

  }

  private boolean containsTile(Tile t, List<Combination> chains){
    for(Combination c: chains){
      if(c.getTiles().contains(t)){
        return true;
      }
    }
    return false;
  }


  /**
   * Create a board of random jewels without a sequence of 3 or more tiles with the same color.
   */
  public void generateRandomBoard(){
    board = new Tile[Board.SIZE][Board.SIZE]; 
    for(int i = 0; i < Board.SIZE; i++){
      for(int j = 0; j < Board.SIZE; j++){
        board[i][j] = new Tile(i,j);
      }

      //Redo column if a sequence has been detected
      if(hasSequence(i)){
        i--;
      }
    }
  }

  /**
   * Checks if column i that just has been added doesn't create a sequence of 3 or more colours/
   * @param i column to check for sequences.
   * @return
   */
  private boolean hasSequence(int i){
    int s = 0;
    //Find sequence in row i
    for(int j = 1; j < Board.SIZE; j++){
      if(board[i][j].equalsColor(board[i][j-1])){
        s++;
      }
      else{
        s = 0;
      }

      if(s >= 2){
        return true;
      }
    }

    //If there are only 1 or 2 rows created, don't check for horizontal sequences
    if(i <= 1) return false; 

    //Find horizonal sequences
    for(int j = 0; j < Board.SIZE; j++){
      s = 0;
      s += (board[i-1][j].equalsColor(board[i][j]) ? 1 : 0);
      s += (board[i-2][j].equalsColor(board[i][j]) ? 1 : 0);

      if(s == 2){
        System.out.println("i,j: " + i + "," + j);
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
   * @return
   */
  public Tile.State checktype(Tile t0, Tile t1) {
    Tile.State res = null;
    String c1 = Tile.colors[board[t0.getX()][t0.getY()].getIndex()];
    String c2 = Tile.colors[board[t1.getX()][t1.getY()].getIndex()];
    Tile tile = null;
    String color = null;
    //swap tiles to look in the rows where the tile will be in case it can be switched
    swapTiles(t0,t1);

    for(int i=1;i<3;i++){

      if(i==1) {tile=t0; color = c1;}
      if(i==2) {tile=t1; color = c2;}

      //check x direction
      int s = 1;
      for(int q = tile.getX()+1; q < SIZE; q++) {
        if(Tile.colors[board[q][tile.getY()].getIndex()].equals(color)) {
          s++;
          System.out.println("1e " + s);
        }
        else{break;}
      }
      for(int q = tile.getX()-1; q >= 0; q--) {
        if(Tile.colors[board[q][tile.getY()].getIndex()].equals(color)) {
          s++;
          System.out.println("2e " + s);
        }
        else{break;}
      }

      if(s==3) {res=Tile.State.NORMAL;}
      if(s==4) {res=Tile.State.FLAME;}
      if(s==5) {res=Tile.State.HYPERCUBE;}

      //check y direction
      s = 1;
      for(int q = tile.getY()+1; q < SIZE; q++) {
        if(Tile.colors[board[tile.getX()][q].getIndex()].equals(color)) {
          s++;
          System.out.println("3e " + s);
        }
        else{break;}
      }
      for(int q = tile.getY()-1; q >= 0; q--) {
        if(Tile.colors[board[tile.getX()][q].getIndex()].equals(color)) {
          s++;
          System.out.println("4e " + s);
        }
        else{break;}
      }

      if(s==3) {res=Tile.State.NORMAL;}
      if(s==4) {res=Tile.State.FLAME;}
      if(s==5) {res=Tile.State.HYPERCUBE;}
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
    for(int i = 0; i < SIZE; i++) {
      for(int j = 0; j < 7; j++) {
        t0 = board[j][i];
        t1 = board[j+1][i];
        if(!(checktype(t0,t1) == null)) {
          possiblemove = true;
        }
      }
    }

    //check combinations in y direction
    for(int i = 0; i < SIZE; i++) {
      for(int j = 0; j < 7; j++) {
        t0 = board[i][j];
        t1 = board[i][j+1];
        if(!(checktype(t0,t1) == null)) {
          possiblemove = true;
        }
      }
    }
    return possiblemove;
  }

  /**
   * Returns a list, which contains lists with 2 objects: (State,List of tiles), eg. (Tile.State.NORMAL,List(t1,t2,t3)).
   * This is a list of all the valid combinations on the board at this time.
   */
  public List<Combination> getAllCombinationsOnBoard() {
    List<Combination> allcombinations = new ArrayList<Combination>();
    Combination t = null;
    for(int i = 0; i < SIZE; i++) {			//for every tile on the board
      for(int j = 0; j < SIZE; j++) {
        t = getSingleCombinationX(board[i][j]);
        if(!t.getTiles().isEmpty()){
          if(!sameCombination(allcombinations, t)) {
            allcombinations.add(t);
          }
        }

        t = getSingleCombinationY(board[i][j]);
        if(!t.getTiles().isEmpty()){
          if(!sameCombination(allcombinations, t)){
            allcombinations.add(t);
          }
        }
      }
    }
    return allcombinations;
  }

  /**
   * @param allcombinations : All combinations already in the list
   * @param singlecombination : Combination to be compared
   * @return true if singlecombination is already in allcombinations
   */
  public boolean sameCombination(List<Combination> allcombinations, Combination singlecombination) {
    boolean same = false;

    for(Combination combi : allcombinations) {
      if(combi.getTiles().containsAll(singlecombination.getTiles())) {
        same = true;
      }
    } 
    return same;
  }

  /**
   * Sees whether 
   * @param t
   * makes a valid combination in x direction on the board,
   * @return a list with first the state of the combination, and second the list of tiles in de combi.
   */
  public Combination getSingleCombinationX(Tile t) {
    Combination combi = new Combination();
    List<Tile> tiles = new ArrayList<Tile>();

    //check x direction
    int s = 1;
    for(int q = t.getX() + 1; q < SIZE; q++) {	//check to the right
      if(board[q][t.getY()].equalsColor(t)) {
        s++;
        tiles.add(board[q][t.getY()]);
      }
      else{break;}
    }
    for(int q = t.getX()-1; q >= 0; q--) {		//check to the left
      if(board[q][t.getY()].equalsColor(t)) {
        s++;
        tiles.add(board[q][t.getY()]);
      }
      else{break;}
    }	
    if(tiles.size() < 2) {						//less than 3 in a row
      tiles.clear();
    }
    else{
      tiles.add(t);
      if(tiles.size() == 3) {
        List<Tile> l = findLTshapeX(tiles);	// check for T and L shapes
        if(l.isEmpty()) {combi.setState(Tile.State.NORMAL);}
        else{
          tiles.addAll(l);
          combi.setState(Tile.State.STAR);
        }
      }
      else if(tiles.size() == 4) {combi.setState(Tile.State.FLAME);}
      else if(tiles.size() == 5) {combi.setState(Tile.State.HYPERCUBE);}
      combi.setTiles(tiles);
    }
    return combi;
  }

  /**
   * Sees whether 
   * @param t
   * makes a valid combination in y direction on the board,
   * @return a list with first the state of the combination, and second the list of tiles in de combi.
   */
  public Combination getSingleCombinationY(Tile t) {
    Combination combi = new Combination();
    List<Tile> tiles = new ArrayList<Tile>();

    //check y direction
    int s = 1;
    for(int q = t.getY() + 1; q < SIZE; q++) {	//check down
      if(board[t.getX()][q].equalsColor(t)) {
        s++;
        tiles.add(board[t.getX()][q]);
      }
      else{break;}
    }
    for(int q = t.getY()-1; q >= 0; q--) {		//check up
      if(board[t.getX()][q].equalsColor(t)) {
        s++;
        tiles.add(board[t.getX()][q]);
      }
      else{break;}
    }	
    if(tiles.size() < 2) {						//less than 3 in a row
      tiles.clear();
    }
    else{
      tiles.add(t);
      if(tiles.size() == 3) {
        List<Tile> l = findLTshapeY(tiles);	// check for T and L shapes
        if(l.isEmpty()) {combi.setState(Tile.State.NORMAL);}
        else{
          tiles.addAll(l);
          combi.setState(Tile.State.STAR);
        }
      }
      else if(tiles.size() == 4) {combi.setState(Tile.State.FLAME);}
      else if(tiles.size() == 5) {combi.setState(Tile.State.HYPERCUBE);}
      combi.setTiles(tiles);
    }
    return combi;
  }

  /**
   * Given
   * @param tiles, three tiles of the same color in a row in x direction
   * the method looks wheter these three tiles are part of an L or T shape.
   * @return the list of tiles which are added if an L or T shape is present.
   */
  public List<Tile> findLTshapeX(List<Tile> tiles) {
    List<Tile> newtiles = new ArrayList<Tile>();
    for(Tile t : tiles) {
      if(t.getY()+1 < 8 && board[t.getX()][t.getY()+1].equalsColor(t)) {
        if(t.getY()+2 < 8 && board[t.getX()][t.getY()+2].equalsColor(t)) {		// 2 erboven
          newtiles.add(board[t.getX()][t.getY()+1]);
          newtiles.add(board[t.getX()][t.getY()+2]);
          break;
        }
        else if(t.getY()-1 >= 0 && board[t.getX()][t.getY()-1].equalsColor(t)) {		// 1 erboven, 1 beneden
          newtiles.add(board[t.getX()][t.getY()+1]);
          newtiles.add(board[t.getX()][t.getY()-1]);
          break;
        }
      }
      else if(t.getY()-2 >= 0 && board[t.getX()][t.getY()-1].equalsColor(t) && board[t.getX()][t.getY()-2].equalsColor(t)) {		// 2 beneden
        newtiles.add(board[t.getX()][t.getY()-1]);
        newtiles.add(board[t.getX()][t.getY()-2]);
        break;
      }
    }
    return newtiles;
  }

  /**
   * Given
   * @param tiles, three tiles of the same color in a row in y direction
   * the method looks wheter these three tiles are part of an L or T shape.
   * @return the list of tiles which are added if an L or T shape is present.
   */
  public List<Tile> findLTshapeY(List<Tile> tiles) {
    List<Tile> newtiles = new ArrayList<Tile>();
    for(Tile t : tiles) {
      if(t.getX()+1 < 8 && board[t.getX()+1][t.getY()].equalsColor(t)) {
        if(t.getX()+2 < 8 && board[t.getX()+2][t.getY()].equalsColor(t)) {		// 2 rechts
          newtiles.add(board[t.getX()+1][t.getY()]);
          newtiles.add(board[t.getX()+2][t.getY()]);
          break;
        }
        else if(t.getX()-1 >= 0 && board[t.getX()-1][t.getY()].equalsColor(t)) {		// 1 rechts, 1 links
          newtiles.add(board[t.getX()+1][t.getY()]);
          newtiles.add(board[t.getX()-1][t.getY()]);
          break;
        }
      }
      else if(t.getX()-2 >= 0 && board[t.getX()-1][t.getY()].equalsColor(t) && board[t.getX()-2][t.getY()].equalsColor(t)) {		// 2 links
        newtiles.add(board[t.getX()-1][t.getY()]);
        newtiles.add(board[t.getX()-2][t.getY()]);
        break;
      }
    }
    return newtiles;
  }

  /**
   * Switch tile t0 and t1 on the board.
   * @param t0 first tile to swap
   * @param t1 second tile to swap
   */
  public void swapTiles(Tile t0, Tile t1){
    Tile temp = board[t0.getX()][t0.getY()];
    board[t0.getX()][t0.getY()] = board[t1.getX()][t1.getY()];
    board[t1.getX()][t1.getY()] = temp;

    Point t = (Point) t0.getLoc().clone();
    t0.setLoc(t1.getX(),t1.getY());
    t1.setLoc(t);
  }

  /**
   * Swap two tiles if it result in a sequence of 3 of more tiles with the same color.
   */
  public boolean canSwap(){
    /*System.out.println(swapTiles.get(0).getX() + "," + swapTiles.get(0).getY());
    	System.out.println(swapTiles.get(1).getX() + "," + swapTiles.get(1).getY());*/

    Tile t0 = board[swapTiles.get(0).getX()][swapTiles.get(0).getY()];
    Tile t1 = board[swapTiles.get(1).getX()][swapTiles.get(1).getY()];

    swapTiles(t0,t1);
    Combination l1 = getSingleCombinationX(t0);
    Combination l2 = getSingleCombinationX(t1);
    Combination l3 = getSingleCombinationY(t0);
    Combination l4 = getSingleCombinationY(t1);
    swapTiles(t0,t1);

    Tile.State type = null;
    if(!l1.getTiles().isEmpty()) {
      type = l1.getState();
      System.out.println("in1");
    }
    else if(!l2.getTiles().isEmpty()) {
      type = l2.getState();
      System.out.println("in2");
    }
    else if(!l3.getTiles().isEmpty()) {
      type = l3.getState();
      System.out.println("in3");
    }
    else if(!l4.getTiles().isEmpty()) {
      type = l4.getState();
      System.out.println("in4");
    }

    //Tile.State type = checktype(t0,t1);

    if(type == null) {
      return false;
    }

    if(!isNeighbour(t0,t1)) {
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
   * @param t0
   * @param t1
   * @return
   */
  public boolean isNeighbour(Tile t0, Tile t1){
    /*System.out.println(t0.getX() + "," + t0.getY());
    	System.out.println(t1.getX() + "," + t1.getY());*/
    if(Math.abs(t0.getX() - t1.getX()) == 1 && Math.abs(t0.getY() - t1.getY()) == 0)
      return true;
    if(Math.abs(t0.getX() - t1.getX()) == 0 && Math.abs(t0.getY() - t1.getY()) == 1)
      return true;
    return false;
  }

  /**
   * Update score in the view.
   * @param type
   */
  private void updateScore(Tile.State type){
    int score = 0;
    switch(type){
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
   * Increase the levelnumber when a certain score is reached
   */
  private void updateLevel() {
    if(this.score >= 1000 && this.score < 3500) {
      panel.setLevel(2);
    }
    if(this.score >= 3500 && this.score < 5500) {
      panel.setLevel(3);
    }
    if(this.score >= 5500 && this.score < 8000) {
      panel.setLevel(4);
    }
    if(this.score >= 8000 && this.score < 11000) {
      panel.setLevel(5);
    }
  }

  /**
   * Reset game
   */
  public void Reset(){
    this.score = 0;
    swapTiles = new ArrayList<Tile>();
    generateRandomBoard();
    boardPanel.repaint();
  }

  /**
   * @return the board
   */
  public Tile[][] getBoard(){
    return this.board;
  }

  /**
   * @return the list swapTiles
   */
  public List<Tile> getSwaptiles(){
    return this.swapTiles;
  }

}