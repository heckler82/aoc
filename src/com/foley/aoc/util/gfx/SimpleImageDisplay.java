package com.foley.aoc.util.gfx;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Image;

/**
 * Displays an image
 *
 * @author Evan Foley
 * @version 28 Nov 2020
 */
public class SimpleImageDisplay{

    /**
     * Creates an image display
     *
     * @param img The image to display
     */
    public SimpleImageDisplay(Image img) {
        this(img, "Simple Image Display");
    }

    /**
     * Creates an image display with the given window title
     *
     * @param img The image to display
     * @param windowTitle The title of the window
     */
    public SimpleImageDisplay(Image img, String windowTitle) {
        // Setup the image
        JLabel label = new JLabel(new ImageIcon(img));
        // Frame setup
        JFrame frame = new JFrame(windowTitle);
        frame.getContentPane().add(label);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
