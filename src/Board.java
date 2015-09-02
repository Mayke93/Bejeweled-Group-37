import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {

	//Board size is 8x8
	public static final int SIZE = 8;
	public static final Point LOCATION = new Point(160,60);

	//All the board from the board
	private Tile[][] board;
	private List<Tile> swaptiles;

    public Board() {
    	this.setBackground(Color.black);
        initBoard();
        swaptiles = new ArrayList<Tile>();
        
        MouseAdapter mouseHandler;
        mouseHandler = new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
            	if(swaptiles.size() == 2){
            		swaptiles.clear();
            	}
            	Point loc = getColAndRow(e.getX(),e.getY());
            	int col = loc.x, row = loc.y;

                System.out.println("Mouse Dragged: (" + col + ", " + row + ")");
                System.out.println(swaptiles.size());
                if(!swaptiles.contains(board[col][row])){
                	swaptiles.add(board[col][row]);
                	if(swaptiles.size() == 2){
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
        	}
        });
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
    	Tile temp = board[swaptiles.get(0).getX()][swaptiles.get(0).getY()];
    	board[swaptiles.get(0).getX()][swaptiles.get(0).getY()] = board[swaptiles.get(1).getX()][swaptiles.get(1).getY()];
    	board[swaptiles.get(1).getX()][swaptiles.get(1).getY()] = temp;
    	repaint();
    }
    
    public void initBoard(){
    	generateRandomBoard();
    }
    
    public void mousePressed(MouseEvent e) {
    	System.out.println("mousePressed");
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
    }
}