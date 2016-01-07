import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class Board extends JPanel implements KeyListener, ActionListener, Commons {

    Timer tm = new Timer(5,this);
    public static boolean SPACE, UP, DOWN;
    private boolean ingame;
    private Dimension d;
    public static int state = ME;
    
    public void actionPerformed(ActionEvent e){repaint();}
    
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
    
    public Board() {
        d = new Dimension(BO_WI, BO_HE);
        setBackground(Color.black);
        tm.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
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
}