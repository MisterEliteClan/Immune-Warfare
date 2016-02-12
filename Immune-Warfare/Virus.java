import javax.swing.ImageIcon;

public class Virus extends Sprite{
    
    private Bomb bomb;
    
    public static final String virus = "/img/virus/virus1.png";    
    
    public Virus(int x, int y){
        this.x = x;
        this.y = y;
        
        bomb = new Bomb(x, y);
        ImageIcon iib = new ImageIcon(this.getClass().getResource(virus));
        setImage(iib.getImage());
    }
    
    public void actX(int directionX){
        this.x += directionX;
    }
    
    public void actY(int directionY){
        this.y += directionY;
    }
    
    public class Bomb extends Sprite{

        private final String bomb = "/img/bomb.png";
        private boolean destroyed;

        public Bomb(int x, int y){
            setDestroyed(true);
            this.x = x;
            this.y = y;
            ImageIcon ii = new ImageIcon(this.getClass().getResource(bomb));
            setImage(ii.getImage());
        }
    
        public void setDestroyed(boolean destroyed){
            this.destroyed = destroyed;
        }
        
        public boolean isDestroyed(){
            return destroyed;
        }
    }
}