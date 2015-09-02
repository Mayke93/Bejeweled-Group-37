import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {

	//Board size is 8x8
	public static final int SIZE = 8;
	public static final Point LOCATION = new Point(160,60);

	//All the board from the board
	private Tile[][] board;
	private List<Tile> swaptiles;
	private Point focus = null;

    public Board() {
    	this.setBackground(Color.black);
        initBoard();
        swaptiles = new ArrayList<Tile>();
        
        MouseAdapter mouseHandler;
        mouseHandler = new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
            	Point loc = getColAndRow(e.getX(),e.getY());
            	int col = loc.x, row = loc.y;

                if(!swaptiles.contains(board[col][row])){
                	System.out.println("Mouse Dragged: (" + col + ", " + row + ")");
                	System.out.println(swaptiles.size());
                	swaptiles.add(board[col][row]);
                	if(swaptiles.size() >= 2){
                		swap();
                	}
                }
            }
        };
        addMouseMotionListener(mouseHandler);
        this.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Point loc = Board.getColAndRow(e.getX(),e.getY());
        		int col = loc.x, row = loc.y;
        		System.out.println("Mouse Clicked: (" + col + ", " + row + ") " + Tile.colors[board[col][row].getIndex()]);
        		
        		setFocus(loc);
        		
                if(!swaptiles.contains(board[col][row])){
                	System.out.println("Mouse Clicked: (" + col + ", " + row + ")");
                	System.out.println(swaptiles.size());
                	swaptiles.add(board[col][row]);
                	if(swaptiles.size() >= 2){
                		swap();
                	}
                }
        	}
        });
    }
    
    private void setFocus(Point loc){
    	int x = loc.x*55+160+5;
    	int y = loc.y*55+60+5;
    	focus = new Point(x,y);
    	repaint();
    }
    
    public static Point getColAndRow(int x,int y){
    	x -= LOCATION.x;
    	y -= LOCATION.y;
        int col = x/55;
        int row = y/55;
        return new Point(col,row);
    }
    
    public void swap(){
    	System.out.println(swaptiles.get(0).getX() + "," + swaptiles.get(0).getY());
    	System.out.println(swaptiles.get(1).getX() + "," + swaptiles.get(1).getY());
    	
    	Tile t0 = board[swaptiles.get(0).getX()][swaptiles.get(0).getY()];
    	Tile t1 = board[swaptiles.get(1).getX()][swaptiles.get(1).getY()];
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
    				System.out.println(board[i][j].getIndex());
    				System.out.println(board[i-1][j].getIndex());
    				System.out.println(board[i-2][j].getIndex());
    				i--;
    				break;
    			}
    		}
    
    	}
    }

    @Override
    public void paintComponent(Graphics g) {
    	int ix = 160,iy = 60;
        int space = 55;

        for(int i = 0,x = ix, y = iy; i < SIZE; i++){
        	x = ix;
        	for(int j = 0; j < SIZE; j++, x += space){
                g.drawImage(board[j][i].getImage(), x, y, null);
        	}
        	y += space;
        }
        
        if(focus != null){
        	ImageIcon ii = new ImageIcon("src/img/focus.png");
        	System.out.println(ii.getIconWidth());
        	System.out.println(ii.getIconHeight());
        	g.drawImage(ii.getImage(), focus.x, focus.y,55,55, null);
        }
        
    }
}