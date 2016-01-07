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
    
    public static boolean SPACE, UP, DOWN, LEFT, RIGHT;

    private Dimension d;
    
    public static int state = ME;
    public static int mepo = 1;
    
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
            String menutxt;
            
            g.setColor(Color.yellow);
            g.fillRect(0, 0, d.width, d.height);
            
            Font big = new Font("Helvetica", Font.BOLD, 50);
            FontMetrics metrb = this.getFontMetrics(big);
            Font small = new Font("Helvetica", Font.BOLD, 30);
            FontMetrics metrs = this.getFontMetrics(small);
            
            menutxt = "START";
            if(mepo == 1){
                g.setColor(Color.red);
                g.setFont(big);
                g.drawString(menutxt, (BO_WI - metrb.stringWidth(menutxt)) / 2, BO_HE / 2 - 100);
            }
            else if(mepo != 1){
                g.setColor(Color.black);
                g.setFont(small);
                g.drawString(menutxt, (BO_WI - metrs.stringWidth(menutxt)) / 2, BO_HE / 2 -100);
            }
            
            menutxt = "SAMPLE";
            if(mepo == 2){
                g.setColor(Color.red);
                g.setFont(big);
                g.drawString(menutxt, (BO_WI - metrb.stringWidth(menutxt)) / 2, BO_HE / 2);
            }
            else if(mepo != 2){
                g.setColor(Color.black);
                g.setFont(small);
                g.drawString(menutxt, (BO_WI - metrs.stringWidth(menutxt)) / 2, BO_HE / 2);
            }
            
            menutxt = "x.X.X__MLG.N0Sc0P3R__X.X.x";
            if(mepo == 3){
                g.setColor(Color.red);
                g.setFont(big);
                g.drawString(menutxt, (BO_WI - metrb.stringWidth(menutxt)) / 2, BO_HE / 2 + 100);
            }
            else if(mepo != 3){
                g.setColor(Color.black);
                g.setFont(small);
                g.drawString(menutxt, (BO_WI - metrs.stringWidth(menutxt)) / 2, BO_HE / 2 + 100);
            }
            
            if(SPACE == true){
                g.setColor(Color.black);
                g.fillRect(d.width / 2 - 50, d.height / 2 - 50, 100, 100);
            }
        }
        
        else if(state == PL){
            g.setColor(Color.blue);
            g.fillRect(0, 0, d.width, d.height);
        }
    }
    
    /*public void run() {
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
    }*/
    
    public void actionPerformed(ActionEvent e){
        repaint();
    }
    
        public void keyTyped(KeyEvent e){
    
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_UP){
            UP = true;
            if(state == ME){
                mepo--;
                if(mepo == 0){
                    mepo = 3;
                }
            }
        }

        if (key == KeyEvent.VK_DOWN){
            DOWN = true;
            if(state == ME){
                mepo++;
                if(mepo == 4){
                    mepo = 1;
                }
            }
        }
        
        if (key == KeyEvent.VK_LEFT){
            LEFT = true;
        }
        
        if (key == KeyEvent.VK_RIGHT){
            RIGHT = true;
        }
        
        if (key == KeyEvent.VK_SPACE){
            SPACE = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP){
            UP = false;
        }

        if (key == KeyEvent.VK_DOWN){
            DOWN = false;
        }
        
        if (key == KeyEvent.VK_LEFT){
            LEFT = false;
        }
        
        if (key == KeyEvent.VK_RIGHT){
            RIGHT = false;
        }
        
        if (key == KeyEvent.VK_SPACE){
            SPACE = false;
        }
    }
}