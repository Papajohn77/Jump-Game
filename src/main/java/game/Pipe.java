package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Pipe {

    // The x coordinate of the pipe.
    private int coordX;

    // The y coordinate of the pipe. (Starts 70 pixels (its height) above the grass)
    private static final int COORD_Y = (Frame.HEIGHT - 115) - 70;

    // The canvas to draw the pipe onto.
    private Graphics2D canvas;

    // The canvas's bounds.
    private Rectangle bounds;

    // The pipe image.
    private BufferedImage img;

    // The Panel we draw the pipes onto.
    private Panel panel;

    public Pipe(Panel p, Frame f, int coordX) {
        try {
            panel = p;
            bounds = panel.getBounds();
            canvas = (Graphics2D) panel.getGraphics();

            this.coordX = coordX;
            this.img = ImageIO.read(getClass().getClassLoader().getResource("MarioPipe(40,70).png"));
        } catch (IOException ex) {
            Logger.getLogger(Pipe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Called repeatedly from the paintComponent() to draw each pipe.
    public void draw(Graphics g) {
        // The pipe goes back to the right side when it hits the left boarder.
        if (coordX + 40 <= 0) {
            coordX = 1200;
        }

        // Draw the pipe.
        g.drawImage(img, coordX, COORD_Y, panel);

        /* When the first pipe goes pass the 100 pixels horizontally (based on mario's
           starting position) for the first time we set the flag to true (we start 
           counting each jump as a point). */
        if (Gameplay.pipes[0].coordX <= 100) {
            Gameplay.passed = true;
        }

        // If the pipe touches mario we lose.
        if (new Rectangle(coordX, COORD_Y, 40, 70).intersects(
                new Rectangle(Mario.MARIO_X, panel.getMarioY(), 30, 30))) {

            Gameplay.play = false;
        }

        // Move the pipe left.
        coordX = coordX - 11;
    }
}