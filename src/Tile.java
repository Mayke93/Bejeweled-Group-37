import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Tile {

    private Random random;
    private int index;
    private ImageIcon image;
    private int i,j;

	private static final String[] paths = {"src/img/gemBlue.png",
							  			   "src/img/gemGreen.png",
							  			   "src/img/gemOrange.png",
							  			   "src/img/gemPurple.png",
							  			   "src/img/gemRed.png",
							  			   "src/img/gemWhite.png",
							  			   "src/img/gemYellow.png"};
	
	public Tile(int i, int j){
    	setRandomTile();
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
}