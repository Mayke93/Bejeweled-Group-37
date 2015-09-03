import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {

	public static final int SIZE = 8; //Board size is 8x8
	public static final Point LOCATION = new Point(241,40);
	public static final int SPACE = 60;
	public static final int SPACEx = 65;
	public static final int SPACEy = 65;
	private static ImageIcon boardImage  = new ImageIcon("src/img/board.png");
	private static ImageIcon focusImage = new ImageIcon("src/img/focus.png");

	private Tile[][] board;
	private List<Tile> swaptiles;
	private Point focus = null;
	private final JFrame frame;
	private StatusPanel panel;
	private int score = 0;

	/**
	 * Initialize the board and create the mouse event listeners.
	 * @param frame JFrame of the game
	 * @param panel JPanel with the labels to display the status of the game
	 */
    public Board(final JFrame frame,StatusPanel panel) {
    	this.frame = frame;
    	this.panel = panel;
    	this.setBackground(Color.black);
        initBoard();
        swaptiles = new ArrayList<Tile>();
        
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
            	Point loc = getColAndRow(e.getX(),e.getY());
            	int col = loc.x, row = loc.y;

                if(!withinBoundaries(col) || !withinBoundaries(row))
                	return;
                if(!swaptiles.contains(board[col][row])){
                	System.out.println("Mouse Dragged: (" + col + ", " + row + ")");
                	swaptiles.add(board[col][row]);
                	setFocus(loc);
                	if(swaptiles.size() == 2){                		
                		swap();
                	}
                }
            }
        });
        this.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		System.out.println(frame.getSize().getWidth() + "," + frame.getSize().getHeight());
        		Point loc = getColAndRow(e.getX(),e.getY());
        		int col = loc.x, row = loc.y;
                
                if(!withinBoundaries(col) || !withinBoundaries(row))
                	return;
                setFocus(loc);
        		System.out.println("Mouse Clicked: (" + col + ", " + row + ") " + Tile.colors[board[col][row].getIndex()]);
                if(!swaptiles.contains(board[col][row])){
                	//System.out.println("Mouse Clicked: (" + col + ", " + row + ")");
                	swaptiles.add(board[col][row]);
                	if(swaptiles.size() == 2){
                		swap();
                	}
                }
                System.out.println("(" + board[col][row].getX() + "," + board[col][row].getY() + ")");
        	}
        	@Override
        	public void mouseReleased(MouseEvent m) {
        		swaptiles.clear();
            }
        });
    }
    
    /**
     * Check if index x is within the boundaries of the board.
     * @param x index to check
     * @return
     */
    private boolean withinBoundaries(int x){
    	return (x >= 0 && x < SIZE);
    }
    
    /**
     * Get the index of the focused jewel based on the coordinated of the mouse event.
     * @param loc location of the mouse event
     */
    private void setFocus(Point loc){
    	int x = loc.x * SPACEx + LOCATION.x;
    	int y = loc.y * SPACEy + LOCATION.y;
    	focus = new Point(x,y);
    	repaint();
    }
    
    /**
     * Get the col and row index based on the coordinates on the screen.
     * @param x x-coordinate of the mouse event
     * @param y y-coordinate of the mouse event
     * @return
     */
    public static Point getColAndRow(int x,int y){
        int col = (x - LOCATION.x) / SPACEx;
        int row = (y - LOCATION.y) / SPACEy;
        return new Point(col,row);
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
    	System.out.println(swaptiles.get(0).getX() + "," + swaptiles.get(0).getY());
    	System.out.println(swaptiles.get(1).getX() + "," + swaptiles.get(1).getY());
    	
    	Tile t0 = board[swaptiles.get(0).getX()][swaptiles.get(0).getY()];
    	Tile t1 = board[swaptiles.get(1).getX()][swaptiles.get(1).getY()];
    	
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

    	swaptiles.clear();
    	updateScore(type);
    	repaint();
    }
    
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
     * Initalize the board array.
     */
    public void initBoard(){
    	generateRandomBoard();
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
     * Draw board on the screen.
     */
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	setOpaque(true);
    	g.drawImage(boardImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    	int ix = LOCATION.x,iy = LOCATION.y;
        int spacex = SPACEx;
        int spacey = SPACEy;

        for(int i = 0,x = ix, y = iy; i < SIZE; i++){
        	x = ix;
        	for(int j = 0; j < SIZE; j++, x += spacex){
                g.drawImage(board[j][i].getImage(), x, y, null);
        	}
        	y += spacey;
        }
        
        if(focus != null){
        	g.drawImage(focusImage.getImage(), focus.x, focus.y,SPACEx,SPACEy, null);
        }
    }
}