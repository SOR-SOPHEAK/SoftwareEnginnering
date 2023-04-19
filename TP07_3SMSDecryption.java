import java.util.Scanner;

public class TP07_3SMSDecryption {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TP07_2SMS_Encrypt listOfSMS1 = new TP07_2SMS_Encrypt();
        TP07_3SMSDecryption listOfSMS = new TP07_3SMSDecryption();

        int option;

        do{
            System.out.println("\n\t1. List all SMSes"+
            "\n\t2. View SMS Detail (decrypt content using password)"+
            "\n\t3. View readable SMSes (all SMS that can be decrypted using given password)"+
            "\n\t4. Remove SMSes by index"+
            "\n\t5. Quit");
            System.out.print("\n\t=> Chose an option: ");
            option = sc.nextInt();

            switch(option){
                case 1:
                    listOfSMS1.ListSMS();
                    break;
                case 2:
                    System.out.print("\n\tDetail SMS by index: \t");
                    int index = sc.nextInt();
                    if(index>listOfSMS1.SizeOfArray() || index<0){
                        System.out.println("\t****No SMS by this index.");
                    }
                    else{
                        System.out.print("\n\tInput Secrete Key: \t");
                        String sk = sc.nextLine();
                        sk = sc.nextLine();

                        if(!listOfSMS1.FindSecreteKey(sk)){
                            System.out.println("\n\t****This Secret Key doesn't exist!!");
                        }
                        else{
                            listOfSMS1.ViewSMSDetail(listOfSMS1.FindSMSByIndex(index-1));
                            listOfSMS1.upDateStatus(true, index);
                        }
                    }
                    break;
                    
                case 3:
                    System.out.print("\n\tInput Secrete Key: \t");
                    String sk = sc.next();

                    if(!listOfSMS1.FindSecreteKey(sk)){
                        System.out.println("\t****This Secret Key doesn't exist!!");
                    }
                    else{
                        listOfSMS1.ListingBySecreteKey(sk);
                    }
                    break;
                case 4:
                    System.out.print("\n\tInput index to remove : \t");
                    index = sc.nextInt();
                    if(index>listOfSMS1.SizeOfArray() || index<0){
                        System.out.println("\t****Index NOT Found!!");
                    }
                    else{
                        listOfSMS1.Remove(listOfSMS1.FindSMSByIndex(index-1));
                        System.out.println("\t ***Remove Successful!!");
                    }
                    break;
                case 5:
                System.out.println("\tQuit!!!");
                    System.exit(1);
                    break;
            }
        }while(option != 5);
        sc.close();
    }
    
}

