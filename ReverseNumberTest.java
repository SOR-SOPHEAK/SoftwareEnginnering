import java.util.Scanner;

public class ReverseNumberTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nProgram for reversing a 4 digits number.");
        System.out.print("Please input 4 digits number: ");
        int num = sc.nextInt();

        TP04_3_ReversingNumber reverse = new TP04_3_ReversingNumber(num);

        if(!reverse.isValid()){
            System.out.println("\nError: invalid number, please input only 4 digits number.");
        }
        else{
            System.out.println("\nAfter reverse: "+reverse.isReverse());
        }
        sc.close();
    }
}
