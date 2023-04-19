import java.util.Scanner;   //import the scanner class
public class TP02_1_Hello_Ones_Name {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("\nInput your name: ");
        String name = keyboard.nextLine(); //read a line
        
        System.out.println("Hello " +name+ "!\n\n");
        keyboard.close();
    }
}



