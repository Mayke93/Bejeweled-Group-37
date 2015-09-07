import java.awt.Image;
import java.awt.Point;
import java.util.Random;

import javax.swing.ImageIcon;

public class Tile {

    private Random random;
    private int index;
    private ImageIcon image;
    private Point loc;
    public Point d;
    public int size;
    private int level;
    public boolean remove;

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
    	this.d = new Point(0,0);
    	this.level = 0;
    	this.remove = false;
    	this.size = 0;
	}

	public void updateTranslation(int dx, int dy){
		int sx = this.d.x + dx;
		if(sx == 64) sx = 65;
		if(sx == -64) sx = -65;
		
		int sy = this.d.y + dy;
		if(sy == 64) sy = 65;
		if(sy == -64) sy = -65;
		this.d.setLocation(sx,sy);
	}
	public void resetD(){
		this.d = new Point(0,0);
	}
	
	/**
	 * Give tile random color.
	 */
	public void setRandomTile(){ 
    	this.random = new Random();
    	this.state = State.NORMAL;
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
		return (this.index == tile.index && tile.getX() == this.getX() && tile.getY() == this.getY());
	}
	
	public boolean equalsColor(Object obj){
		if(!(obj instanceof Tile))
			return false;
		Tile tile = (Tile)obj;		
		return (this.index == tile.index);
	}
	
	public Tile clone(int i, int j){
		Tile t = new Tile(i,j);
    	t.state = State.NORMAL;
		t.index = this.index;
		t.image = new ImageIcon(paths[index]);
		return t;
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
	public int getLevel() { return level; }
	public void setLevel(int level) { this.level = level; }
	public void increaseLevel(){ this.level++; }

	public State getState(){ return this.state; }
	public void setState(State state){ this.state = state; }
	public String toString(){
		return "(" + Integer.toString(this.loc.x) + "," + Integer.toString(this.loc.y) + ") " + colors[this.index];
	}
}