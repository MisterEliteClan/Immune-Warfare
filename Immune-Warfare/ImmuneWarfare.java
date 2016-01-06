import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class ImmuneWarfare extends JFrame{
    
    public static final int PL = 0; //playing
    public static final int WI = 1; //win
    public static final int LO = 2; //lost
    public static final int PA = 3; //pause
    public static final int ME = 4; //menu
    public static final int DI = 5; //dialog
    
    public void Game(){
        setTitle ("Immune Warefare");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,700);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}