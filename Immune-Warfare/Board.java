import java.awt.*;
import java.util.*;
import javax.swing.*;


public class Board extends JPanel implements Commons {

    private Dimension d;

    public Board() 
    {
        d = new Dimension(BO_WI, BO_HE);
        setBackground(Color.black);
    } 
    
    public void paint(Graphics g)
    {
        super.paint(g);

        g.setColor(Color.blue);
        g.fillRect(0, 0, d.width, d.height);
    }
}