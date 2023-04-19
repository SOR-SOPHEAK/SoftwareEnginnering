import java.util.Scanner;

public class TP05_CE_1_RangeUlitClass {
    private int start;
    private int end;
    private int step;
    String result;

    // public TP05_CE_1_RangeUlitClass(int start, int end, int step){
    //     this.start = start;
    //     this.end = end;
    //     this.step = step;
    // }
 
    public void RangeUtil(){
        int i;
        if(start<end){
            System.out.print("Range of numbers : ");
            for(i=start; i<=end; i+=step){
                System.out.println(i);
            }
            
        }
        else if(start>end){
            for(i=start; i<=end; i--){
                i = i+step;
            }
            System.out.printf("Range of numbers : %d ",i);
        }   
    }

    // @Override
    // public String toString(){
        
    //     return "Range of numbers "+result;
    // }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start, end, step;

        TP05_CE_1_RangeUlitClass range = new TP05_CE_1_RangeUlitClass();

        System.out.println("Range of number");
        System.out.print("Input starting number: ");
        start = sc.nextInt();

        System.out.print("Input ending number: ");
        end = sc.nextInt();

        System.out.print("Input increasing step: ");
        step = sc.nextInt();

        range.RangeUtil();

        sc.close();
    }
}
