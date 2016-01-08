import java.awt.*;
import java.awt.Font;
import java.awt.FontMetrics;

public interface Commons{
    //board
    public static final int BO_WI = 500; //board width
    public static final int BO_HE = 700; //board height
    
    //player
    public static final int PL_WI = 10; //player width
    public static final int PL_HE = 10; //player height
    
    public static final String player = "";
    
    //alien
    public static final int AMOUNT = 20; //amount of aliens
    
    public static final int AL_WI = 10; //alien width
    public static final int AL_HE = 10; //alien height
    
    public static final String alien = "";
    
    public static final int CHANCE = 5; //chance of shot
    
    public static final String shot = "";
    
    //shot
    public static final int SH_WI = 1; //shot width
    public static final int SH_HE = 3; //shot height
    
    //gamestates
    public static final int PL = 0; //playing
    public static final int WI = 1; //win
    public static final int LO = 2; //lost
    public static final int PA = 3; //pause
    public static final int ME = 4; //menu
    public static final int DI = 5; //dialog
    
    //colors
    Color grayDark = new Color (66, 66, 66);
    Color gray = new Color (125, 125, 125);
    Color grayLight = new Color (199, 199, 199);
    
    //images
    public static final String pa_bg = "/img/graytransbg.png";
    public static final String me_bg = "/img/me_bg.png";
    public static final String sampleimg = "/img/sample.png";
    
    //fonts
    public static final Font big = new Font("Helvetica", Font.BOLD, 50);
    public static final Font big2 = new Font("Helvetica", Font.BOLD, 30);
    public static final Font small = new Font("Helvetica", Font.BOLD, 10);
}