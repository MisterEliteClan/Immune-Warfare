import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;

import javax.swing.*;
import javax.swing.Timer;

public class Board extends JPanel implements KeyListener, ActionListener, Commons {

    private Dimension d;
    
    public static boolean SPACE, UP, DOWN, LEFT, RIGHT, keyP, ESC;
    
    Timer tm = new Timer(5,this);
    
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
        
        Font big = new Font("Helvetica", Font.BOLD, 50);
        FontMetrics metrb = this.getFontMetrics(big);
        Font big2 = new Font("Comic Sans", Font.BOLD, 30);
        FontMetrics metrb2 = this.getFontMetrics(big2);
        Font small = new Font("Comic Sans", Font.BOLD, 10);
        FontMetrics metrs = this.getFontMetrics(small);

        if(state == ME){
            String menutxt;
            
            g.setColor(grayDark);
            g.fillRect(0, 0, d.width, d.height);
            
            menutxt = "START";
            if(mepo == 1){
                g.setColor(grayLight);
                g.setFont(big);
                g.drawString(menutxt, (BO_WI - metrb.stringWidth(menutxt)) / 2, BO_HE / 2 - 100);
            }
            else if(mepo != 1){
                g.setColor(gray);
                g.setFont(big2);
                g.drawString(menutxt, (BO_WI - metrb2.stringWidth(menutxt)) / 2, BO_HE / 2 -100);
            }
            
            menutxt = "SAMPLE TEXT";
            if(mepo == 2){
                g.setColor(grayLight);
                g.setFont(big);
                g.drawString(menutxt, (BO_WI - metrb.stringWidth(menutxt)) / 2, BO_HE / 2);
            }
            else if(mepo != 2){
                g.setColor(gray);
                g.setFont(big2);
                g.drawString(menutxt, (BO_WI - metrb2.stringWidth(menutxt)) / 2, BO_HE / 2);
            }
            
            menutxt = "TOM STINKT";
            if(mepo == 3){
                g.setColor(grayLight);
                g.setFont(big);
                g.drawString(menutxt, (BO_WI - metrb.stringWidth(menutxt)) / 2, BO_HE / 2 + 100);
            }
            else if(mepo != 3){
                g.setColor(gray);
                g.setFont(big2);
                g.drawString(menutxt, (BO_WI - metrb2.stringWidth(menutxt)) / 2, BO_HE / 2 + 100);
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
        
        else if(state == PA){
            String pausetxt;
            g.setColor(Color.black);
            g.fillRect(0, 0, d.width, d.height);
            g.setColor(Color.white);
            g.setFont(big2);
            pausetxt = "PAUSE";
            g.drawString(pausetxt, (BO_WI - metrb2.stringWidth(pausetxt)) / 2, BO_HE / 2);
            g.setFont(small);
            pausetxt = "press ESC to return to the menu";
            g.drawString(pausetxt, (BO_WI - metrs.stringWidth(pausetxt)) / 2, BO_HE / 2 + 50);
        }
    } 
      
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
            if(state == ME && mepo == 1){
                state = PL;
            }
        }
        
        if (key == KeyEvent.VK_P){
            keyP = true;
            if(state == PL){
                state = PA;
            }
            else if(state == PA){
                state = PL;
            }
        }
        
        if (key == KeyEvent.VK_ESCAPE){
            ESC = true;
            if(state == PA){
                state = ME;
            }
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