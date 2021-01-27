package game;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JumpingThread implements Runnable {
    @Override
    public void run() {
        try {
            // Waits for 1,25 seconds and turns jumping to false.
            Thread.sleep(1250);
            Mario.jumping = false;
        } catch (InterruptedException ex) {
            Logger.getLogger(JumpingThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
