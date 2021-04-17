/**
 * * Assignment Number   :  6.2
 * * File Name       :  Editor2.java
 * * Name            :  yarden toledano
 * * ID              :  205576663
 * * Email           :  yardentd@gmail.com
 */
public class Editor2 {
    public static void main(String[] args) {
        String imageName = args[0];
        int width = Integer.parseInt(args[1]);
        int height = Integer.parseInt(args[2]);
        ImageEditing.show(
                ImageEditing.scale(ImageEditing.read(imageName), width, height));

    }

}
