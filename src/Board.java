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
	public static final int SPACEx = 65;
	public static final int SPACEy = 65;
	private static ImageIcon boardImage  = new ImageIcon("src/img/board.png");
	private static ImageIcon focusImage = new ImageIcon("src/img/focus.png");

	private Point focus = null;
	private final JFrame frame;
	private StatusPanel panel;
	private Game game;

	/**
	 * Initialize the board and create the mouse event listeners.
	 * @param frame JFrame of the game
	 * @param panel JPanel with the labels to display the status of the game
	 */
    public Board(final JFrame frame,StatusPanel panel) {
    	this.frame = frame;
    	this.panel = panel;
    	game = new Game(this,panel);

    	setOpaque(true);
        //initBoard();
        swaptiles = new ArrayList<Tile>();
        
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
            	Point loc = getColAndRow(e.getX(),e.getY());
            	int col = loc.x, row = loc.y;

                if(!withinBoundaries(col) || !withinBoundaries(row))
                	return;
                
                game.addTile(loc);
                /*if(!swaptiles.contains(board[col][row])){
                	System.out.println("Mouse Dragged: (" + col + ", " + row + ")");
                	swaptiles.add(board[col][row]);
                	setFocus(loc);
                	if(swaptiles.size() == 2){                		
                		swap();
                	}
                }*/
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
        		/*System.out.println("Mouse Clicked: (" + col + ", " + row + ") " + Tile.colors[board[col][row].getIndex()]);
                if(!swaptiles.contains(board[col][row])){
                	//System.out.println("Mouse Clicked: (" + col + ", " + row + ")");
                	swaptiles.add(board[col][row]);
                	if(swaptiles.size() == 2){
                		swap();
                	}
                }
                System.out.println("(" + board[col][row].getX() + "," + board[col][row].getY() + ")");
                */
        	}
        	@Override
        	public void mouseReleased(MouseEvent m) {
        		game.getSwaptiles().clear();
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
    public void setFocus(Point loc){
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
     * Draw board on the screen.
     */
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	Tile[][] board = game.getBoard();
    	g.drawImage(boardImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    	int ix = LOCATION.x,iy = LOCATION.y;

        for(int i = 0,x = ix, y = iy; i < SIZE; i++){
        	x = ix;
        	for(int j = 0; j < SIZE; j++, x += SPACEx){
                g.drawImage(board[j][i].getImage(), x, y, null);
        	}
        	y += SPACEy;
        }
        
        if(focus != null){
        	g.drawImage(focusImage.getImage(), focus.x, focus.y,SPACEx,SPACEy, null);
        }
    }
}