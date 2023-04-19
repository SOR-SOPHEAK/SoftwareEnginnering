import java.util.Scanner;

public class RectangleTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TP07_1_Rectangle rec = new TP07_1_Rectangle(0, 0);

        System.out.print("\n\tInput height: ");
        rec.setHeight(sc.nextInt());

        System.out.print("\tInput width: ");
        rec.setWidth(sc.nextInt());

        System.out.println("\n\tPerimeter: "+rec.calculatePerimeter());
        System.out.println("\tSurface: "+rec.calculateSurface()+"\n");
    
    }
}
