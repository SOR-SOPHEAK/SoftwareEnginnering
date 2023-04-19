import java.util.Scanner;

public class TP03_5_TravelingDuration {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
            
        System.out.println("\n\nProgram for calculating duration of travel from ITC to Airport.");
        System.out.print("Please input traffic jam factor (in percentage [0-100]): ");
        float trafficJamFactor = keyboard.nextFloat();

        trafficJamFactor = trafficJamFactor/100;
        float distance = 7;
        float avgSpeed = 30*trafficJamFactor;
        float time = (distance/avgSpeed)*3600;
    
        int hour = (int)time/3600;
        int minute = (int)(time%3600)/60;
        int second = (int)(time%3600)%60;

        System.out.printf("\nTravelling Duration = %02d:%02d:%02d\n\n", hour, minute, second);
        
        //keyboard.close();
    }
}
