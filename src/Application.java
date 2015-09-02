import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class Application extends JFrame {
    
    public Application() {
    	JLabel f = new JLabel();
    	//f.mouseDrag(I, x, y)
    	f.setBackground(Color.black);
    	
        initUI();
        /*try {
    		setContentPane(new JLabel(new Icon(ImageIO.read(new File("test.jpg")))));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}*/
    }

    private void initUI() {
        add(new Board());
        setSize(800, 600);
        this.setBackground(Color.black);
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