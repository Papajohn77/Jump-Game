package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Panel extends JPanel implements KeyListener {

    // Mario figure.
    private final Mario mario;

    // Get the y coordinate of mario.
    public int getMarioY() {
        return mario.getMarioY();
    }

    // Required, because JPanel implements serializable.
    private static final long serialVersionUID = 1L;

    public Panel(Mario mario) {
        this.mario = mario;
        addKeyListener(mario);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    /**
     * Perform all drawing operations
     * By overriding the JPanel method and initiating all the drawing
     * from this place we take advantage of JPanel's double-buffering
     * capability.
     */
    /* This method is called from the Gameplay (with repaint()) to repaint everything
       on the Panel! */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Frame.BACKGROUNDCOLOUR);

        // The score.
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 35));
        g.drawString(String.valueOf(Gameplay.score), 1120, 40);

        // The grass.
        g.setColor(Color.green);
        g.fillRect(0, (Frame.HEIGHT - 115), Frame.WIDTH, Frame.HEIGHT);

        // The ground.
        g.setColor(new Color(101, 67, 33));
        g.fillRect(0, (Frame.HEIGHT - 100), Frame.WIDTH, Frame.HEIGHT);

        // The message we display when the state of the game is false.
        if (!Gameplay.play) {
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 35));
            g.drawString("Game Over, Score: " + Gameplay.score, 425, 300);

            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("Press ENTER to Restart", 450, 350);
            return;
        }

        // Draws mario. (We draw mario here to disapear when we have lost.)
        mario.draw(g, this);

        // Draws the pipes.
        Pipe[] pipes = Gameplay.pipes;
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }

        // Draws the clouds.
        Cloud[] clouds = Gameplay.clouds;
        for (Cloud cloud : clouds) {
            cloud.draw(g);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            // We want to restart the game only if its state is false.
            if (Gameplay.play) {
                return;
            }

            // Recreate the pipes.
            Gameplay.pipes = new Pipe[(Frame.WIDTH / 200)];
            for (int i = 0; i < Gameplay.pipes.length; i++) {
                Gameplay.pipes[i] = new Pipe(this, Gameplay.frame, (i * 200) + 1200);
            }

            // Reset score.
            Gameplay.score = 0;

            // Turn the state of the game to true.
            Gameplay.play = true;

            // Reset the flag.
            Gameplay.passed = false;
        }
    }
}