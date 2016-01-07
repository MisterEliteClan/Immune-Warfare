import java.awt.event.KeyEvent;

public class Navi implements Commons{
    
    public static boolean SPACE, UP, DOWN;

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
