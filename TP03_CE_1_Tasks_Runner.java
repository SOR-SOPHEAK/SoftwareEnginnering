import java.util.Scanner;

public class TP03_CE_1_Tasks_Runner {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("\n-------- Menu --------");
        System.out.println("1. Seconds to Time");
        System.out.println("2. Time to Seconds");
        System.out.println("3. Calling Cost");
        System.out.println("4. Riels to Dollar");
        System.out.println("5. Traveling Duration");
        System.out.println("0. Exit");
        
        int option;
            
        do{
            System.out.print("\nChoose an option: ");
            option = keyboard.nextInt();
            System.out.print("------------------------");
            switch(option){
                case 0:
                    System.out.println("\nexit\n");
                    System.exit(0);
                    break;
    
                case 1:
                    TP03_1_SecondsToTime.main(args);
                    break;
    
                case 2:
                    TP03_2_TimeToSeconds.main(args);
                    break;
    
                case 3:
                    TP03_3_CallingCost.main(args);
                    break;
    
                case 4: 
                    TP03_4_RielsToDollar.main(args);
                    break;
    
                case 5:
                    TP03_5_TravelingDuration.main(args);
                    break;
            }
        }while(option != 0);
        
        keyboard.close();
            }
    }
    

