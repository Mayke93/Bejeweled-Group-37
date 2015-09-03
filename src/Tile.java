import java.awt.Image;
import java.awt.Point;
import java.util.Random;

import javax.swing.ImageIcon;

public class Tile {

    private Random random;
    private int index;
    private ImageIcon image;
    private Point loc;

    public static enum State{
    	DEFAULT,NORMAL,FLAME,HYPERCUBE,STAR;
    }
    private State state;

	private static final String[] paths = {"src/img/gemBlue.png", "src/img/gemGreen.png",
							  			   "src/img/gemOrange.png", "src/img/gemPurple.png",
							  			   "src/img/gemRed.png", "src/img/gemWhite.png",
							  			   "src/img/gemYellow.png"};
	public static final String[] colors = {"Blue", "Green", "Orange", "Purple",
							  			   "Red", "White", "Yellow"};

	
	/**
	 * Initalize tile with location and a random color and set state to NORMAL.
	 * @param i location of Tile on the board
	 * @param j location of Tile on the board
	 */
	public Tile(int i, int j){
    	setRandomTile();
    	this.state = State.NORMAL;
    	this.loc = new Point(i,j);
	}
	
	/**
	 * Give tile random color.
	 */
	public void setRandomTile(){ 
    	this.random = new Random();
		this.index = random.nextInt(paths.length);
		this.image = new ImageIcon(paths[index]);
	}
	
	public Image getImage(){
		return image.getImage();
	}
	
	public int getIndex(){
		return this.index;
	}
	
	public boolean equals(Object obj){
		if(!(obj instanceof Tile))
			return false;
		Tile tile = (Tile)obj;
		return (this.index == tile.index);
	}
	
	public int getX(){ return this.loc.x; }
	public int getY(){ return this.loc.y; }
	public Point getLoc(){ return this.loc;}
	public void setLoc(Point loc){
		this.loc = loc;
	}
	public void setLoc(int x, int y){
		this.loc = new Point(x,y);
	}

	public State getState(){ return this.state; }
	public void setState(State state){ this.state = state; }
}