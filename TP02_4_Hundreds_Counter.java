import java.util.Scanner;

public class TP02_4_Hundreds_Counter {
    public static void main(String[] args) {
        Scanner keybord = new Scanner(System.in);

        System.out.println("\n\nProgram for counting the number of hundreds.");
        System.out.print("Please input a positive number: ");
        int num = keybord.nextInt();

        System.out.printf("\nThere are %d hundred in numebr %d.\n\n", num/100, num);

        keybord.close();
    }
}
