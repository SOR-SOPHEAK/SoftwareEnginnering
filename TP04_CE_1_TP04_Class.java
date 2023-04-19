import java.util.Scanner;

public class TP04_CE_1_TP04_Class {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n--------- Menu --------");
        System.out.println("1. Prime number.");
        System.out.println("2. Lucky number.");
        System.out.println("3. Reversing number.");
        System.out.println("4. Money exchange.");
        System.out.println("5. Max amoung 8 numbers.");
        System.out.println("6. Shipping.");
        System.out.println("7. Leap year.");
        System.out.println("0. Exit.");

        int option;
        do{
            System.out.println("Choose option: ");
            option = sc.nextInt();

            if(option == 0){
                System.out.println("Exit!!");
                break;
            }
            else if(option == 1){
                TP04_1_PrimeNumber.main(args);
            }
            else if(option == 2){
                TP04_2_LuckyNumber.main(args);
            }
            else if(option == 3){
                TP04_3_ReversingNumber.main(args);
            }
            else if(option == 4){
                TP04_4_MoneyExchanges.main(args);
            }
            else if(option == 5){
                TP04_5_MaxAmoung8Numbers.main(args);
            }
            else if(option == 6){
                TP04_6_Shipping.main(args);
            }
        }while(option != 0);

        sc.close();
    }
}
