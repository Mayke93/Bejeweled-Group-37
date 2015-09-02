import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Tile {

    private Random random;
    private int index;
    private ImageIcon image;
    private int i,j;

    public static enum State{
    	NORMAL,FLAME,HYPERCUBE,STAR;
    }
    private State state;

	private static final String[] paths = {"src/img/gemBlue.png", "src/img/gemGreen.png",
							  			   "src/img/gemOrange.png", "src/img/gemPurple.png",
							  			   "src/img/gemRed.png", "src/img/gemWhite.png",
							  			   "src/img/gemYellow.png"};
	public static final String[] colors = {"Blue", "Green", "Orange", "Purple",
							  			   "Red", "White", "Yellow"};

	
	public Tile(int i, int j){
    	setRandomTile();
    	state = State.NORMAL;
    	this.i = i;
    	this.j = j;
	}
	
	public void setRandomTile(){ 
    	random = new Random();
		index = random.nextInt(paths.length);
		image = new ImageIcon(paths[index]);
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
	
	public int getX(){ return this.i; }
	public void setX(int x){ this.i = x; }
	public int getY(){ return this.j; }
	public void setY(int y){ this.j = y; }
	public State getState(){ return this.state; }
	public void setState(State state){ this.state = state; }
}