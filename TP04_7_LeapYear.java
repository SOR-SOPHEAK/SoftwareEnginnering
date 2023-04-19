import java.util.Scanner;

public class TP04_7_LeapYear {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("/nProgram to test leap year.");
        System.out.print("Input year: ");
        int year = sc.nextInt();

        if(year%4 == 0 && year%100 != 0){
            System.out.println("Year "+year+" is Leap year.");
        }
        else if(year<1){
            System.out.println("Error.");
        }
        else{
            System.out.println("Year "+year+" is NOT Leap year.");
        }
        sc.close();
    }
}
