import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class TP06_3_PCinDICEsLab {
    private ArrayList<Computer> computerList = new ArrayList<>();

    Computer pc1 = new Computer("111", "12/02/2010", false);
    Computer pc2 = new Computer("222", "10/10/2010", false);
    Computer pc3 = new Computer("333", "28/01/2011", true);
    Computer pc4 = new Computer("444", "19/03/2015", false);
    Computer pc5 = new Computer("555", "01/02/2019", true);
    Computer pc6 = new Computer("666", "23/07/2020", true);
    Computer pc7 = new Computer("777", "11/6/2019", true);
    Computer pc8 = new Computer("888", "23/11/2013", false);
    Computer pc9 = new Computer("999", "30/09/2018", false);
    Computer pc10 = new Computer("1010", "12/02/2021", true);

    public TP06_3_PCinDICEsLab(){
        computerList.add(pc1);
        computerList.add(pc2);
        computerList.add(pc3);
        computerList.add(pc4);
        computerList.add(pc5);
        computerList.add(pc6);
        computerList.add(pc7);
        computerList.add(pc8);
        computerList.add(pc9);
        computerList.add(pc10);
    }


    public void listAllPCs(){
        System.out.println("\n\tSerialNumber\tPurchaseDate\tStatus");
        System.out.println("\t-----------------------------------------");
        for(Computer pc: computerList){
            String p = "";
            if(pc.isGood) p = "Good";
            else p = "Damaged";
            System.out.println("\t\t"+pc.getSerialNumber()+"\t"+pc.getPurchaseDate()+"\t"+p);
        }
    }
    
    public void listAllGoodDamagedPCs(Boolean isGood){
        System.out.println("\n\tSerialNumber\tPurchaseDate\tStatus");
        System.out.println("\t-----------------------------------------");

        for(Computer pc: computerList){
            if(isGood == pc.isGood){
                String p = "";
                if(pc.isGood) p = "Good";
                else p = "Damaged";
                System.out.println("\t\t"+pc.getSerialNumber()+"\t"+pc.getPurchaseDate()+"\t"+p);
            }
        }
    }

    public int indexPCbySerialNum(String serialN){
        int index=0;
        for(int i=0; i<computerList.size(); i++){
            if(serialN.equals(computerList.get(i).getSerialNumber())) index = i;
        }
        return index;
    }

    public void MarkPCdamage(){
        Scanner sc = new Scanner(System.in);
        System.out.print("\tInput number of PC that you want to mark: ");
        int numM = sc.nextInt();

        for(int i=0; i<numM; i++){
            System.out.print("\tInput serial number to mark as damaged: ");
            String serialN = sc.next();
            computerList.get(indexPCbySerialNum(serialN)).isGood = false;
        }
    }

    public void MarkPCgood(){
        Scanner sc = new Scanner(System.in);
        System.out.print("\tInput number of PC that you want to mark: ");
        int numM = sc.nextInt();

        for(int i=0; i<numM; i++){
            System.out.print("\tInput serial number to mark as good: ");
            String serialN = sc.next();
            computerList.get(indexPCbySerialNum(serialN)).isGood = true;
        }
    }

    

    public void Menu(){
        System.out.println("\n\t==================================");
        System.out.println("\t\t\tMenu");
        System.out.println("\t==================================");
        System.out.println("\t1. List all PCs.");
        System.out.println("\t2. List all damages PCs.");
        System.out.println("\t3. List all good PCs.");
        System.out.println("\t4. Mark a PC as damaged.");
        System.out.println("\t5. Mark a PC as not damaged.");
        System.out.println("\t6. Quit.");
        System.out.println("\t\t--------x--------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TP06_3_PCinDICEsLab pc = new TP06_3_PCinDICEsLab();

        int option;

        do{
            pc.Menu();
            System.out.print("\n-->  Chose an option: ");
            option = sc.nextInt();

            switch(option){
                case 1:
                //list all pcs
                    pc.listAllPCs();
                    break;
                case 2:
                //list all damage pcs
                    pc.listAllGoodDamagedPCs(false);;
                    break;
                case 3:
                //list all good pcs
                    pc.listAllGoodDamagedPCs(true);
                    break;
                case 4:
                //Mark a PC as damaged
                    pc.MarkPCdamage();
                    break;
                case 5: 
                //Mark a PC as not damaged
                    pc.MarkPCgood();
                    break;
                case 6:
                //quit
                    System.out.println("Quit!!");
                    System.exit(1);
                    break;
            }
        }while(option != 6);
    }
}
