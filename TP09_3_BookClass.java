import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TP09_3_BookClass {
    public String title;
    public String author;
    public int isbn; 
    public String category;
    public String publish_date;
    public int amount;
    public boolean status;

    private int valid_dateInput(String s1) {
        if (s1.matches("[0-9]{2}[ ]{1}[0-9]{2}[ ]{1}[0-9]{4}")) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
            sdf.setLenient(false);
            try {
                sdf.parse(s1);
                return 1;
            } catch (ParseException e) {
                return -1;
            }
        } else
            return -1;
    }
    
    public void Data_Input() throws ParseException{
        Scanner sc=new Scanner(System.in);

        System.out.println("\n\tEnter information about the book.");
        System.out.print("\t\tTitle: ");
        title=sc.nextLine();
        while(true){
            System.out.print("\t\tISBN: ");
            if(sc.hasNextInt()){
                isbn=sc.nextInt();
                if(String.valueOf(isbn).length()!=7 && String.valueOf(isbn).length()!=9){
                    System.out.println(">>>ISBN must be equal 7 digits or 9 digits!!!!");
                    System.out.println("\t***Try again.");   
                }else break;
            }else{
                sc.nextLine();
                System.out.println("\t***>>>Check ISBN again!!!!!");
            }
        }

        System.out.print("\t\tAuthor: ");
        sc.nextLine();
        author=sc.nextLine();

        while(true){
            System.out.print("\t\tPublish date(DD MM YYYY): ");
            publish_date=sc.nextLine();
            if(valid_dateInput(publish_date)==1){

                ParsePosition ps=new ParsePosition(0);
                SimpleDateFormat simp=new SimpleDateFormat("dd-MM-yyyy");
                Date cuurent_date=simp.parse(simp.format(new Date()));//the current date

                Date d=simp.parse(publish_date.replaceAll(" ", "-"), ps);
                if(d.compareTo(cuurent_date)>0){
                    System.out.println("\t***Publish ahead of time? WOW Try again!!!");
                }else{
                    break;
                }
            }else{
                System.out.println("\t***Invalid date format!!!\n\tTry again");
            }
        }

        System.out.print("\t\tCategory: ");
        category=sc.nextLine();
        while(true){
            System.out.print("\t\tAmount of book: ");
            if(sc.hasNextInt()){
                amount=sc.nextInt();
                if(amount<0){
                    System.out.println("\t****Amount must be positive");
                }else break;
            }else{
                sc.nextLine();
                System.out.println("\t***Amount must be integer number!!!");
            }
        }

        if(amount>2) status=true;
    }

    public void Display_Book_Info(){
        System.out.printf("\t%-18s %-18s %-17d %-17s %-12s %-9d %s",title, author, isbn, category,status ? "Free": "Not free",amount, publish_date);
    }

    public static void main(String[] args) throws Exception {
        TP09_3_BookClass b = new TP09_3_BookClass();

        b.Data_Input();

        b.Display_Book_Info();
        
    }
}







