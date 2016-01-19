import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends Sprite implements Commons{
    
    private final int ST_X = BO_WI / 2 - 16;
    private final int ST_Y = GROUND - 32;
    
    private boolean SPACE, LEFT, RIGHT;
    
    private final String player = "/img/player/player1.png";
    private final String playerl = "/img/player/player1l.png";
    private final String playerr = "/img/player/player1r.png";
    private final String playerahh = "/img/player/player1ahh.png";
    private final String playerahhl = "/img/player/player1ahhl.png";
    private final String playerahhr = "/img/player/player1ahhr.png";
    
    private int width;
    
    ImageIcon ii = new ImageIcon(this.getClass().getResource(player));
    ImageIcon iil = new ImageIcon(this.getClass().getResource(playerl));
    ImageIcon iir = new ImageIcon(this.getClass().getResource(playerr));
    ImageIcon iiahh = new ImageIcon(this.getClass().getResource(playerahh));
    ImageIcon iiahhl = new ImageIcon(this.getClass().getResource(playerahhl));
    ImageIcon iiahhr = new ImageIcon(this.getClass().getResource(playerahhr));
    
    public Player(){
        setX(ST_X);
        setY(ST_Y);
    }
    
    public void act(){
        width = ii.getImage().getWidth(null);

        x += + dxl + dxr;
        
        if(x <= 2){x = 2;}
        if(x >= BO_WI - width - 8){x = BO_WI - width - 8;}
        
        if(dxl == 0 && dxr == 0){
            setImage(ii.getImage());
            if(SPACE == true){
                setImage(iiahh.getImage());
            }
        }
        else if(dxl == -2 && dxr == 0){
            setImage(iil.getImage());
            if(SPACE == true){
                setImage(iiahhl.getImage());
            }
        }
        else if((dxl == 0 && dxr == 2)){
            setImage(iir.getImage());
            if(SPACE == true){
                setImage(iiahhr.getImage());
            }
        }
        
        
        
        if(RIGHT == true){dxr = 2;}
        else{dxr = 0;}
        
        if(LEFT == true){dxl = -2;}
        else{dxl = 0;}
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT){LEFT = true;}
        if(key == KeyEvent.VK_RIGHT){RIGHT = true;}
        if(key == KeyEvent.VK_SPACE){SPACE = true;}
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT){LEFT = false;}
        if(key == KeyEvent.VK_RIGHT){RIGHT = false;}
        if(key == KeyEvent.VK_SPACE){SPACE = false;}
    }
}