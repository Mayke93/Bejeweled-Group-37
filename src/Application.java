import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
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
    }
    
    private void initUI() {
    	label.setFont(new Font("Serif", Font.PLAIN,40));
    	label2.setFont(new Font("Serif", Font.PLAIN,40));
    	setLayout(new BorderLayout());
    	leftPane.setLayout(new GridBagLayout());
    	leftPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    	leftPane.setOpaque(false);
    	
    	Box box = Box.createVerticalBox();
    	box.add(label);
    	box.add(label2);
    	leftPane.add(box);

    	Board board = new Board(this);
    	box.setLocation(200,100);
    	
    	board.setLayout(new BorderLayout());
    	board.add(leftPane,BorderLayout.WEST);
    	
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