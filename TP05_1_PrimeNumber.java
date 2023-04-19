import java.util.Scanner;

public class TP05_1_PrimeNumber {
    private int num;

    public int getPrime(){
        return num;
    }
    public TP05_1_PrimeNumber(int num){
        this.num = num;
    }

    public boolean isPrime(){
    int flage=0;
        if(num<2){
            return false;
        }
        else{
            for(int i=2; i<=num; i++){
                flage = 0;
                for(int j=2; j<=i/2; j++){
                    if(i%j==0){
                     flage ++;
                    }
                }
                if(flage==0){
                    System.out.printf("%d ",i);
                }
            }
        return true;   
        }
    }  

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nInput number to list prime numbers from 2 to it: ");

        TP05_1_PrimeNumber Prime = new TP05_1_PrimeNumber(sc.nextInt());
        
        Prime.isPrime();

        System.out.println("is prime number.\n");

        sc.close();
    }
   
}



