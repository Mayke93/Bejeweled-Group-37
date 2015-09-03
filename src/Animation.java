import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Animation implements ActionListener{
	private Game game;
	private Board board;
	private Timer timer;
	private int frame;
	private Tile t0;
	private Tile t1;
	
	public Animation(Game game, Board board) {
		this.game = game;
		this.board = board;
		this.timer = new Timer(10,this);
		this.frame = 0;
		this.t0 = null;
		this.t1 = null;
	}
	
	public void swap(Tile t0, Tile t1){
		this.t0 = t0;
		this.t1 = t1;
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame++;
		int speed = 3;
        if (frame > 22) {endSwap();}
        else {
            int direction = 1;
            if (t0.getX() == t1.getX()){
                if (t0.getY() < t1.getY()) { direction = 1;}
                else { direction = -1;}
                t0.updateTranslation(0,speed*direction);
                t1.updateTranslation(0,-speed*direction);
            }
            else {                
                if (t0.getX() < t1.getX()) { direction = 1;}
                else { direction = -1;}
                t0.updateTranslation(speed*direction, 0);
                t1.updateTranslation(-speed*direction, 0);
            }
            board.repaint();
        }	
	}

	private void endSwap() {
		this.timer.stop();
		this.frame = 0;
		
		t0.resetD();
		t1.resetD();
		game.swapTiles(t0,t1);
	}
}
