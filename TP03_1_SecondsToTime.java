import java.util.Scanner;

public class TP03_1_SecondsToTime {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("\n\nInput number of seconds: ");
        int seconds = keyboard.nextInt();

        int hours, minutes;

        hours = seconds/3600;
        minutes = (seconds%3600)/60;
        //seconds = seconds-(hours*3600 + minutes*60);

        System.out.printf("Time corresponding to %dseconds is %02d:%02d:%02d.\n\n", seconds, hours, minutes, seconds = seconds-(hours*3600 + minutes*60));

        //keyboard.close();

    }
}
