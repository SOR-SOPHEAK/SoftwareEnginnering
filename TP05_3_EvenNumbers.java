import java.util.Scanner;

public class TP05_3_EvenNumbers {
    private static int num = 500;
    private int A;

    public TP05_3_EvenNumbers(int A){
        this.A = A;
    }
    public void isEven(){
        for(int i=A; i<num; i++){
            if(i%2 != 0){
                
            }
            else{
                System.out.printf("%d ", i);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\nProgram in Java to display enven numbers.\n");
        System.out.print("Input A value to display even numbers between A and 500 where (0<A<500): ");
        TP05_3_EvenNumbers even = new TP05_3_EvenNumbers(sc.nextInt());

        even.isEven();

        sc.close();
    }
}
