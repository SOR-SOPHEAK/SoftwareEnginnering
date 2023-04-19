import java.util.Scanner;

public class TP03_3_CallingCost {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("\n\nProgram for calculating cost of a call.");
        
        System.out.print("Please input start hours: ");
        int St_hours = keyboard.nextInt();
        System.out.print("Please input start minutes: ");
        int St_minutes = keyboard.nextInt();
        System.out.print("Please input start seconds: ");
        int St_seconds = keyboard.nextInt();

        St_seconds = St_hours*3600 + St_minutes*60 + St_seconds;

        System.out.print("\nPlease input end hours: ");
        int E_hours = keyboard.nextInt();
        System.out.print("Please input end minutes: ");
        int E_minutes = keyboard.nextInt();
        System.out.print("Please input end seconds: ");
        int E_seconds = keyboard.nextInt();

        E_seconds = E_hours*3600 + E_minutes*60 + E_seconds;

        int hours, minutes, seconds;

        //Total call
        seconds = E_seconds - St_seconds;
        //Total cost (cost of call per minute is 0.05$)
        double cost = seconds*(0.05/60);

        hours = seconds/3600;
        minutes = (seconds%3600)/60;
        seconds = seconds - (hours*3600 + minutes*60);

        System.out.printf("\nTotal call duration: %dh %dmn %ds", hours, minutes, seconds);
        
        System.out.printf("\nTotal cost of this call: %.4f$\n\n", cost);
        //keyboard.close();
    }
}
