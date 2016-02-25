import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends Sprite implements Commons{
    
    private final int ST_X = BO_WI / 2 - 16;
    private final int ST_Y = GROUND - 32;
    
    private boolean SPACE, LEFT, RIGHT, allow;
        
    private int width;
    
    ImageIcon ii = new ImageIcon(this.getClass().getResource(playerImage));
    ImageIcon iil = new ImageIcon(this.getClass().getResource(playerlImage));
    ImageIcon iir = new ImageIcon(this.getClass().getResource(playerrImage));
    ImageIcon iiahh = new ImageIcon(this.getClass().getResource(playerahhImage));
    ImageIcon iiahhl = new ImageIcon(this.getClass().getResource(playerahhlImage));
    ImageIcon iiahhr = new ImageIcon(this.getClass().getResource(playerahhrImage));
    
    ImageIcon iios = new ImageIcon(this.getClass().getResource(playerosImage));
    ImageIcon iilos = new ImageIcon(this.getClass().getResource(playerlosImage));
    ImageIcon iiros = new ImageIcon(this.getClass().getResource(playerrosImage));
    ImageIcon iiahhos = new ImageIcon(this.getClass().getResource(playerahhosImage));
    ImageIcon iiahhlos = new ImageIcon(this.getClass().getResource(playerahhlosImage));
    ImageIcon iiahhros = new ImageIcon(this.getClass().getResource(playerahhrosImage));
    
    public Player(){
        setX(ST_X);
        setY(ST_Y);
    }
    
    public void act(){
        allow = Board.allow;
        
        width = ii.getImage().getWidth(null);

        x += + dxl + dxr;
        
        if(x <= 2){x = 2;}
        if(x >= BO_WI - width - 8){x = BO_WI - width - 8;}
        
        if(dxl == 0 && dxr == 0){
            if(allow == false){
                setImage(iios.getImage());
            }
            else{
                setImage(ii.getImage());
            }
            if(SPACE == true){
                if(allow == false){
                    setImage(iiahhos.getImage());
                }
                else{
                    setImage(iiahh.getImage());
                }
            }
        }
        if(dxl == -2 && dxr == 0){
            if(allow == false){
                setImage(iilos.getImage());
            }
            else{
                setImage(iil.getImage());
            }
            if(SPACE == true){
                if(allow == false){
                    setImage(iiahhlos.getImage());
                }
                else{
                    setImage(iiahhl.getImage());
                }
            }
        }
        if((dxl == 0 && dxr == 2)){
            if(allow == false){
                setImage(iiros.getImage());
            }
            else{
                setImage(iir.getImage());
            }
            if(SPACE == true){
                if(allow == false){
                    setImage(iiahhros.getImage());
                }
                else{
                    setImage(iiahhr.getImage());
                }
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