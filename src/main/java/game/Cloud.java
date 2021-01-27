package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class Cloud {

    // The 'x' current coordinate of the cloud.
    private int coordX;

    // The 'y' coordinate of the cloud.
    private int coordY = 60;

    // The canvas to draw the cloud onto.
    private Graphics2D canvas;

    // The canvas's bounds.
    private Rectangle bounds;

    // The cloud image.
    private BufferedImage img;

    // The Panel we draw the pipes onto.
    private Panel panel;

    public Cloud(Panel p, Frame f, int coordX) {
        try {
            panel = p;
            bounds = panel.getBounds();
            canvas = (Graphics2D) panel.getGraphics();

            this.coordX = coordX;
            this.img = ImageIO.read(getClass().getClassLoader().getResource("cloud(120,120).png"));
        } catch (IOException ex) {
            Logger.getLogger(Pipe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Its called repeatedly from the Panel to draw each cloud.
    public void draw(Graphics g) {
        // The cloud goes back to the right side when it hits the left boarder.
        if (coordX + 100 <= 0) {
            coordX = 1200;
        }

        // Draw the cloud.
        g.drawImage(img, coordX, coordY, panel);

        // Move the cloud left.
        coordX = coordX - 5;

        /* If the coordX is negative we don't want to move the cloud up and down
           because it will always go up (to the else statement). */
        if (coordX <= 0) {
            return;
        }

        // Move the cloud up and down.
        if (coordX % 10 == 5) {
            coordY += 2;
        } else {
            coordY -= 2;
        }
    }
}
