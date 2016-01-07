/*import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.Timer;

public class Navi extends JPanel implements KeyListener, ActionListener, Commons{
    Timer tm = new Timer(5,this);
    public static boolean SPACE, UP, DOWN;
    
    public Navi(){
    
        tm.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
    }
    
    public void actionPerformed(ActionEvent e){}
    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_UP){
            UP = true;
        }

        if (key == KeyEvent.VK_DOWN){
            DOWN = true;
        }
        
        if (key == KeyEvent.VK_SPACE){
            SPACE = true;
        }
    }

    public void keyTyped(KeyEvent e){}
    
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP){
            UP = false;
        }

        if (key == KeyEvent.VK_DOWN){
            DOWN = false;
        }
        
        if (key == KeyEvent.VK_SPACE){
            SPACE = false;
        }
    }
}
*/