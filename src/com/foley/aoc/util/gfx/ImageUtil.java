package com.foley.aoc.util.gfx;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Utility class for creating and working with images
 *
 * @author Evan Foley
 * @version 13 Dec 2021
 */
public class ImageUtil {
    /**
     * Creates a new image from a collection of points with the specified background and foreground colors, the specified
     * point sizes, and the specified padding around the points
     *
     * @param pts the collection of points
     * @param background the background color
     * @param foreground the foreground color
     * @param pointSizeX the lateral size of the points
     * @param pointSizeY the vertical size of the points
     * @param padding the size of the padding around the points
     * @return the created image
     */
    public static Image createImage(Collection<Point> pts, Color background, Color foreground, int pointSizeX, int pointSizeY, int padding) {
        if(pts == null || pts.isEmpty()) {
            throw new IllegalArgumentException("Cannot create an image from a null or empty collection");
        }
        Point maxX = pts.stream().max((p1, p2) -> Integer.compare(p1.x, p2.x)).get();
        Point maxY = pts.stream().max((p1, p2) -> Integer.compare(p1.y, p2.y)).get();

        return createImage(pts, maxX.x, maxY.y, background, foreground, pointSizeX, pointSizeY, padding);
    }

    /**
     * Creates a new image from a collection of points with the specified background and foreground colors, the specified
     * point sizes, and the specified padding around the points. The image will be width units wide by height units high with
     * padding units around
     *
     * @param pts the collection of points
     * @param background the background color
     * @param foreground the foreground color
     * @param pointSizeX the lateral size of the points
     * @param pointSizeY the vertical size of the points
     * @param padding the size of the padding around the points
     * @return the created image
     */
    public static Image createImage(Collection<Point> pts, int width, int height, Color background, Color foreground,
                                    int pointSizeX, int pointSizeY, int padding) {
        if(pts == null || pts.isEmpty()) {
            throw new IllegalArgumentException("Cannot create an image from a null or empty collection");
        }

        int padX = padding * pointSizeX;
        int padY = padding * pointSizeY;
        int pxlWidth = (width * pointSizeX) + (3 * padX);
        int pxlHeight = (height * pointSizeY) + (3 * padY);

        Image img = new BufferedImage(pxlWidth, pxlHeight, BufferedImage.TYPE_INT_ARGB);

        var g = img.getGraphics();
        g.setColor(background);
        g.fillRect(0, 0, pxlWidth, pxlHeight);
        g.setColor(foreground);
        for(var p : pts) {
            int x = p.x * pointSizeX;
            int y = p.y * pointSizeY;
            g.fillRect(x + padX, y + padY, pointSizeX, pointSizeY);
        }
        g.dispose();

        return img;
    }

    /**
     * Saves an image to the /res/images folder
     *
     * @param img the image to save
     * @param fileName the name of the file to save the image as
     */
    public static void saveImage(Image img, String fileName) {
        RenderedImage rImg = (RenderedImage)img;
        try {
            File output = new File("res/images/saved/" + fileName);
            if(output.exists()) {
                System.out.println("INFORMATIONAL: File already exists. Will not overwrite");
                return;
            }
            output.createNewFile();
            ImageIO.write(rImg, "png",output);
        } catch(IOException e) {
            System.err.println("ERROR: Could not write image to folder");
        }
    }
}
