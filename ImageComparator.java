import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageComparator {
    public static void main(String[] args) throws IOException {
        BufferedImage image1 = ImageIO.read(new File("image1.png"));
        BufferedImage image2 = ImageIO.read(new File("image2.png"));

        int width1 = image1.getWidth();
        int height1 = image1.getHeight();
        int width2 = image2.getWidth();
        int height2 = image2.getHeight();

        if (width1 != width2 || height1 != height2) {
            int newWidth = Math.min(width1, width2);
            int newHeight = Math.min(height1, height2);

            BufferedImage resizedImage;
            if (width1 < width2) {
                resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
                resizedImage.getGraphics().drawImage(image1, 0, 0, newWidth, newHeight, null);
            } else {
                resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
                resizedImage.getGraphics().drawImage(image2, 0, 0, newWidth, newHeight, null);
            }

            image1 = resizedImage;
            image2 = resizedImage;
        }

        int width = image1.getWidth();
        int height = image1.getHeight();

        BufferedImage differenceImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = image1.getRGB(x, y);
                int rgb2 = image2.getRGB(x, y);

                if (rgb1 != rgb2) {
                    differenceImage.setRGB(x, y, 0xFF0000); // Mark differences in red
                } else {
                    differenceImage.setRGB(x, y, rgb1);
                }
            }
        }

        ImageIO.write(differenceImage, "png", new File("difference.png"));
    }
}
