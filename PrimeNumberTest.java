import java.util.Scanner;
import java.util.stream.Collectors;
public class PrimeNumberTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nInput number to check whether it is prime number: ");
        TP04_1_PrimeNumber pn = new TP04_1_PrimeNumber(sc.nextInt());
        if(pn.isPrime()){
            System.out.printf("%d is prime number.\n",
            pn.getNum());
        }else{
            System.out.printf("%d is NOT prime number.",
            pn.getNum());
            System.out.printf("Because it is divisible by: %s\n\n",
            pn.getDivisibleNum().stream()
            .map(Object::toString)
            .collect(Collectors.joining(", ")));
        }
    sc.close();
    }
}
