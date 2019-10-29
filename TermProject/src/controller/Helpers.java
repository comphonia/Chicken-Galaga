package controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public  class Helpers {

    public static final class ImageLoader
    {

        public static BufferedImage LoadImage(String fileName)
        {
            BufferedImage img = null;

            try {
                img = ImageIO.read(new File(fileName));

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Image could not be read");
                System.exit(1);
            }

            return img;
        }
    }

}
