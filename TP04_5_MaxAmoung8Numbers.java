import java.util.Scanner;

public class TP04_5_MaxAmoung8Numbers {
    private static int [] number = new int[8];

    void MaxNum(){
        System.out.println("\nFind max amoung 8 numbers.");
        System.out.println("Input a number: ");
        int max = number[0];

        Scanner sc = new Scanner(System.in);
        for(int i=0; i<number.length; i++){
            number[i] = sc.nextInt();

            if(number[i]>max){
                max = number[i];
            }
        }
        System.out.println("The maximum number is " +max);
        
        sc.close();
    
    }

    // public int getNum(){
    //     return number[];
    // }
}
