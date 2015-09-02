import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Application extends JFrame {
	private StatusPanel sPanel;

    public Application() {
        initUI();
    }
    
    private void initUI() {
    	setLayout(new BorderLayout());

    	Board board = new Board(this);
    	board.setLayout(new BorderLayout());

    	sPanel = new StatusPanel();
    	board.add(sPanel,BorderLayout.WEST);
    	add(board);
    	
        setSize(800, 619);
        getContentPane().setBackground(Color.black);
        
        setTitle("Bejeweled");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }    
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Application ex = new Application();
                ex.setVisible(true);
            }
        });
    }
}