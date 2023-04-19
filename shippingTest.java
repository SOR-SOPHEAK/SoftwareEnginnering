import java.util.Scanner;

public class shippingTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x, y, z;
        System.out.println("\nProgram to calculate the minimum number of liters needed to refill at point B in order to reach point C\n");
        System.out.print("Distance between A to B(km): ");
         x = sc.nextInt();
        System.out.print("Distance between B to C(km): ");
         y = sc.nextInt();
        System.out.print("The weight to be loaded(kg): ");
        z = sc.nextInt();

        Shipping s = new Shipping(x, y, z);

        if(s.getZ() > 0 && s.getZ() <= 5000){
            s.distance(10);
        }
        else if(s.getZ() > 5000 && s.getZ() <= 10000){
            s.distance(20);
        }
        else if(s.getZ() > 10000 && s.getZ() <= 20000){
            s.distance(25);
        }
        else if(s.getZ() > 20000 && s.getZ() <= 30000){
            s.distance(35);
        }
        else{
            if(s.getZ() < 0){
                System.out.print("\nError weight inputted!\n");
            }
            else{
                System.out.print("\nThe ship cannot load with more than 30000kg.\n");
            }
        }
       sc.close();
    }
}
