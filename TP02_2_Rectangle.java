import java.util.Scanner;
public class TP02_2_Rectangle {
    public static void main(String[] orgs){
        Scanner keybord = new Scanner(System.in);

        System.out.println("\n\nProgram for calculating perimeter and surface of a Rectangle.");
        System.out.print("Please input width (in meter): ");
        int width = keybord.nextInt();
        
        System.out.print("Please input height (in meter): ");
        int height = keybord.nextInt();

        int perimeter = (width+height) * 2; 

        System.out.printf("\n\nPerimeter = (%d + %d) x 2 = %d m", width, height, perimeter);
        
        int surface = width*height;
        System.out.printf("\nSurface = %d x %d = %d m^2\n\n", width, height, surface);
        

        keybord.close();
    }
    
}
