import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class ImmuneWarfare extends JFrame{
    
    public static final int PL = 0; //playing
    public static final int WI = 1; //win
    public static final int LO = 2; //lost
    public static final int PA = 3; //pause
    public static final int ME = 4; //menu
    public static final int DI = 5; //dialog
    
    private AntiV Player;
    private boolean left, right, up, down;
    private int state = ME;
        
    public void ImmuneWarfare(){
        //Player = new AntiV(this);
        //ImmuneWarfare IW = new ImmuneWarfare();
        
        JFrame fr = new JFrame("Immune Warefare");
        fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel pa = (JPanel)fr.getContentPane();
        
        JLabel la = new JLabel();
        la.setIcon(new ImageIcon("img/player.png"));
        pa.add(la);
        
        fr.setSize(500,700);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
        fr.setResizable(false);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ImmuneWarfare();
            }
        });
    }
    
    public void paint(Graphics g){
        //Player.display(g);
    }
}