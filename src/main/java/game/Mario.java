package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class Mario implements KeyListener {

    // The mario image.
    private BufferedImage img;

    // The coordinate X of mario.
    public static final int MARIO_X = 70;

    // The coordinate Y of mario. (Starts 70 pixels (his height) above the grass.)
    public static int marioY = (Frame.HEIGHT - 115) - 30;

    /* The minimum coordinate Y of mario. */
    private static final int MARIO_MIN_Y = (Frame.HEIGHT - 115) - 30;

    /* The maximum coordinate Y of mario. */
    private static final int MARIO_MAX_X = 360;

    // A flag that inform us if mario is jumping or not.
    public static boolean jumping = false;

    public Mario() {
        try {
            // Mario image
            this.img = this.img = ImageIO.read(getClass().getClassLoader().getResource("mario(30,30).png"));
        } catch (IOException ex) {
            Logger.getLogger(Mario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Get the Y coordinate of mario.
    public int getMarioY() {
        return marioY;
    }

    // Draws the mario figure on its current coordinates.
    public void draw(Graphics g, Panel p) {
        // Draw mario
        g.drawImage(img, MARIO_X, marioY, p);

        // Moves mario up or down based on if he is jumping or not.
        if(jumping) {
            marioY -= 18;
        } else {
            marioY += 16;
        }

        // Do not allow mario to go down pass a certain point.
        if (marioY >= MARIO_MIN_Y) {
            marioY = MARIO_MIN_Y;
        }

        // Do not allow mario to go up pass a certain point.
        if (marioY <= MARIO_MAX_X) {
            marioY = MARIO_MAX_X;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            // If mario is on the ground we jump.
            if (marioY >= MARIO_MIN_Y) {
                jumping = true;
                new Thread(new JumpingThread()).start();
            }

            if (Gameplay.passed && Gameplay.play) {
                Gameplay.score++;
            }
        }
    }
}