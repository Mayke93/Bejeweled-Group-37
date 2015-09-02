import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Application extends JFrame {
    
	private JLabel label = new JLabel("Score: 0", JLabel.LEFT);
	private JLabel label2 = new JLabel("Level: 1", JLabel.LEFT);
	private JPanel leftPane = new JPanel();
	
    public Application() {
    	label.setForeground(Color.white);
    	label2.setForeground(Color.white);
        initUI();
        /*try {
    		setContentPane(new JLabel(new Icon(ImageIO.read(new File("test.jpg")))));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}*/
    }

    private void initUI() {
    	leftPane.setLayout(new GridBagLayout());
    	leftPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    	
    	add(leftPane, BorderLayout.WEST);
    	Box box = Box.createVerticalBox();
    	leftPane.add(box);
    	box.add(label);
    	box.add(label2);
    	Board board = new Board();
    	
    	add(board);
    	
        setSize(800, 600);
        leftPane.setBackground(Color.black);
        getContentPane().setBackground(Color.black);
        
        setTitle("Bejeweled");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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