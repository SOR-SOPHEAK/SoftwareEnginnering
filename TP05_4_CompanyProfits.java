import java.util.Scanner;

public class TP05_4_CompanyProfits {
   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = 12;
        float totalProfit = 0;
        System.out.println("\nProgram Java to calculate company profits for 12 months.\n");
        for(int i=0; i<m; i++){
            System.out.printf("Profit for month %d: ", i+1);
            float profit = sc.nextFloat();
        
           totalProfit = totalProfit + profit;
        }
        System.out.printf("\nTotal profits for 12 months: %.2f \n\n", totalProfit);
    sc.close();
    }
    
}
