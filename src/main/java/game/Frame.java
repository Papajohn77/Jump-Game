package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;



import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * The program's main window.
 */
public class Frame extends JFrame {

    // The window's width.
    public static final int WIDTH = 1200;
    // The window's height.
    public static final int HEIGHT = 600;

    // The window's background color (blue).
    public static final Color BACKGROUNDCOLOUR = new Color(0, 162, 232);

    // The canvas to draw onto.
    private Panel drawablePanel = null;

    // Required, because JFrame implements serializable.
    static final long serialVersionUID = 1L;

    // Constructor to initialize and display the window.
    public Frame() throws IOException {
        // The title
        super(String.format("Mario Jump Game"));
        // Sets the icon of the window.
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("MarioIcon.png"));
        setIconImage(icon.getImage());

        initializeGraphics();
    }

    // Initialize the main window. 
    private void initializeGraphics() throws IOException {
        // Make our window look nice.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create mario.
        Mario mario = new Mario();

        // Create our drawing canvas and add it to the Frame.
        drawablePanel = new Panel(mario);
        drawablePanel.setBackground(BACKGROUNDCOLOUR);
        drawablePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setContentPane(drawablePanel);

        // Handle termination.
        setDefaultCloseOperation(
                javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        // Exit when the window is closed.
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Our window size.
        setSize(WIDTH, HEIGHT);

        // Center the window.
        setLocationRelativeTo(null);

        // Removes the ability to change the size of the window.
        setResizable(false);

        /* Display the window 
          (When the window becomes visible (or resized) => paintComponent() is called!). */
        setVisible(true);
    }

    // Get the canvas's drawing panel
    public Panel getCanvas(){
        return drawablePanel;
    }
}