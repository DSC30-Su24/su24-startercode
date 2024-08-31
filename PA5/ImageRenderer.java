import java.awt.*;

/**
 * Render MNIST images and display predictions
 */
public class ImageRenderer {
    private static final boolean DARK = false;
    private static final int PIXEL_SIZE = 15;
    private int width;
    private int height;
    private int padX;
    private int padY;

    public static class Pixel {
        private Color color;

        public Pixel(Color c) {
            this.color = c;
        }

        public void draw(double x, double y) {
            StdDraw.setPenColor(color);
            StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
        }
    }

    public void init(int w, int h, int padX, int padY, Pixel[][] img) {
        this.width = w;
        this.height = h;
        this.padX = padX;
        this.padY = padY;

        StdDraw.setCanvasSize((width+2* this.padX) * PIXEL_SIZE,
                (height+2* this.padY) * PIXEL_SIZE);

        StdDraw.setXscale(0, width+2* this.padX);
        StdDraw.setYscale(0, height+2* this.padY);
        StdDraw.clear(Color.BLACK);

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (i == 28 || j == 28)
                    img[i][j] = new Pixel(Color.GRAY);
                else if (DARK)
                    img[i][j] = new Pixel(Color.BLACK);
                else
                    img[i][j] = new Pixel(Color.WHITE);
            }
        }

        StdDraw.enableDoubleBuffering();
        StdDraw.show();
    }

    public void render(Pixel[][] img) {
        StdDraw.clear(Color.GRAY);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setFont(new Font("Courier", Font.BOLD, PIXEL_SIZE*11/5));
        StdDraw.text( this.width / 2. + 1, this.height + 12,
                "[Q] quit | [N] cycle | [C] clear | [G] guess");
        StdDraw.setFont(new Font("Courier", Font.PLAIN, PIXEL_SIZE*11/5));
        StdDraw.text(this.width / 4. + 1, this.height + 9, "Test Image");
        StdDraw.text(this.width * 3 / 4. + 1, this.height + 9, "Closest Match");
        StdDraw.text(this.width / 4. + 1, 5, "2nd Closest Match");
        StdDraw.text(this.width * 3 / 4. + 1, 5, "3rd Closest Match");
        for (int x = 0; x < img.length; x += 1) {
            for (int y = 0; y < img[0].length; y += 1) {
                img[x][y].draw(x + padX, y + padY);
            }
        }
        StdDraw.show();
    }

    public static float[] flatten(Pixel[][] img) {
        float[] ans = new float[28*28];
        for (int i = 0; i < 28; i ++) {
            for (int j = 0; j < 28; j++) {
                float color = img[i][27-j + 29].color.getBlue() / 255f;
                if (DARK)
                    ans[i + 28*j] = color;
                else
                    ans[i + j*28] = 1 - color;
            }
        }
        return ans;
    }

    public void changeColor(int i, int j, Pixel[][] img) {
        boolean changed = false;
        for (int x = i - 1; x < i + 1; x++) {
            for (int y = j - 1; y < j + 1; y++) {
                if (DARK && x < 28 && x >= 0 && y < 28+29 && y >= 29
                        && !img[x][y].color.equals(Color.WHITE)) {
                    changed = true;
                    img[x][y].color = Color.WHITE;
                }
                if (!DARK && x < 28 && x >= 0 && y < 28+29 && y >= 29
                        && !img[x][y].color.equals(Color.BLACK)) {
                    changed = true;
                    img[x][y].color = Color.BLACK;
                }
            }
        }
        if (changed) {
            this.render(img);
        }
    }

    public void drawGuess(int guess) {
        StdDraw.setFont(new Font("Courier", Font.BOLD, PIXEL_SIZE*5/2));
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text( this.width / 2. + 1, 2, "Guess: " + guess);
        StdDraw.show();
    }

    private static void drawMatches(Pixel[][] img, int i, float num2, float num3, float num4) {
        img[i % 28 + 29][27 - i / 28 + 29].color = new Color(num2, num2, num2);
        img[i % 28][27 - i / 28].color = new Color(num3, num3, num3);
        img[i % 28 + 29][27 - i / 28].color = new Color(num4, num4, num4);
    }

    public static void main(String[] args) {

        MNIST.downloadMNIST();

        int padX = 2;
        int padY = 7;

        ImageRenderer pr = new ImageRenderer();

        Pixel[][] img = new Pixel[MNIST.IMG_DIM * 2 + 1][MNIST.IMG_DIM * 2 + 1];
        pr.init(MNIST.IMG_DIM * 2 + 1, MNIST.IMG_DIM*2 + 1, padX, padY, img);

        pr.render(img);

        boolean over = false;

        int ctr = 0;
        while(!over) {
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                int tileX = ((int) x) - padX;
                int tileY = ((int) y) - padY;
                pr.changeColor(tileX, tileY, img);
            }

            if (StdDraw.hasNextKeyTyped()) {
                char curr = StdDraw.nextKeyTyped();
                if (curr == 'Q' || curr == 'q') {
                    over = true;
                } else if (curr == 'G' || curr == 'g') {
                    float[] data = flatten(img);
                    MNIST.DataHolder[] closestMatches = MNIST.getClosestMatches(data, 3);
                    int guess = MNIST.predict(closestMatches);
                    for (int i = 0; i < closestMatches[0].image.length; i++) {
                        float num2 = closestMatches[0].image[i];
                        float num3 = closestMatches[1].image[i];
                        float num4 = closestMatches[2].image[i];
                        if (!DARK) {
                            num2 = 1 - num2;
                            num3 = 1 - num3;
                            num4 = 1 - num4;
                        }
                        drawMatches(img, i, num2, num3, num4);
                    }
                    pr.render(img);
                    pr.drawGuess(guess);
                } else if (curr == 'C' || curr == 'c') {
                    for (int i = 0; i < img.length; i++) {
                        for (int j = 0; j < img[0].length; j++) {
                            if (i == 28 || j == 28)
                                img[i][j].color = Color.GRAY;
                            else if (DARK)
                                img[i][j].color = Color.BLACK;
                            else
                                img[i][j].color = Color.WHITE;
                        }
                    }
                    pr.render(img);
                } else if (curr == 'N' || curr == 'n') {
                    MNIST.DataHolder[] closestMatches =
                            MNIST.getClosestMatches(MNIST.TEST_IMAGES[ctr], 3);
                    int guess = MNIST.predict(closestMatches);
                    for (int i = 0; i < MNIST.TEST_IMAGES[ctr].length; i++) {
                        float num = MNIST.TEST_IMAGES[ctr][i];
                        float num2 = closestMatches[0].image[i];
                        float num3 = closestMatches[1].image[i];
                        float num4 = closestMatches[2].image[i];
                        if (!DARK) {
                            num = 1 - num;
                            num2 = 1 - num2;
                            num3 = 1 - num3;
                            num4 = 1 - num4;
                        }
                        img[i % 28][27 - i / 28 + 29].color = new Color(num, num, num);
                        drawMatches(img, i, num2, num3, num4);

                    }
                    ctr = (ctr + 1) % MNIST.TEST_IMAGES.length;
                    pr.render(img);
                    pr.drawGuess(guess);
                }
            }
        }

        StdDraw.pause(100);
        System.exit(0);

    }
}