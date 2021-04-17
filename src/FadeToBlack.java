/**
 * Assignment Number   :  6.4
 * File Name       :  Editor2.java
 * Name            :  yarden toledano
 * ID              :  205576663
 * Email           :  yardentd@gmail.com
 * <p>
 * The program FadeToBlack produces an animated colorful image
 * fades into a black and white segmented image.
 * get from the clint: image name example.ppm and get a int number n>0 .
 */
public class FadeToBlack {

    public static void main(String[] args) {
        String imageName = args[0]; // enter image name
        int n = Integer.parseInt(args[1]); // enter number of the steps
        int[][][] source = ImageEditing.read(imageName);
        int[][][] blackNWhite = ImageEditing.segement(source);
        ImageEditing.morph(source, blackNWhite, n);
    }

}
