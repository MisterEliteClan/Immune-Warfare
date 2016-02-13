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
    private Shot shot;
    
    private int tmV = 15;
    Timer tm = new Timer(tmV,this);
    
    public static boolean SPACE, UP, DOWN, LEFT, RIGHT, keyP, ESC, ENTER;
    
    public static int score,level,hp;
    public static String scoreS,levelS,hpS,scoreSave,levelSave, Username;
    
    private boolean saveFile1 = false,saveFile2 = false,saveFile3 = false;
    
    private int virusAmountX = 3;
    private int virusAmountY = 2;
    private int virusX = 0;
    private int virusY = TOP + 5;
    private int directionX = 1;
    private int directionY = 1;
    
    //placeholder
    private int dY, dY2;
    private int csf;
    
    private int deaths;
    
    public static int state = CS;
    public static int mepo = 1;
    public static int oppo = 1; 
    public static int cspo = 1;
    
    private Thread animator;
    
    public Board(){
        d = new Dimension(BO_WI, BO_HE);
        setBackground(Color.black);
        
        tm.start();
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        readSaveFile();
        writeSaveFile();
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
        shot = new Shot();
    }
    
    public void reset(){
        score = 0;
        level = 1;
        hp= 100;
        directionX = 1;
        deaths = 0;
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
            state = LO;
        }
    }
    
    public void drawShot(Graphics g) {
        if (shot.isVisible()){
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }
    
    public void drawBomb(Graphics g){
        Iterator i3 = viruses.iterator();
        
        while(i3.hasNext()){
            Virus a = (Virus) i3.next();
            Virus.Bomb b = a.getBomb();
            
            if(!b.isDestroyed()){
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
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
             if(csf == 0){
             FileReader fr = new FileReader("Save" + csf +".txt");
             BufferedReader br = new BufferedReader(fr);
             saveFile1 = Boolean.parseBoolean(br.readLine());
             saveFile2 = Boolean.parseBoolean(br.readLine());
             saveFile3 = Boolean.parseBoolean(br.readLine());
             System.out.println(saveFile1 + " " + saveFile2 + " " + saveFile3);
             br.close();
            }
            else if(csf == 1){
             FileReader fr = new FileReader("Save" + csf +".txt");
             BufferedReader br = new BufferedReader(fr);
             level = Integer.parseInt(br.readLine());
             score = Integer.parseInt(br.readLine());
             Username = br.readLine();
             br.close();
            }
            else if(csf == 2){
             FileReader fr = new FileReader("Save" + csf +".txt");
             BufferedReader br = new BufferedReader(fr);
             level = Integer.parseInt(br.readLine());
             score = Integer.parseInt(br.readLine());
             Username = br.readLine();
             br.close();
            }
            else if(csf == 3){
             FileReader fr = new FileReader("Save" + csf +".txt");
             BufferedReader br = new BufferedReader(fr);
             level = Integer.parseInt(br.readLine());
             score = Integer.parseInt(br.readLine());
             Username = br.readLine();
             br.close();
            }
         } catch(IOException e) {
             if(csf != 0){System.out.println("Error! Please check if you have permission to read data to your storage!");}
         }
    }

    public void writeSaveFile(){
        try {
            String n = System.getProperty("line.separator");
            if(csf == 0){
            FileWriter fw = new FileWriter("save"+ csf +".txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("" + saveFile1);       
            bw.write(n);
            bw.write("" + saveFile2);
            bw.write(n);
            bw.write("" + saveFile3);
            bw.close();
            }
            else if(csf == 1){
            FileWriter fw = new FileWriter("save"+ csf +".txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("" + level);       
            bw.write(n);
            bw.write("" + score);
            bw.write(n);
            bw.write("" + Username);
            bw.close();
            saveFile1 = true;
            FileWriter fw2 = new FileWriter("save0.txt");
            BufferedWriter bw2 = new BufferedWriter(fw2);
            bw2.write("" + saveFile1);     
            bw2.write(n);
            bw2.write("" + saveFile2);
            bw2.write(n);
            bw2.write("" + saveFile3);
            bw2.close();
            }
            else if(csf == 2){
            FileWriter fw = new FileWriter("save"+ csf +".txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("" + level);       
            bw.write(n);
            bw.write("" + score);
            bw.write(n);
            bw.write("" + Username);
            bw.close();
            saveFile2 = true;
            FileWriter fw2 = new FileWriter("save0.txt");
            BufferedWriter bw2 = new BufferedWriter(fw2);
            bw2.write("" + saveFile1);
            bw2.write(n);
            bw2.write("" + saveFile2);
            bw2.write(n);
            bw2.write("" + saveFile3);
            bw2.close();
            }
            else if(csf == 3){
            FileWriter fw = new FileWriter("save"+ csf +".txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("" + level);       
            bw.write(n);
            bw.write("" + score);
            bw.write(n);
            bw.write("" + Username);
            bw.close();
            saveFile3 = true;
            FileWriter fw2 = new FileWriter("save0.txt");
            BufferedWriter bw2 = new BufferedWriter(fw2);
            bw2.write("" + saveFile1);    
            bw2.write(n);
            bw2.write("" + saveFile2);
            bw2.write(n);
            bw2.write("" + saveFile3);
            bw2.close();
            }
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
        FontMetrics metrm = this.getFontMetrics(middle);
        FontMetrics metrm2 = this.getFontMetrics(middle2);
        
        g.setColor(grayDark);
        g.fillRect(0, 0, d.width, d.height);
        
        if(state == CS){
            String cstxt;
            
            int line = BO_HE / 2 - 100;
            if(saveFile1 == true){
            cstxt = "Save avaible!";
            }else{
            cstxt = "No save avaible!";
            }
            g.setFont(big);
            if(cspo == 1){g.setColor(grayLight);}else{g.setColor(gray);}
            g.drawString(cstxt, (BO_WI - metrb.stringWidth(cstxt)) / 2, line);
            
            line += 100;
            if(saveFile2 == true){
            cstxt = "Save avaible!";
            }else{
            cstxt = "No save avaible!";
            }
            g.setFont(big);
            if(cspo == 2){g.setColor(grayLight);}else{g.setColor(gray);}
            g.drawString(cstxt, (BO_WI - metrb.stringWidth(cstxt)) / 2, line);
            line += 100;
            if(saveFile3 == true){
            cstxt = "Save avaible!";
            }else{
            cstxt = "No save avaible!";
            }
            g.setFont(big);
            if(cspo == 3){g.setColor(grayLight);}else{g.setColor(gray);}
            g.drawString(cstxt, (BO_WI - metrb.stringWidth(cstxt)) / 2, line);
        }

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
        
        if(state == HT){
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
        
        if(state == OP){
            String optionstxt = "";
            String oppo1 = "Easy Mode";
            String oppo2 = "MLG X-Tr3m3 T3RR0R S3TT1NG5";
            String oppo3 = "M8";
            String oppo4 = "J01N F4C3-CL4N";
            String oppo5 = "Kill yourself!";
            
            
           
            g.setColor(grayLight);
            g.setFont(big2);
            optionstxt = "Options";
            g.drawString(optionstxt, (BO_WI - metrb2.stringWidth(optionstxt)) / 2, BO_HE/2 -150);
            
            if(UP == true){g.setColor(grayLight);}
            else{g.setColor(grayDark);}
            optionstxt = "Λ";
            g.setFont(middle2);
            g.drawString(optionstxt, (BO_WI - metrm2.stringWidth(optionstxt)) / 2, BO_HE / 2 -100);
            
            if(oppo == 1){optionstxt = oppo5;}
            else if(oppo == 2){optionstxt = oppo1;}
            else if(oppo == 3){optionstxt = oppo2;}
            else if(oppo == 4){optionstxt = oppo3;}
            else if(oppo == 5){optionstxt = oppo4;}
            g.setColor(gray);
            g.setFont(middle);
            g.drawString(optionstxt, (BO_WI - metrm.stringWidth(optionstxt)) / 2, BO_HE / 2 -50);
            
            if(oppo == 1){optionstxt = ">  " + oppo1 + "  <";}
            else if(oppo == 2){optionstxt = ">  " + oppo2 + "  <";}
            else if(oppo == 3){optionstxt = ">  " + oppo3 + "  <";}
            else if(oppo == 4){optionstxt = ">  " + oppo4 + "  <";}
            else if(oppo == 5){optionstxt = ">  " + oppo5 + "  <";}
            g.setColor(grayLight);
            g.setFont(middle2);
            g.drawString(optionstxt, (BO_WI - metrm2.stringWidth(optionstxt)) / 2, BO_HE / 2);
            
            if(oppo == 1){optionstxt = oppo2;}
            else if(oppo == 2){optionstxt = oppo3;}
            else if(oppo == 3){optionstxt = oppo4;}
            else if(oppo == 4){optionstxt = oppo5;}
            else if(oppo == 5){optionstxt = oppo1;}
            g.setColor(gray);
            g.setFont(middle);
            g.drawString(optionstxt, (BO_WI - metrm.stringWidth(optionstxt)) / 2, BO_HE / 2 + 50);
            
            if(DOWN == true){g.setColor(grayLight);}
            else{g.setColor(grayDark);}
            optionstxt = "V";
            g.setFont(middle2);
            g.drawString(optionstxt, (BO_WI - metrm2.stringWidth(optionstxt)) / 2, BO_HE / 2 + 100);
                
            optionstxt = "Press ENTER to choose";
            g.setColor(gray);
            g.setFont(small2);
            g.drawString(optionstxt, (BO_WI - metrs2.stringWidth(optionstxt)) / 2, BO_HE / 2 + 150);
            
            optionstxt = "press ESC to return to the menu";
            g.drawString(optionstxt, (BO_WI - metrs2.stringWidth(optionstxt)) / 2, BO_HE - 50);
        }
        
        if(state == PL || state == PA || state == LO || state == WI){

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
            
            playtxt = "Upgrades";
            g.drawString(playtxt,BO_WI - metrs2.stringWidth(playtxt) - 8, GROUND + 5 + 16);
            
            g.fillRect(BO_WI / 2 - 52, GROUND + 30 - 2, 104, 14);
            g.setColor(gray);
            g.fillRect(BO_WI / 2 - 50, GROUND + 30, 100, 10);

            if(hp >= 75){g.setColor(green);}
            else if(hp >= 50){g.setColor(yellow);}
            else if(hp >= 25){g.setColor(orange);}
            else if(hp >= 0){g.setColor(red);}
            g.fillRect(BO_WI / 2 - 50, GROUND + 30, hp, 10);
            
            g.setColor(grayLight); 

            drawBomb(g);
            drawShot(g);
            drawViruses(g);
            drawPlayer(g);            
           
            if(hp == 0){state = LO;}

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
                
            if(state == WI){
                writeSaveFile();
                String wintxt;
                ImageIcon pa_bgii = new ImageIcon(this.getClass().getResource(pa_bg));
                g.drawImage(pa_bgii.getImage(), 0, 0, null);
                g.setColor(grayLight);
                g.setFont(big2);
                
                wintxt = "WIN!";
                g.drawString(wintxt, (BO_WI - metrb2.stringWidth(wintxt)) / 2, BO_HE / 2);
                
                g.setColor(gray);
                g.setFont(small);
                wintxt = "Level: " + level;
                g.drawString(wintxt, (BO_WI - metrs.stringWidth(wintxt)) / 2, BO_HE / 2 +50);
                wintxt = "Score: " + score;
                g.drawString(wintxt, (BO_WI - metrs.stringWidth(wintxt)) / 2, BO_HE / 2 +150);
                wintxt = "press ESC to return to the menu";
                g.drawString(wintxt, (BO_WI - metrs.stringWidth(wintxt)) / 2, BO_HE / 2 +200);
            }
            
            if(state == LO){
                String losttxt;
                ImageIcon pa_bgii = new ImageIcon(this.getClass().getResource(pa_bg));
                g.drawImage(pa_bgii.getImage(), 0, 0, null);
                g.setColor(grayLight);
                g.setFont(big2);
                
                losttxt = "You Lose";
                g.drawString(losttxt, (BO_WI - metrb2.stringWidth(losttxt)) / 2, BO_HE / 2);
                
                g.setColor(gray);
                g.setFont(small);
                losttxt = "press ESC to return to the menu";
                g.drawString(losttxt, (BO_WI - metrs.stringWidth(losttxt)) / 2, BO_HE / 2 +50);
            }
        }
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void animationCycle(){
        //player
        
        player.act();
        
        if(deaths == virusAmountY * virusAmountX){
            state = WI;
        }
        
        //shot
        
        if(shot.isVisible()){
            Iterator it = viruses.iterator();
            int shotX = shot.getX() + SH_WI / 2;
            int shotY = shot.getY() + SH_HE / 2;

            while(it.hasNext()){
                Virus virus = (Virus) it.next();
                int virusX = virus.getX() + VI_WI / 2;
                int virusY = virus.getY() + VI_HE / 2;

                if(virus.isVisible() && shot.isVisible()){
                    
                    //virus hitbox
                    
                    if (shotX + SH_SPACE_SIDE >= virusX 
                    && shotX - SH_SPACE_SIDE <= virusX 
                    && shotY + SH_HE / 2 + SH_SPACE_TOP >= virusY 
                    && shotY <= virusY){
                            ImageIcon ii = new ImageIcon(getClass().getResource(virusImage));
                            virus.setImage(ii.getImage());
                            virus.setDying(true);
                            deaths++;
                            shot.die();
                    }
                }
            }
            
            int y = shot.getY();
            y -= 4;
            if (y < TOP - SH_HE + SH_SPACE_TOP + 4)
                shot.die();
            else shot.setY(y);
        }
        
        //virus
        
        Iterator it1 = viruses.iterator();
        
         while (it1.hasNext()){
            Virus v1 = (Virus) it1.next();

            int x = v1.getX();
            int y = v1.getY();
            
            if(x  >= BO_WI - BO_RIGHT && directionX != -1){
                directionX = 0;
                if(dY >= GO_DOWN){
                    directionX = -1;
                    dY = 0;
                }
            }
            
            if(x <= BO_LEFT && directionX != 1){             
                directionX = 0;
                if(dY >= GO_DOWN){
                    directionX = 1;
                    dY = 0;
                }
            }
        }
        
        Iterator it = viruses.iterator();
        
        while (it.hasNext()) {
            Virus virus = (Virus) it.next();
            if (virus.isVisible()) {
                int y = virus.getY();
                if (y > GROUND - VI_HE) {
                    state = LO;
                }
                if(directionX != 0){
                    virus.actX(directionX);
                }
                if(directionX == 0){
                    virus.actY(directionY);
                    dY2++;
                    if(dY2 >= virusAmountY * virusAmountX - deaths){
                        dY += directionY;
                        dY2 = 0;
                    }
                }
            }
            
            System.out.println("dY: " + dY + ", dY2: " + dY2);
        }
        
        //Bombs
        
        Iterator i3 = viruses.iterator();
        Random generator = new Random();

        while(i3.hasNext()){
            int shot = generator.nextInt(15);
            Virus a = (Virus) i3.next();
            Virus.Bomb b = a.getBomb();
            
            if(shot == CHANCE && a.isVisible() && b.isDestroyed()) {
                b.setDestroyed(false);
                b.setX(a.getX());
                b.setY(a.getY());
            }

            int bombX = b.getX() + BOMB_WI / 2;
            int bombY = b.getY() + BOMB_HE / 2;
            int playerX = player.getX() + PL_WI / 2;
            int playerY = player.getY() + PL_HE / 2;

            if(player.isVisible() && !b.isDestroyed()) { 
                
                //player hitbox
                
                if(bombX + BOMB_WI / 2 +  BOMB_SPACE_SIDE>= (playerX) 
                && bombX - BOMB_WI / 2 -  BOMB_SPACE_SIDE <= (playerX) 
                && bombY + BOMB_HE >= (playerY + BOMB_SPACE_TOP) 
                && bombY <= (playerY) ){
                    hp -= 10;
                    b.setDestroyed(true);;
                }
            }
            
            if (!b.isDestroyed()) {
                b.setY(b.getY() + 1);
                if (!b.isDestroyed()) {
                    b.setY(b.getY() + 1);
                    if (b.getY() >= GROUND - BOMB_HE){
                         b.setDestroyed(true);
                    }
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
            if(state == CS){
                cspo--;
                if(cspo == 0){
                    cspo = 3;
                }
            }
            if(state == ME){
                mepo--;
                if(mepo == 0){
                    mepo = 5;
                }
            }
            if(state == OP){
                oppo--;
                if(oppo == 0){
                    oppo = 5;
                }
            }
            if(state == PL){
                if(hp > 0){hp -= 10;}
            }
        }

        if (key == KeyEvent.VK_DOWN){
            DOWN = true;
            if(state == CS){
                cspo++;
                if(cspo == 4){
                    cspo = 1;
                }
            }
            if(state == ME){
                mepo++;
                if(mepo == 6){
                    mepo = 1;
                }
            }
            if(state == OP){
                oppo++;
                if(oppo == 6){
                    oppo = 1;
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

            if(state == PA || state == WI || state == LO || state == OP || state == HT){
                state = ME;
                oppo = 1;
            }
            if(state == PL){
                if(hp > 0){hp -= 10;}
             }
        }
        
        if (key == KeyEvent.VK_ENTER){
            ENTER = true;
            if(state == ME && mepo == 1){
                reset();
                //readSaveFile();

                state = PL;
            }
            if(state == ME && mepo == 3){
                state = HT;
            }
            if(state == ME && mepo == 4){
                state = OP;
                oppo=1;
            }
            if(state == ME && mepo == 5){
                System.exit(0);
            }
            if(state == OP && oppo == 4){
                Username = JOptionPane.showInputDialog("Please enter your username:");
                System.out.println(Username); 
                writeSaveFile();
            }
            if(state == CS && cspo == 1){
                csf = 1;
                state = ME;
            }
            if(state == CS && cspo == 2){
                csf = 2;
                state = ME;
            }
            if(state == CS && cspo == 3){
                csf = 3;
                state = ME;
            }
        }    
        
        if(key == KeyEvent.VK_SPACE){
            if(state == PL){
                if(!shot.isVisible()){
                    shot = new Shot(x, y);
                }
                level++;
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