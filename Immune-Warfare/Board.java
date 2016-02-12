import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.Image;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.ImageIcon;

import java.io.*;

public class Board extends JPanel implements KeyListener, ActionListener, Commons {

    private Dimension d;
    private ArrayList viruses;
    private Player player;
    
    Timer tm = new Timer(35,this);
    
    public static boolean SPACE, UP, DOWN, LEFT, RIGHT, keyP, ESC, ENTER;
    
    public static int score,level,hp;
    public static String scoreS,levelS,hpS,scoreSave,levelSave;
    
    private int virusAmountX = 5;
    private int virusAmountY = 5;
    private int virusX = 0;
    private int virusY = TOP + 5;
    private int y2 = 0;
    private int virusAmount = 0;
    private int directionX = 1;
    private int directionY = 1;
    private int deaths = 0;
    
    public static int state = ME;
    public static int mepo = 1; 
    
    private Thread animator;
    
    private JTextField[] fields;
    
    public Board(){
        d = new Dimension(BO_WI, BO_HE);
        setBackground(Color.black);
        
        tm.start();
                
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        init();
        setDoubleBuffered(true);
    }
    
    public void addNotify(){
        super.addNotify();
        init();
    }
    
    public void init(){
        viruses = new ArrayList();
        
        ImageIcon iiv = new ImageIcon(this.getClass().getResource(virusImage));
        
        for(int i = 0; i < virusAmountY; i++){
            for(int j = 0; j < virusAmountX; j++){
                Virus virus = new Virus(virusX + VI_WI * j, virusY + VI_HE * i);
                virus.setImage(iiv.getImage());
                viruses.add(virus);
            }
        }
        
        player = new Player();
    }
    
    public void reset(){
        score = 0;
        level = 0;
        hp= 100;
        directionX = 1;
        init();
    }
    
    public void drawViruses(Graphics g){
        Iterator it = viruses.iterator();

        while (it.hasNext()) {
            Virus virus = (Virus) it.next();

            if (virus.isVisible()) {
                g.drawImage(virus.getImage(), virus.getX(), virus.getY(), this);
            }

            if (virus.isDying()) {
                virus.die();
            }
        }
    }
    
    public void drawPlayer(Graphics g) {
        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {
            player.die();
            state = PA;
        }
    }
            
    public void integersToString(){    
        Integer mIS = new Integer(score);
        scoreS = mIS.toString();
        Integer mIL = new Integer(level);
        levelS = mIL.toString();
        Integer mIHP = new Integer(hp);
        hpS = mIHP.toString();
    }

    public void readSaveFile(){
         try {
         FileReader fr = new FileReader("Save.txt");
         BufferedReader br = new BufferedReader(fr);

         String zeile1 = br.readLine();
         score = Integer.parseInt(zeile1);
         System.out.println(score);
         
         String zeile2 = br.readLine();
         level = Integer.parseInt(zeile2);
         System.out.println(level);
         
         System.out.println(level+score);
         
         br.close();
         } catch(IOException e) {
         System.out.println("Error! Please check if you have permission to read data to your storage!");
         }
    }

    public void Save(){
        try {
        FileWriter fw = new FileWriter("Save.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("" + score);       
        String n = System.getProperty("line.separator");
        bw.write(n);
        bw.write("" + level);
        
        bw.close();
        } catch(IOException e) {
         System.out.println("Error! Please check if you have permission to write data to your storage!");
        }
    }
    
    
    public void paint(Graphics g){
        super.paint(g);
        
        FontMetrics metrb = this.getFontMetrics(big);
        FontMetrics metrb2 = this.getFontMetrics(big2);
        FontMetrics metrs = this.getFontMetrics(small);
        FontMetrics metrs2 = this.getFontMetrics(small2);
        
        g.setColor(grayDark);
        g.fillRect(0, 0, d.width, d.height);

        if(state == ME){
            String menutxt = "";
            String mepo1 = "Start Game";
            String mepo2 = "Upgrade Shop";
            String mepo3 = "How to play?";
            String mepo4 = "Options";
            String mepo5 = "Exit Game";
            
            ImageIcon me_bgii = new ImageIcon(this.getClass().getResource(me_bg));
            g.drawImage(me_bgii.getImage(), 0, 0, null);
            
            if(UP == true){g.setColor(grayLight);}
            else{g.setColor(grayDark);}
            menutxt = "Λ";
            g.setFont(big2);
            g.drawString(menutxt, (BO_WI - metrb2.stringWidth(menutxt)) / 2, BO_HE / 2 -100);
            
            if(mepo == 1){menutxt = mepo5;}
            else if(mepo == 2){menutxt = mepo1;}
            else if(mepo == 3){menutxt = mepo2;}
            else if(mepo == 4){menutxt = mepo3;}
            else if(mepo == 5){menutxt = mepo4;}
            g.setColor(gray);
            g.setFont(big2);
            g.drawString(menutxt, (BO_WI - metrb2.stringWidth(menutxt)) / 2, BO_HE / 2 -50);
            
            if(mepo == 1){menutxt = ">  " + mepo1 + "  <";}
            else if(mepo == 2){menutxt = ">  " + mepo2 + "  <";}
            else if(mepo == 3){menutxt = ">  " + mepo3 + "  <";}
            else if(mepo == 4){menutxt = ">  " + mepo4 + "  <";}
            else if(mepo == 5){menutxt = ">  " + mepo5 + "  <";}
            g.setColor(grayLight);
            g.setFont(big);
            g.drawString(menutxt, (BO_WI - metrb.stringWidth(menutxt)) / 2, BO_HE / 2);
            
            if(mepo == 1){menutxt = mepo2;}
            else if(mepo == 2){menutxt = mepo3;}
            else if(mepo == 3){menutxt = mepo4;}
            else if(mepo == 4){menutxt = mepo5;}
            else if(mepo == 5){menutxt = mepo1;}
            g.setColor(gray);
            g.setFont(big2);
            g.drawString(menutxt, (BO_WI - metrb2.stringWidth(menutxt)) / 2, BO_HE / 2 + 50);
            
            if(DOWN == true){g.setColor(grayLight);}
            else{g.setColor(grayDark);}
            menutxt = "V";
            g.setFont(big2);
            g.drawString(menutxt, (BO_WI - metrb2.stringWidth(menutxt)) / 2, BO_HE / 2 + 100);
                
            menutxt = "Press ENTER to choose";
            g.setColor(gray);
            g.setFont(small);
            g.drawString(menutxt, (BO_WI - metrs.stringWidth(menutxt)) / 2, BO_HE / 2 + 150);
            
            menutxt = "Copyright by MisterEliteClan GbR.";
            g.drawString(menutxt, (BO_WI - metrs.stringWidth(menutxt)) - 10, BO_HE - 40);
        }
        
        else if(state == HT){
            String httxt;
            int line = 50;
            
            g.setColor(grayLight);
            g.setFont(big2);
            
            httxt = "Basics:";
            g.drawString(httxt, (BO_WI - metrb2.stringWidth(httxt)) / 2, line);
            line += 50;
             
            g.setColor(gray);
            g.setFont(small);
            
            httxt = "You navigate with your Arrowkeys and Enter";
            g.drawString(httxt, (BO_WI - metrs.stringWidth(httxt)) / 2, line);
            line += 50;
            
            httxt = "Ingame you will move with your Arrowkeys, shoot with Space and pause with P";
            g.drawString(httxt, (BO_WI - metrs.stringWidth(httxt)) / 2, line);
            line += 50;
            
            g.setColor(grayLight);
            g.setFont(big2);
            
            line += 50;
            httxt = "Lexicon:";
            g.drawString(httxt, (BO_WI - metrb2.stringWidth(httxt)) / 2, line);
            line += 50;
            
            g.setColor(gray);
            g.setFont(small);
            
            httxt = "This is you:";
            g.drawString(httxt, 50, line);
            
            ImageIcon plii1 = new ImageIcon(this.getClass().getResource(playerImage));
            g.drawImage(plii1.getImage(), BO_WI / 2 - 16 - 2 -32, line - 16, null);
            ImageIcon plii2 = new ImageIcon(this.getClass().getResource(playerlImage));
            g.drawImage(plii2.getImage(), BO_WI / 2 - 16, line - 16, null);
            ImageIcon plii4 = new ImageIcon(this.getClass().getResource(playerrImage));
            g.drawImage(plii4.getImage(), BO_WI / 2 - 16 + 2 + 32, line - 16, null);
            
            line += 50;
            
            httxt = "This your enemy:";
            g.drawString(httxt, 50, line);
            
            ImageIcon vii = new ImageIcon(this.getClass().getResource(virusImage));
            g.drawImage(vii.getImage(), BO_WI / 2 - 16, line - 16, null);
            
            line += 50;
            
            httxt = "press ESC to return to the menu";
            g.drawString(httxt, (BO_WI - metrs.stringWidth(httxt)) / 2, BO_HE - 50);
        }
        else if(state == OP){
            String httxt;

            int line = 50;
            
            g.setColor(grayLight);
            g.setFont(big2);
            
            httxt = "Basics:";
            g.drawString(httxt, (BO_WI - metrb2.stringWidth(httxt)) / 2, line);
            line += 50;
             
            g.setColor(gray);
            g.setFont(small);
            
            httxt = "You navigate with your Arrowkeys and Enter";
            g.drawString(httxt, (BO_WI - metrs.stringWidth(httxt)) / 2, line);
            line += 50;
            
            httxt = "Ingame you will move with your Arrowkeys, shoot with Space and pause with P";
            g.drawString(httxt, (BO_WI - metrs.stringWidth(httxt)) / 2, line);
            line += 50;
            
            g.setColor(grayLight);
            g.setFont(big2);
            
            line += 50;
            httxt = "Lexicon:";
            g.drawString(httxt, (BO_WI - metrb2.stringWidth(httxt)) / 2, line);
            line += 50;
            
            g.setColor(gray);
            g.setFont(small);
            
            httxt = "This is you:";
            g.drawString(httxt, 50, line);
            
            ImageIcon plii1 = new ImageIcon(this.getClass().getResource(playerImage));
            g.drawImage(plii1.getImage(), BO_WI / 2 - 16 - 2 -32, line - 16, null);
            ImageIcon plii2 = new ImageIcon(this.getClass().getResource(playerlImage));
            g.drawImage(plii2.getImage(), BO_WI / 2 - 16, line - 16, null);
            ImageIcon plii4 = new ImageIcon(this.getClass().getResource(playerrImage));
            g.drawImage(plii4.getImage(), BO_WI / 2 - 16 + 2 + 32, line - 16, null);
            
            line += 50;
            
            httxt = "This your enemy:";
            g.drawString(httxt, 50, line);
            
            ImageIcon vii = new ImageIcon(this.getClass().getResource(virusImage));
            g.drawImage(vii.getImage(), BO_WI / 2 - 16, line - 16, null);
            
            line += 50;
            
            httxt = "press ESC to return to the menu";
            g.drawString(httxt, (BO_WI - metrs.stringWidth(httxt)) / 2, BO_HE - 50);
        }
        
        else if(state == PL || state == PA || state == WI){
            String playtxt;
            
            //ImageIcon pl_bgii = new ImageIcon(this.getClass().getResource(sampleimg));
            //g.drawImage(pl_bgii.getImage(), 0, 0, null);
            
            g.setColor(grayLight);
            g.fillRect(0, TOP, BO_WI, 4); 
            g.fillRect(0, GROUND, BO_WI, 4);
            
            integersToString();
            
            g.setFont(small2);
            g.setColor(grayLight);
                      
            playtxt = "Level";
            g.drawString(playtxt,(BO_WI - metrs2.stringWidth(playtxt)) / 2, TOP - 25);
            g.drawString(levelS,(BO_WI - metrs2.stringWidth(levelS)) / 2, TOP - 5);
            
            playtxt = "Score";
            g.drawString(playtxt , 2, GROUND + 5 + 16);
            g.drawString(scoreS, 2, GROUND + 25 + 16);

            playtxt = "HP";
            g.drawString(playtxt,(BO_WI - metrs2.stringWidth(playtxt)) / 2, GROUND + 5 + 16); 
            g.fillRect(BO_WI / 2 - 52, GROUND + 30 - 2, 104, 14);
            g.setColor(gray);
            g.fillRect(BO_WI / 2 - 50, GROUND + 30, 100, 10);
            
            if(hp >= 75){g.setColor(green);}
            else if(hp >= 50){g.setColor(yellow);}
            else if(hp >= 25){g.setColor(orange);}
            else if(hp >= 0){g.setColor(red);}
            g.fillRect(BO_WI / 2 - 50, GROUND + 30, hp, 10);
            
            g.setColor(grayLight);
            
            playtxt = "Upgrades";
            g.drawString(playtxt,BO_WI - metrs2.stringWidth(playtxt) - 8, GROUND + 5 + 16);
            
            drawViruses(g);
            drawPlayer(g);
            
                    
            if(hp == 0){
                state = PA;
            }
           
            if(state == WI){
                Save();
                String wintext1,wintext2,wintext3;
                ImageIcon pa_bgii = new ImageIcon(this.getClass().getResource(pa_bg));
                g.drawImage(pa_bgii.getImage(), 0, 0, null);
                g.setColor(grayLight);
                g.setFont(big2);
                wintext1 = "WIN!";
                g.drawString(wintext1, (BO_WI - metrb2.stringWidth(wintext1)) / 2, BO_HE / 2);
                
                g.setColor(gray);
                g.setFont(small2);
                wintext2 = "Level: " + level;
                g.drawString(wintext2, (BO_WI - metrs2.stringWidth(wintext2)) / 2, BO_HE / 2 +100);
                wintext3 = "Score: " + score;
                g.drawString(wintext3, (BO_WI - metrs2.stringWidth(wintext3)) / 2, BO_HE / 2 +125);
            }
            
            if(state == PA){
                String pausetxt;
                ImageIcon pa_bgii = new ImageIcon(this.getClass().getResource(pa_bg));
                g.drawImage(pa_bgii.getImage(), 0, 0, null);
                g.setColor(grayLight);
                g.setFont(big2);
                pausetxt = "PAUSE";
                g.drawString(pausetxt, (BO_WI - metrb2.stringWidth(pausetxt)) / 2, BO_HE / 2);
                
                g.setColor(gray);
                g.setFont(small);
                pausetxt = "press ESC to return to the menu";
                g.drawString(pausetxt, (BO_WI - metrs.stringWidth(pausetxt)) / 2, BO_HE / 2 +50);
            }
        }
        
         
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    public void animationCycle(){
        //player
        
        player.act();
         
        //virus
        
        Iterator it1 = viruses.iterator();
        
         while (it1.hasNext()){
            Virus v1 = (Virus) it1.next();
            
            virusAmount++;
            
            int x = v1.getX();
            int y = v1.getY();
            
            if (virusAmount >= virusAmountX * virusAmountY){
                if(directionX != 0){
                    y2 = v1.getY();
                }
            
                if(x  >= BO_WI - BO_RIGHT && directionX != -1){
                    directionX = 0;
                    if(y == y2 + GO_DOWN){
                        directionX = -1;
                    }
                }
            
                if((x - (virusAmountX - 1) * VI_WI) <= BO_LEFT && directionX != 1){
                    directionX = 0;
                    if(y == y2 + GO_DOWN){
                        directionX = 1;
                    }
                }
            
                virusAmount = 0;
            }
        }
        
        Iterator it = viruses.iterator();
        
        while (it.hasNext()) {
            Virus virus = (Virus) it.next();
            if (virus.isVisible()) {

                int y = virus.getY();

                if (y > GROUND - VI_HE) {
                    state = PA;
                }

                if(directionX != 0){
                    virus.actX(directionX);
                }
                else if(directionX == 0){
                    virus.actY(directionY);
                }
            }
        }
    }
    
    public void actionPerformed(ActionEvent e){
        repaint();
        if(state == PL){animationCycle();}
    }
    
    public void keyTyped(KeyEvent e){
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        player.keyPressed(e);
        
        int x = player.getX();
        int y = player.getY();
        
        if (key == KeyEvent.VK_UP){
            UP = true;
            if(state == ME){
                mepo--;
                if(mepo == 0){
                    mepo = 5;
                }
            }
            if(state == PL){
                if(hp > 0){hp -= 10;}
            }
        }

        if (key == KeyEvent.VK_DOWN){
            DOWN = true;
            if(state == ME){
                mepo++;
                if(mepo == 6){
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
        
        if (key == KeyEvent.VK_P){
            keyP = true;
            if(state == PL){
                state = PA;
            }
            else if(state == PA){
                state = PL;
            }
        }
        
        if (key == KeyEvent.VK_W){
            state = WI;
        }
        
        if (key == KeyEvent.VK_H){
            readSaveFile();
        }
        
        if (key == KeyEvent.VK_ESCAPE){
            ESC = true;
            if(state == PA || state == WI || state == OP || state == HT){
                state = ME;
            }
            if(state == PL){
                if(hp > 0){hp -= 10;}
             }
        }
        
        if (key == KeyEvent.VK_ENTER){
            ENTER = true;
            if(state == ME && mepo == 1){
                reset();
                state = PL;
            }
            if(state == ME && mepo == 3){
                state = HT;
            }
            if(state == ME && mepo == 4){
                state = OP;
            }
            if(state == ME && mepo == 5){
                System.exit(0);
            }
        }    
        
        if(key == KeyEvent.VK_SPACE){
            if(state == PL){
                score += 10;   
                level += 1;
                if(hp < 100){hp += 10;}
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        player.keyReleased(e);

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