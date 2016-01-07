import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.*;


public class Board extends JPanel implements Commons {

    private boolean ingame;
    
    private boolean UP = Navi.UP; 
    private boolean DOWN = Navi.DOWN;
    private boolean SPACE = Navi.SPACE;
    
    private Dimension d;
    private Navi navi;
    
    public static int state = ME;

    public Board() {
        d = new Dimension(BO_WI, BO_HE);
        setBackground(Color.black);
    } 
    
    public void paint(Graphics g){
        super.paint(g);

        if(state == ME){
            g.setColor(Color.yellow);
            g.fillRect(0, 0, d.width, d.height);
            if(UP == true){
                g.setColor(Color.black);
                g.fillRect(d.width / 2, d.height / 2, d.width / 6, d.height / 6);
            }
            if(DOWN == true){
                g.setColor(Color.black);
                g.fillRect(d.width / 2, d.height / 2, d.width / 6, d.height / 6);
            }
            if(SPACE == true){
                g.setColor(Color.black);
                g.fillRect(d.width / 2, d.height / 2, d.width / 6, d.height / 6);
            }
        }
        
        else if(state == PL){
            g.setColor(Color.blue);
            g.fillRect(0, 0, d.width, d.height);
        }
    }
    
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (ingame) {
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) 
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            beforeTime = System.currentTimeMillis();
        }
    }
    
    private class TAdapter extends KeyAdapter {
        
        public void keyPressed(KeyEvent e) {
            navi.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            navi.keyReleased(e);
        }
    }
}