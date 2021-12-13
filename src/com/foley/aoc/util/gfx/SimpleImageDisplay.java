package com.foley.aoc.util.gfx;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 * Displays an image
 *
 * @author Evan Foley
 * @version 28 Nov 2020
 */
public class SimpleImageDisplay extends JFrame {
    private Image img;

    /**
     * Creates a new image display for the specified image with the specified window title
     *
     * @param img the image to display
     * @param title the title of the window
     */
    private SimpleImageDisplay(Image img, String title) {
        super(title);
        this.img = img;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(img.getWidth(null) + 16, img.getHeight(null) + 39); // 16 is the left and right insets, 39 is the top and bottom insets
        setIconImage(getTitleImage());
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Draws the image to the window
     *
     * @param g the graphics context
     */
    public void paint(Graphics g) {
        var insets = getInsets();
        g.drawImage(img, insets.left, insets.top, null);
    }

    private Image getTitleImage() {
        Image tImg = null;
        try(InputStream is = this.getClass().getClassLoader().getResourceAsStream("images/aoc_star.png")) {
            tImg = ImageIO.read(is);
        } catch(IOException | IllegalArgumentException e) {
            System.out.printf("INFORMATIONAL: Could not find title image\n");
        }
        return tImg;
    }

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
        var sid = new SimpleImageDisplay(img, windowTitle);
    }
}
