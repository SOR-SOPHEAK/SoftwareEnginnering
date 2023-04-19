import java.util.Scanner;

public class LuckyNumberTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nProgram for testing for lucky number.");
        System.out.print("Please input 6 digit number: ");
        int num = sc.nextInt();

        TP04_2_LuckyNumber luckynum = new TP04_2_LuckyNumber(num);

        if(!luckynum.isValid()){
            System.out.println("\nInvalid input number, plase input only 6 digits number.");
        }
        else{
            if(luckynum.isLucky()){
                System.out.println(luckynum.getNumber()+" is lucky number.");
            }
            else{
                System.out.println(luckynum.getNumber()+ " is NOT lucky number.");
            }
        }
        sc.close();
    }

}
