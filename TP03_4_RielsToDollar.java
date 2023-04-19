import java.util.Scanner;

public class TP03_4_RielsToDollar {
    public static void main(String[] args) {
        Scanner keybord = new Scanner(System.in);
        
        System.out.println("\n\nProgram for converting money in Riels to Dollars.");
        System.out.println("Conversion rate is: 1 USD = 4000 RIELS");
        System.out.print("Please input money in Riels: ");
        int riels = keybord.nextInt();

        float usd = (float)riels/4000;
        System.out.printf("\n%d RIELS = %.2f USD\n\n", riels, usd);

        //keybord.close();
    }
}
