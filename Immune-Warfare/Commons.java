import java.awt.*;
import java.awt.Font;
import java.awt.FontMetrics;

public interface Commons{
    //board
    public static final int BO_WI = 500; //board width
    public static final int BO_HE = 700; //board height
    public static final int DELAY = 17;
    
    //player
    public static final int PL_WI = 32; //player width
    public static final int PL_HE = 32; //player height
    
    public static final int GROUND = BO_HE - 80;
    public static final int TOP = 40;
    
    //virus  
    public static final int VI_WI = 32; //virus width
    public static final int VI_HE = 32; //virus height
    
    public static final int BO_RIGHT = 40; //border right
    public static final int BO_LEFT = 2; // border left
    public static final int GO_DOWN = 32; //viruses go down
    
    public static final String alien = "";
    
    public static final int CHANCE = 5; //chance of shot
    
    //bomb
    public static final int BOMB_WI = 32; //bomb width
    public static final int BOMB_HE = 32; //bomb height

    //shot
    public static final int SH_WI = 1; //shot width
    public static final int SH_HE = 3; //shot height
    
    public static final String shot = "";
    
    //gamestates
    public static final int PL = 0; //playing
    public static final int WI = 1; //win
    public static final int LO = 2; //lost
    public static final int PA = 3; //pause
    public static final int ME = 4; //menu
    public static final int DI = 5; //dialog
    public static final int HT = 6; //how to play
    public static final int OP = 7; //how to play
    
    //colors
    Color grayDark = new Color (66, 66, 66);
    Color gray = new Color (125, 125, 125);
    Color grayLight = new Color (166, 166, 166);
    Color red = new Color (255, 26, 0);
    Color orange = new Color (255, 153, 0);
    Color yellow = new Color (255, 230, 0);
    Color green = new Color (204, 255, 51);
    
    //images
    public static final String pa_bg = "/img/backgrounds/graytransbg.png";
    public static final String me_bg = "/img/backgrounds/me_bg.png";
    public static final String sampleimg = "/img/backgrounds/pl_bg.png";
    
    public static final String playerImage = "/img/player/player1.png";
    public static final String playerlImage = "/img/player/player1l.png";
    public static final String playerrImage = "/img/player/player1r.png";
    public static final String playerahhImage = "/img/player/player1ahh.png";
    public static final String playerahhlImage = "/img/player/player1ahhl.png";
    public static final String playerahhrImage = "/img/player/player1ahhr.png";
    
    public static final String virusImage = "/img/virus/virus1.png";
 
    //fonts
    public static final Font big = new Font("Helvetica", Font.BOLD, 50);
    public static final Font big2 = new Font("Helvetica", Font.BOLD, 30);
    public static final Font small = new Font("Helvetica", Font.BOLD, 10);
    public static final Font small2 = new Font("Helvetica",Font.BOLD,16);
}