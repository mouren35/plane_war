package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class app {
    public static BufferedImage getImg(String path){
        try {
            BufferedImage img=ImageIO.read(app.class.getResource(path));
            return img;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return  null;
    }
}
