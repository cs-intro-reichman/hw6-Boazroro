import java.awt.Color;

import java.awt.Color;

public class Editor2 {

    public static void main(String[] args) {
        // Check for valid command-line arguments
        if (args.length != 3) {
            System.out.println("Usage: java Editor2 <image.ppm> <width> <height>");
            return;
        }

        // Parse the command-line arguments
        String fileName = args[0];
        int targetWidth = Integer.parseInt(args[1]);
        int targetHeight = Integer.parseInt(args[2]);

        // Load the source image
        Color[][] sourceImage = Runigram.read(fileName);

        // Scale the image using the provided scaled function
        Color[][] scaledImage = Runigram.scaled(sourceImage, targetWidth, targetHeight);

        // Display the source image
        int originalWidth = sourceImage[0].length;
        int originalHeight = sourceImage.length;
        StdDraw.setCanvasSize(originalWidth, originalHeight);
        StdDraw.setXscale(0, originalWidth);
        StdDraw.setYscale(0, originalHeight);
        Runigram.display(sourceImage);
        StdDraw.pause(3000); // Pause for 3 seconds

        // Display the scaled image
        StdDraw.setCanvasSize(targetWidth, targetHeight);
        StdDraw.setXscale(0, targetWidth);
        StdDraw.setYscale(0, targetHeight);
        Runigram.display(scaledImage);
    }
}
