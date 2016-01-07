import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.*;


public class Menu extends JPanel implements Commons {

    private Dimension d;
    private Navi navi;

    public Menu() {
        addKeyListener(new TAdapter());
        d = new Dimension(BO_WI, BO_HE);
        setBackground(Color.black);
    } 
    
    public void paint(Graphics g){
        super.paint(g);

        g.setColor(Color.yellow);
        g.fillRect(0, 0, d.width, d.height);
    }
    
    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            navi.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {

          navi.keyPressed(e);

          if (state == ME)
          {
            if (e.getKeyChar()==' ') {
                //state = PL;
            }
          }
        }
    }
}