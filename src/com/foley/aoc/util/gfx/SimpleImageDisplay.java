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
     * Shows an image on the screen
     *
     * @param img the image to show
     */
    public static void show(Image img) {
        show(img, "Simple Image Display");
    }

    /**
     * Shows an image to the screen; displays a custom title on the window
     *
     * @param img the image to show
     * @param windowTitle the title of the window
     */
    public static void show(Image img, String windowTitle) {
        JLabel label = new JLabel(new ImageIcon(img));
        JFrame frame = new JFrame(windowTitle);
        frame.getContentPane().add(label);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
