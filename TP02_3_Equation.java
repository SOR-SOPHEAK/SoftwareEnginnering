import java.util.Scanner;

public class TP02_3_Equation {
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);

        System.out.println("\nPrgram for calculating equation 1/x = 1/y + 1/z");
        System.out.print("Please input y: ");
        float y = keyboard.nextFloat();

        System.out.print("Please in put z: ");
        float z = keyboard.nextFloat();

        float x = (y*z)/(z+y);
        System.out.printf("Result x: %.7f\n\n", x);
        
        keyboard.close();
        
    }
}
