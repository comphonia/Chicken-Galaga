package view;

import controller.Helpers;
import controller.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MyCanvas extends JPanel {

    public int width;
    public int height;

    public void render() {
        width = getSize().width;
        height = getSize().height;

        //off-screen double buffer image
        Image doubleBufferImage = createImage(width, height);
        if (doubleBufferImage == null) {
            System.out.println("Critical error: doubleBufferImage is null");
            System.exit(1);
        }

        //off-screen rendering
        Graphics2D g2Offscreen = (Graphics2D) doubleBufferImage.getGraphics();
        if (g2Offscreen == null) {
            System.out.println("Critical error: g2Offscreen is null");
            System.exit(1);
        }

        // initialize the image buffer
        g2Offscreen.setBackground((Color.BLACK));
        g2Offscreen.clearRect(0, 0, width, height);

        // render all game data here
        g2Offscreen.setColor(Color.RED);
       // g2Offscreen.drawOval(100, 100, 50, 50);

        //render all game data
        for(var fig: Main.gameData.fixedObject){
            fig.render(g2Offscreen);
        }
        for(var fig: Main.gameData.friendObject){
            fig.render(g2Offscreen);
        }
        for(var fig: Main.gameData.enemyObject){
            fig.render(g2Offscreen);
        }

        // use active rendering to put the buffer image on screen
        Graphics gOnScreen;
        gOnScreen = this.getGraphics();
        if (gOnScreen != null) {
            // copy offScreen image to onScreen
            if(Main.running)
            gOnScreen.drawImage(doubleBufferImage, 0, 0, null);
        }
        Toolkit.getDefaultToolkit().sync(); // sync the display on some systems
        if (gOnScreen != null) {
            gOnScreen.dispose();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        // read bg image
        BufferedImage img = Helpers.ImageLoader.LoadImage("./assets/graphics/bg.png");
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this); // see javadoc for more info on the parameters
    }

}
