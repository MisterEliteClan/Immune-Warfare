import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends Sprite implements Commons{
    
    private final int ST_X = BO_WI / 2;
    private final int ST_Y = BO_HE - 150 - 32;
    
    private final String player = "/img/player/player1.png";
    
    private int width;
    
    public Player(){
        ImageIcon ii = new ImageIcon(this.getClass().getResource(player));
        
        width = ii.getImage().getWidth(null);
        
        setImage(ii.getImage());
        setX(ST_X);
        setY(ST_Y);
    }
    
    public void act(){
        x += dx;
        if (x <= 2){x = 2;}
        if (x >= BO_WI - 2*width){x = BO_WI - 2*width;}
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT){dx = -2;}
        if (key == KeyEvent.VK_RIGHT){dx = 2;}
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT){dx = 0;}
        if (key == KeyEvent.VK_RIGHT){dx = 0;}
    }
}