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

	//Board size is 8x8
	public static final int SIZE = 8;
	public static final Point LOCATION = new Point(232,38);
	public static final int SPACE = 60;
	public static final int SPACEX = 72;
	public static final int SPACEY = 62;
	private static final String FOCUS = "src/img/focus.png";

	private Tile[][] board;
	private List<Tile> swaptiles;
	private Point focus = null;
	private final JFrame frame;

    public Board(final JFrame frame) {
    	this.frame = frame;
    	this.setBackground(Color.black);
        initBoard();
        swaptiles = new ArrayList<Tile>();
        
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
            	Point loc = getColAndRow(e.getX(),e.getY());
            	int col = loc.x, row = loc.y;

                if(!swaptiles.contains(board[col][row])){
                	System.out.println("Mouse Dragged: (" + col + ", " + row + ")");
                	System.out.println(swaptiles.size());
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
        		System.out.println("Mouse Clicked: (" + col + ", " + row + ") " + Tile.colors[board[col][row].getIndex()]);
        		
        		System.out.println();
        		setFocus(loc);
        		
                if(!swaptiles.contains(board[col][row])){
                	System.out.println("Mouse Clicked: (" + col + ", " + row + ")");
                	System.out.println(swaptiles.size());
                	swaptiles.add(board[col][row]);
                	setFocus(loc);
                	if(swaptiles.size() >= 2){
                		swap();
                	}
                }
        	}
        	@Override
        	public void mouseReleased(MouseEvent m)
            {
        		swaptiles.clear();
            }
        });
    }
    
    private void setFocus(Point loc){
    	int x = loc.x*SPACEX+LOCATION.x;
    	int y = loc.y*SPACEY+LOCATION.y;
    	focus = new Point(x,y);
    	repaint();
    }
    
    public static Point getColAndRow(int x,int y){
    	x -= LOCATION.x;
    	y -= LOCATION.y;
        int col = x/SPACEX;
        int row = y/SPACEY;
        return new Point(col,row);
    }
    
    public void swap(){
    	System.out.println(swaptiles.get(0).getX() + "," + swaptiles.get(0).getY());
    	System.out.println(swaptiles.get(1).getX() + "," + swaptiles.get(1).getY());
    	
    	Tile t0 = board[swaptiles.get(0).getX()][swaptiles.get(0).getY()];
    	Tile t1 = board[swaptiles.get(1).getX()][swaptiles.get(1).getY()];

    	int s = 0;
    	int d = t0.getX()-t1.getX();
    	s += (d < 0 ? -d : d); 
    	
    	d = t0.getY()-t1.getY();
    	s += (d < 0 ? -d : d);
    	if(s != 1) {
    		System.out.println("s != 1");
    		return;
    	}

    	Tile temp = board[swaptiles.get(0).getX()][swaptiles.get(0).getY()];
    	
    	board[swaptiles.get(0).getX()][swaptiles.get(0).getY()] = board[swaptiles.get(1).getX()][swaptiles.get(1).getY()];
    	board[swaptiles.get(1).getX()][swaptiles.get(1).getY()] = temp;

    	int x = t0.getX();
    	t0.setX(t1.getX());
    	t1.setX(x);

    	int y = t0.getY();
    	t0.setY(t1.getY());
    	t1.setY(y);

    	swaptiles.clear();
    	repaint();
    }
    
    public void initBoard(){
    	generateRandomBoard();
    }

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

    @Override
    public void paintComponent(Graphics g) {
    	setOpaque(true);
    	g.drawImage(new ImageIcon("src/img/board.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    	int ix = LOCATION.x,iy = LOCATION.y;
        int spacex = SPACEX;
        int spacey = SPACEY;

        for(int i = 0,x = ix, y = iy; i < SIZE; i++){
        	x = ix;
        	for(int j = 0; j < SIZE; j++, x += spacex){
                g.drawImage(board[j][i].getImage(), x, y, null);
        	}
        	y += spacey;
        }
        
        if(focus != null){
        	ImageIcon ii = new ImageIcon(FOCUS);
        	g.drawImage(ii.getImage(), focus.x, focus.y,SPACE,SPACE, null);
        }
    }
}