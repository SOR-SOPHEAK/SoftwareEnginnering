import java.util.Scanner;

public class TP02_5_Cheater_Game {
    public static void main(String[] args) {
    Scanner keybord = new Scanner(System.in);

    System.out.println("\n\nProgram for guessing your luckiness.");
    System.out.print("Please in put a positive number: ");
    int num = keybord.nextInt();

    System.out.printf("I got %d. I am luckier.\n\n", num+1);

    keybord.close();
    }
}
