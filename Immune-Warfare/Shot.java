import javax.swing.ImageIcon;

public class Shot extends Sprite{

    private String shot = "/img/shot and bomb/shot.png";

    //public Shot() {
    //}

    public Shot(int x, int y) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(shot));
        setImage(ii.getImage());
        setX(x);
        setY(y);
    }
}