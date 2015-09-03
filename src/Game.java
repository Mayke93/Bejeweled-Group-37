import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Game {
	private Tile[][] board;
	private List<Tile> swapTiles;
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
	 * Add tile to swapTiles bases on location from a mouseEvent.
	 * @param loc location of tile
	 */
	public void addTile(Point loc){
		int col = loc.x, row = loc.y;
        if(!swapTiles.contains(board[col][row])){
        	System.out.println("Mouse Dragged: (" + col + ", " + row + ")");
        	swapTiles.add(board[col][row]);
        	boardPanel.setFocus(loc);
        	if(swapTiles.size() == 2){                		
        		swap();
        	}
        }
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

    		//Dit zorgt ervoor dat er geen rijen van 3 of meer jewels zijn
    		int s = 0;
    		for(int j = 1; j < Board.SIZE; j++){
    			if(board[i][j].equals(board[i][j-1])){
    				s++;
    			}
    			else{
    				s = 0;
    			}

    			if(s >= 2){
    				i--;
    				break;
    			}
    		}

    		if(i <= 1) continue;

    		for(int j = 0; j < Board.SIZE; j++){
    			s = 0;
    			s += (board[i-1][j].equals(board[i][j]) ? 1 : 0);
    			s += (board[i-2][j].equals(board[i][j]) ? 1 : 0);

    			if(s == 2){
    				System.out.println("i,j: " + i + "," + j);
    				i--;
    				break;
    			}
    		}
    	}
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
    
/*    public List<List<Object>> getCombinations() {
    	List<List<Object>> allcombinations = new ArrayList<List<Object>>();
    	Tile t0 = null;
    	Tile t1 = null;
    	List<Object> combi = new ArrayList<Object>();
    	
    	//check combinations in x direction
    	for(int i=0;i<8;i++) {
    		for(int j=0;j<7;j++) {
    			t0 = board[j][i];
    			t1 = board[j+1][i];
    			if(swappable(t0,t1)) {
    				combi.add(getSingleCombination(t0,t1));
    			}
    		}
    	}
    	
    	//check combinations in y direction
    	for(int i=0;i<8;i++) {
    		for(int j=0;j<7;j++) {
    			t0 = board[i][j];
    			t1 = board[i][j+1];
    			if(swappable(t0,t1)) {
    				combi.add(getSingleCombination(t));
    			}
    		}
    	}
    	return allcombinations;
    }*/
    
    /**
     * Returns a list, which contains lists with 2 objects: (State,List of tiles), eg. (Tile.State.NORMAL,List(t1,t2,t3)).
     * This is a list of all the valid combinations on the board at this time.
     */
    public List<List<Object>> getAllCombinationsOnBoard() {
    	List<List<Object>> allcombinations = new ArrayList<List<Object>>();
    	
    	for(int i = 0; i < SIZE; i++) {			//for every tile on the board
    		for(int j = 0; j < SIZE; j++) {
    			if(!getSingleCombinationX(board[i][j]).isEmpty()){
    				allcombinations.add(getSingleCombinationX(board[i][j]));
    			}
    			if(!getSingleCombinationY(board[i][j]).isEmpty()){
    				allcombinations.add(getSingleCombinationY(board[i][j]));
    			}
    		}
    	}
    	return allcombinations;
    }
    
    /**
     * Sees whether 
     * @param t
     * makes a valid combination in x direction on the board,
     * @return a list with first the state of the combination, and second the list of tiles in de combi.
     */
    public List<Object> getSingleCombinationX(Tile t) {
    	List combi = new ArrayList<Object>();
    	List tiles = new ArrayList<Tile>();
  
    	//check x direction
		int s = 1;
		for(int q = t.getX() + 1; q < SIZE; q++) {	//check to the right
			if(board[q][t.getY()].equals(t)) {
				s++;
				tiles.add(board[q][t.getY()]);
			}
			else{break;}
		}
		for(int q = t.getX()-1; q >= 0; q--) {		//check to the left
			if(board[q][t.getY()].equals(t)) {
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
				if(l.isEmpty()) {combi.add(Tile.State.NORMAL);}
				else{
					tiles.addAll(l);
					combi.add(Tile.State.STAR);
				}
			}
			if(tiles.size() == 4) {combi.add(Tile.State.FLAME);}
			if(tiles.size() == 5) {combi.add(Tile.State.HYPERCUBE);}
			combi.add(tiles);
		}
    	return combi;
    }
    
    /**
     * Sees whether 
     * @param t
     * makes a valid combination in y direction on the board,
     * @return a list with first the state of the combination, and second the list of tiles in de combi.
     */
    public List<Object> getSingleCombinationY(Tile t) {
    	List combi = new ArrayList<Object>();
    	List tiles = new ArrayList<Tile>();
  
    	//check y direction
		int s = 1;
		for(int q = t.getX() + 1; q < SIZE; q++) {	//check down
			if(board[t.getX()][q].equals(t)) {
				s++;
				tiles.add(board[t.getX()][q]);
			}
			else{break;}
		}
		for(int q = t.getX()-1; q >= 0; q--) {		//check up
			if(board[t.getX()][q].equals(t)) {
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
				if(l.isEmpty()) {combi.add(Tile.State.NORMAL);}
				else{
					tiles.addAll(l);
					combi.add(Tile.State.STAR);
				}
			}
			if(tiles.size() == 4) {combi.add(Tile.State.FLAME);}
			if(tiles.size() == 5) {combi.add(Tile.State.HYPERCUBE);}
			combi.add(tiles);
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
    		if(board[t.getX()][t.getY()+1].equals(t)) {
    			if(board[t.getX()][t.getY()+2].equals(t)) {		// 2 erboven
        			newtiles.add(board[t.getX()][t.getY()+1]);
        			newtiles.add(board[t.getX()][t.getY()+2]);
        			break;
        		}
    			if(board[t.getX()][t.getY()-1].equals(t)) {		// 1 erboven, 1 beneden
        			newtiles.add(board[t.getX()][t.getY()+1]);
        			newtiles.add(board[t.getX()][t.getY()-1]);
        			break;
        		}
    		}
    		if(board[t.getX()][t.getY()-1].equals(t) && board[t.getX()][t.getY()-2].equals(t)) {		// 2 beneden
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
    		if(board[t.getX()+1][t.getY()].equals(t)) {
    			if(board[t.getX()+2][t.getY()].equals(t)) {		// 2 rechts
        			newtiles.add(board[t.getX()+1][t.getY()]);
        			newtiles.add(board[t.getX()+2][t.getY()]);
        			break;
        		}
    			if(board[t.getX()-1][t.getY()].equals(t)) {		// 1 rechts, 1 links
        			newtiles.add(board[t.getX()+1][t.getY()]);
        			newtiles.add(board[t.getX()-1][t.getY()]);
        			break;
        		}
    		}
    		if(board[t.getX()-1][t.getY()].equals(t) && board[t.getX()-2][t.getY()].equals(t)) {		// 2 links
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
    private void swapTiles(Tile t0, Tile t1){
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
    public void swap(){
    	System.out.println(swapTiles.get(0).getX() + "," + swapTiles.get(0).getY());
    	System.out.println(swapTiles.get(1).getX() + "," + swapTiles.get(1).getY());
    	
    	Tile t0 = board[swapTiles.get(0).getX()][swapTiles.get(0).getY()];
    	Tile t1 = board[swapTiles.get(1).getX()][swapTiles.get(1).getY()];
    	
    	Tile.State type = checktype(t0,t1);
    	if(type == null) {
    		return;
    	}
    	
    	//Kijk of de tiles naast elkaar zijn
    	int s = 0;
    	int d = t0.getX()-t1.getX();
    	s += (d < 0 ? -d : d); 
    	
    	d = t0.getY()-t1.getY();
    	s += (d < 0 ? -d : d);
    	if(s != 1) {
    		System.out.println("s != 1");
    		return;
    	}

    	swapTiles(t0,t1);

    	swapTiles.clear();
    	updateScore(type);
    	boardPanel.repaint();
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
     * Reset game
     */
	public void Reset(){
		this.score = 0;
		swapTiles = new ArrayList<Tile>();
		generateRandomBoard();
		boardPanel.repaint();
	}
	
	public Tile[][] getBoard(){
		return this.board;
	}
	public List<Tile> getSwaptiles(){
		return this.swapTiles;
	}
}
