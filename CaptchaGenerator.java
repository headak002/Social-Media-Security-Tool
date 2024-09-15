import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CaptchaGenerator {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 80;
    private static final int NUM_CHARACTERS = 6;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws IOException {
        String captchaText = generateCaptchaText();
        BufferedImage captchaImage = generateCaptchaImage(captchaText);
        saveImage(captchaImage, "captcha.png");
        System.out.println("CAPTCHA generated successfully!");
    }

    private static String generateCaptchaText() {
        StringBuilder captchaText = new StringBuilder();
        for (int i = 0; i < NUM_CHARACTERS; i++) {
            captchaText.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return captchaText.toString();
    }

    private static BufferedImage generateCaptchaImage(String captchaText) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // Fill background with white color
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw captcha text
        g2d.setColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 40);
        g2d.setFont(font);
        g2d.drawString(captchaText, 10, 50);

        // Add noise (random dots)
        for (int i = 0; i < 100; i++) {
            int x = RANDOM.nextInt(WIDTH);
            int y = RANDOM.nextInt(HEIGHT);
            g2d.setColor(new Color(RANDOM.nextInt(256), RANDOM.nextInt(256), RANDOM.nextInt(256)));
            g2d.fillRect(x, y, 1, 1);
        }

        // Add noise (random lines)
        for (int i = 0; i < 10; i++) {
            int x1 = RANDOM.nextInt(WIDTH);
            int y1 = RANDOM.nextInt(HEIGHT);
            int x2 = RANDOM.nextInt(WIDTH);
            int y2 = RANDOM.nextInt(HEIGHT);
            g2d.setColor(new Color(RANDOM.nextInt(256), RANDOM.nextInt(256), RANDOM.nextInt(256)));
            g2d.drawLine(x1, y1, x2, y2);
        }

        g2d.dispose();
        return image;
    }

    private static void saveImage(BufferedImage image, String filename) throws IOException {
        File file = new File(filename);
        ImageIO.write(image, "png", file);
    }
}