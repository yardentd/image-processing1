/**
 * * Assignment Number   :  6.1
 * * File Name       :  Editor.java
 * * Name            :  yarden toledano
 * * ID              :  205576663
 * * Email           :  yardentd@gmail.com
 * <p>
 * client program that uses the three image processing:
 * flip horizontally ,flip vertically , segment.
 */
public class Editor1 {
    public static void main(String[] args) {

        System.out.println("Enter filename ____.ppm");
        String imageName = args[0];
        System.out.println("Enter operator :");
        System.out.println("fh - flip the image Horizontally ");
        System.out.println("fv -flip the image Vertically");
        System.out.println("se - black & white segement");
        String operation = args[1];
        if (operation.equals("fv")) {
            ImageEditing.show(
                    ImageEditing.flipVertically(ImageEditing.read(imageName)));
        }
        if (operation.equals("fh")) {
            ImageEditing.show(
                    ImageEditing.flipHorizontally(ImageEditing.read(imageName)));
        }
        if (operation.equals("se")) {
            ImageEditing.show(
                    ImageEditing.flipVertically(ImageEditing.read(imageName)));
        }
    }
}
