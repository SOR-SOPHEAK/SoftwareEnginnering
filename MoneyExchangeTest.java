import java.util.Scanner;

public class MoneyExchangeTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nWelcome to program Money Exchanges!");
        System.out.println("1. Riels to Dollar");
        System.out.println("2. Riels to Thai Baht");
        System.out.println("3. Dollar to Riels");
        System.out.println("4. Dollar to Thai Bath");
        System.out.println("5. Thai bath to Riels");
        System.out.println("6. Exit");
        
        // System.out.print("Choose an option: ");
        // int option = sc.nextInt();
int option;
        TP04_4_MoneyExchanges moneyExchanges = new TP04_4_MoneyExchanges();
    do{    
        System.out.print("\nChoose an option: ");
        option = sc.nextInt();
        if(option == 1){
            System.out.print("Input money in Riels: ");
            moneyExchanges.setRiel(sc.nextDouble());
            System.out.println(moneyExchanges.getRiel()+" RIELS = " +moneyExchanges.RielToDollar()+" USD");
            
        }
        else if(option == 2){
            System.out.print("Input money in Riels: ");
            moneyExchanges.setRiel(sc.nextDouble());
            System.out.println(moneyExchanges.getRiel()+" RIELS = "+moneyExchanges.RielToBaht()+" BAHT");
            
        }
        else if(option == 3){
            System.out.print("Input money in Dollars: ");
            moneyExchanges.setDollar(sc.nextDouble());
            System.out.println(moneyExchanges.getDollar()+ " USD = "+moneyExchanges.DollarToRiel()+" RIEL");
        
        }
        else if(option == 4){
            System.out.print("Input money in Dollars: ");
            moneyExchanges.setDollar(sc.nextDouble());
            System.out.println(moneyExchanges.getDollar()+" USD = "+moneyExchanges.DollarTOBaht()+ " BAHT");
            
        }
        else if(option == 5){
            System.out.print("Input money in Thai Bahts: ");
            moneyExchanges.setBaht(sc.nextDouble());
            System.out.println(moneyExchanges.getBaht()+" BAHT = "+moneyExchanges.BahtToRiel()+" RIELS");
        }
        else if(option == 6){
            System.out.println("Exit!!");
            break;
        }
            
        
    }while(option!=6);

        sc.close();
    }

    
}