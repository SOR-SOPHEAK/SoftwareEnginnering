import java.util.Scanner;

public class TP03_2_TimeToSeconds {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("\n\nProgram for converting time to seconds.");
        System.out.print("Please input hours: ");
        int hours = keyboard.nextInt();

        System.out.print("Please input minutes: ");
        int minutes = keyboard.nextInt();

        System.out.print("Please input seconds: ");
        int seconds = keyboard.nextInt();

        System.out.printf("\nNumber of seconds = %dx3600 + %dx60 + %d = %d\n\n", hours, minutes, seconds, seconds=hours*3600+minutes*60+seconds);
        
        //keyboard.close();
    }
}
