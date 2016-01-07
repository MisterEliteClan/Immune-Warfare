import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class ImmuneWarfare extends JFrame implements Commons{

    public void ImmuneWarfare(){
        if (state == ME){add(new Menu());}
        else if(state == PL){add(new Board());}
        
        setTitle("Immune Warefare");
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
        setSize(BO_WI,BO_HE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    
    public static void main(String[] args) {
        new ImmuneWarfare();
    }
}