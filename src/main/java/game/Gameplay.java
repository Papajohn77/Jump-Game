package game;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gameplay {

    static Frame frame;

    static Panel panel;

    // The state of the game.
    static boolean play;

    /* It becomes true when the first pipe (pipes[0]) passes a certain point,
       when its true every jump gives a point. */
    static boolean passed = false;

    // The score.
    static int score = 0;

    /* We need that in order to not reset the score field when we lose, we dont want to 
       reset the score field because we want to display the previous round score until
       the player decide to start a new round. */
    static int resetScore = 0;

    // Contain the pipes.
    static Pipe[] pipes;

    // Contain the clouds.
    static Cloud[] clouds;

    // TODO: remove magic numbers from all classes.
    public static void main(String[] args) {
        try {
            // Create a window and the canvas to draw onto.
            frame = new Frame();
            panel = frame.getCanvas();

            // Create the pipes.
            pipes = new Pipe[(Frame.WIDTH / 200)];
            for (int i = 0; i < pipes.length; i++) {
                pipes[i] = new Pipe(panel, frame, (i * 200) + 1200);
            }

            // Create the clouds.
            clouds = new Cloud[4];
            for (int i = 0; i < clouds.length; i++) {
                clouds[i] = new Cloud(panel, frame, (i * 300) + 350);
            }

            /* We initialize the state of the game here because when the frame sets
               visible to true the paintComponent() method is called and the arrays are
               not created yet, so it throws NullPointerExceptions when it tries to draw
               the contents of the arrays.
               If the state of the game is false paintComponent() stops before it draws
               mario, pipes & clouds. */
            play = true;

            /* This loop is used to repaint everything on the panel every 0.125 seconds
               and it's gonna continuously run until we close the window. */
            while(true) {
                panel.repaint();
                Thread.sleep(125);
            }
        } catch (IOException ex) {
            Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
