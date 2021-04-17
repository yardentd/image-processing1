/**
 * * Assignment Number   :  6.0
 * * File Name       :  ImageEditing.java
 * * Name            :  yarden toledano
 * * ID              :  205576663
 * * Email           :  yardentd@gmail.com
 * <p>
 * The ImageEditing class provides static methods
 * for read,show and editing digital images (.ppm).
 *
 * @author yarden toledano.
 */
public class ImageEditing {

    public static void main(String[] args) {
        int[][][] image = read("tinypic.ppm");
        print(image);  //test print

        //**test flipH & flipV**
//      testFlip();
//      testSegment();

        //**test morph**
        String source = args[0];
        String target = args[1];
        int n = Integer.parseInt(args[2]);
        morph(read(source), read(target), n);

    }

    public static void testFlip() {
        int[][][] image = read("thor.ppm");
        show(image);
        show(flipHorizontally(image));
        show(flipVertically(image));
    }

    public static void testSegment() {
        int[][][] image = read("eyes.ppm");
        show(segement(image));
    }

    /**
     * A function that receives an file name and creates
     * image represented by a rectangular matrix of pixels*
     *
     * @param filename the name of the image - example.ppm.
     * @return three-dimensional array image
     */
    public static int[][][] read(String filename) {
        StdIn.setInput(filename);
        StdIn.readLine();
        int colom = StdIn.readInt();
        int row = StdIn.readInt();
        int[][][] image = new int[row][colom][3];
        StdIn.readInt();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colom; j++) {
                image[i][j][0] = StdIn.readInt();
                image[i][j][1] = StdIn.readInt();
                image[i][j][2] = StdIn.readInt();
            }
        }
        return image;
    }

    /**
     * print  function writes to standard output the contents
     * of the given 3- dimensional array.
     *
     * @param source a 3- dimensional array of ppm file. [H][W][r,g,b].
     */
    private static void print(int[][][] source) {
        int row = source.length;
        int colom = source[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colom; j++) {
                System.out.print(String.format("%3s", source[i][j][0]) + " "); //StdOut.print(String.format("%3s" ,source[i][j][0] + " "));
                System.out.print(String.format("%3s", source[i][j][1]) + " "); //StdOut.print(String.format("%3s" ,source[i][j][1] + " "));
                System.out.print(String.format("%3s", source[i][j][2]) + " "); //StdOut.print(String.format("%3s" ,source[i][j][2] + " "));
            }
            System.out.println();
        }
    }

    /**
     * flip the given image horizontally.
     *
     * @param source
     * @return new horizontally flipped image matrix.
     */
    public static int[][][] flipHorizontally(int[][][] source) {
        int row = source.length;
        int colom = source[0].length;
        int[][][] horiz = new int[row][colom][3];
        for (int i = 0; i < row; i++) {
            for (int j = colom - 1; j >= 0; j--) {
                horiz[i][colom - 1 - j][0] = source[i][j][0];
                horiz[i][colom - 1 - j][1] = source[i][j][1];
                horiz[i][colom - 1 - j][2] = source[i][j][2];
            }
        }
        return horiz;
    }

    /**
     * flip the given image vertically.
     *
     * @param source a three-dimensional array image ppm [H][W][RGB]
     * @return new vertically flipped image matrix.
     */
    public static int[][][] flipVertically(int[][][] source) {
        int row = source.length;
        int colom = source[0].length;
        int[][][] vertic = new int[row][colom][3];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colom; j++) {
                vertic[row - 1 - i][j][0] = source[i][j][0];
                vertic[row - 1 - i][j][1] = source[i][j][1];
                vertic[row - 1 - i][j][2] = source[i][j][2];
            }
        }
        return vertic;
    }

    /**
     * compute  the average color of the pixel.
     *
     * @param pixel the last array  of the three-dimensional array image [R,G,B].
     * @return avg typ double.
     */
    public static double average(int[][][] pixel) {
        int sumR = 0, sumB = 0, sumG = 0;
        int counter = 0;
        double avg = 0;
        for (int i = 0; i < pixel.length; i++) {
            for (int j = 0; j < pixel[0].length; j++) {
                sumR = sumR + pixel[i][j][0];
                sumG = sumG + pixel[i][j][1];
                sumB = sumB + pixel[i][j][2];
                counter++;
            }
        }
        avg = (sumR + sumG + sumB) / counter;
        return avg;
    }

    /**
     * make the image black & whit
     * @param source a three-dimensional array image ppm [H][W][RGB]
     * @return image rectangular matrix of pixels representing  black & whit picture.
     */
    public static int[][][] segement(int[][][] source) {
        int row = source.length;
        int colom = source[0].length;
        int[][][] blackNwhite = new int[row][colom][3];
        double avg = average(source);
        int red = 0, green = 0, blue = 0;
        int sumPix = 0;
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[0].length; j++) {
                red = source[i][j][0];
                green = source[i][j][1];
                blue = source[i][j][2];
                sumPix = red + green + blue;
                if (sumPix > avg) { //more then the avg --> make it white
                    blackNwhite[i][j][0] = 255;
                    blackNwhite[i][j][1] = 255;
                    blackNwhite[i][j][2] = 255;
                } else {  //less  then the avg --> make it black
                    blackNwhite[i][j][0] = 0;
                    blackNwhite[i][j][1] = 0;
                    blackNwhite[i][j][2] = 0;
                }
            }
        }
        return blackNwhite;
    }

    /**
     * take as input a digital image and two dimensions height , wide.
     * returns a scaled version of the digital image.
     * @param source a three-dimensional array image ppm [H][W][RGB]
     * @param width  int size from the clint.
     * @param height int size form the clint.
     * @return scaled version of the digital image.
     */
    public static int[][][] scale(int[][][] source, int width, int height) {
        int[][][] scl = new int[height][width][3];
        int H0 = source.length;
        int W0 = source[0].length;
        for (int i = 0; i < scl.length; i++) {
            for (int j = 0; j < scl[i].length; j++) {
                scl[i][j] = source[(int) (i * ((double) H0 / height))][(int) (j * ((double) W0 / width))];
            }
        }
        return scl;
    }

    /**
     * the function blend  two pixels to a new pixel
     * the user enter  0 <=alpha=< 1. and return a new RGB
     * according to the process - alpha*pixel1 +(1-alpha)*pixel2
     *
     * @param pixel1
     * @param pixel2
     * @param alpha  areal number 0<a<1 that determines how to blend the two inputs
     * @return returns a blended  pixel.
     */
    public static int[] blend(int[] pixel1, int[] pixel2, double alpha) {
        int[] blendRgb = new int[3];
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum = (int) (alpha * pixel1[i]) + (int) ((1 - alpha) * pixel2[i]);
            blendRgb[i] = sum;
        }
        return blendRgb;
    }

    /**
     * The function combine between tow image using a given alpha.
     * computes each new pixel using the blend function
     *
     * @param source1 a three-dimensional array image ppm [H][W][RGB]
     * @param source2 a three-dimensional array image ppm [H][W][RGB]
     * @param alpha   a real number 0<=a=<1 that determines how to blend the two inputs.
     * @return comboImage new array withe the alpha-blending of the two given source images.
     */
    public static int[][][] combine(int[][][] source1, int[][][] source2, double alpha) {
        int[][][] comboImage = new int[source1.length][source1[0].length][3];

        for (int i = 0; i < source1.length; i++) {
            for (int j = 0; j < source1[0].length; j++) {
                comboImage[i][j] = blend(source1[i][j], source2[i][j], alpha);
            }
        }
        return comboImage;
    }

    /**
     * morphs the source image into the target image in n steps.
     *
     * @param source the first digital image.
     * @param target the second digital image.
     * @param n      the number of steps to replace for image1 to image2 .
     */
    public static void morph(int[][][] source, int[][][] target, int n) {
        double alpha = 0;
        if (source.length != target.length || source[0].length != target[0].length) {
            target = scale(target, source[0].length, source.length);
        }
        double x = n;
        for (int i = 0; i <= n; i++) {
            alpha = (double) (n - i);
            alpha = alpha / x;
            ImageEditing.show(ImageEditing.combine(source, target, alpha));
        }
    }


    public static void show(int[][][] pic) {
        StdDraw.setCanvasSize(pic[0].length, pic.length);
        int height = pic.length;
        int width = pic[0].length;
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.show(30);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                StdDraw.setPenColor(pic[i][j][0], pic[i][j][1], pic[i][j][2]);
                StdDraw.filledRectangle(j + 0.5, height - i - 0.5, 0.5, 0.5);
            }
        }
        StdDraw.show();
    }
}


