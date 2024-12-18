import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] image;

		// Tests the horizontal flipping of an image:
		image = flippedHorizontally(tinypic);
		System.out.println();
		print(image);
		
		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	/** Returns a 2D array of Color values, representing the image data
 * stored in the given PPM file. */
public static Color[][] read(String fileName) {
    In in = new In(fileName);
    // Reads the file header, ignoring the first and the third lines.
    in.readString(); // Skip "P3"
    int numCols = in.readInt(); // Number of columns
    int numRows = in.readInt(); // Number of rows
    in.readInt(); 
    
    // Creates the image array
    Color[][] image = new Color[numRows][numCols];
    
    // Reads the RGB values from the file into the image array
    for (int i = 0; i < numRows; i++) {
        for (int j = 0; j < numCols; j++) {
            int red = in.readInt();
            int green = in.readInt();
            int blue = in.readInt();
            image[i][j] = new Color(red, green, blue);
        }
    }
    
    return image;
}


    // Prints the RGB values of a given color.
	public static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	public static void print(Color[][] image) {
		// Loop through each row
		for (int i = 0; i < image.length; i++) {
			// Loop through each column in the row
			for (int j = 0; j < image[i].length; j++) {
				// Print the current color using the print(Color) function
				print(image[i][j]);
				System.out.print(" "); // Add a space between colors in the row
			}
			// Move to the next line after printing all colors in the row
			System.out.println();
		}
	}
	
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		
		int rows = image.length;
		int cols = image[0].length;
	
		
		Color[][] flippedImage = new Color[rows][cols];
	
	
		for (int i = 0; i < rows; i++) {
			// Reverse the pixels in the current row
			for (int j = 0; j < cols; j++) {
				flippedImage[i][j] = image[i][cols - 1 - j];
			}
		}
	
		return flippedImage;
	}
	
	public static Color[][] flippedVertically(Color[][] image) {
		int rows = image.length;
		int cols = image[0].length;
		Color[][] flippedImage = new Color[rows][cols];
		for (int i = 0; i < rows; i++) {
			flippedImage[i] = image[rows - 1 - i];
		}
		return flippedImage;
	}
	
	private static Color luminance(Color pixel) {
		int r = pixel.getRed();
		int g = pixel.getGreen();
		int b = pixel.getBlue();
		int lum = (int) (0.299 * r + 0.587 * g + 0.114 * b);
		return new Color(lum, lum, lum);
	}
	
	/**
 * Returns an image which is the grayscaled version of the given image.
 */
public static Color[][] grayScaled(Color[][] image) {
    int rows = image.length;
    int cols = image[0].length;
    Color[][] grayImage = new Color[rows][cols];

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            grayImage[i][j] = luminance(image[i][j]);
        }
    }

    return grayImage;
}

	/**
 * Returns a new image which is a scaled version of the original image.
 * @param image - the original image (2D array of Color objects)
 * @param width - the desired width of the new image
 * @param height - the desired height of the new image
 * @return a scaled version of the image
 */
public static Color[][] scaled(Color[][] image, int width, int height) {
    int originalWidth = image[0].length;
    int originalHeight = image.length;

    Color[][] scaledImage = new Color[height][width];

    double scaleWidth = (double) originalWidth / width;
    double scaleHeight = (double) originalHeight / height;

    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            int originalRow = (int) (i * scaleHeight);
            int originalCol = (int) (j * scaleWidth);
            scaledImage[i][j] = image[originalRow][originalCol];
        }
    }

    return scaledImage;
}

	
public static Color blend(Color c1, Color c2, double alpha) {
   

    int red = (int) Math.round(alpha * c1.getRed() + (1 - alpha) * c2.getRed());
    int green = (int) Math.round(alpha * c1.getGreen() + (1 - alpha) * c2.getGreen());
    int blue = (int) Math.round(alpha * c1.getBlue() + (1 - alpha) * c2.getBlue());

    return new Color(red, green, blue);
}
public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
    int rows = image1.length;
    int cols = image1[0].length;

    Color[][] blendedImage = new Color[rows][cols];

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            blendedImage[i][j] = blend(image1[i][j], image2[i][j], alpha);
        }
    }

    return blendedImage;
}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		//// Replace this comment with your code
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

