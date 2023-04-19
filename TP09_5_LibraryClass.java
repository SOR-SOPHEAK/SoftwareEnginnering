import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TP09_5_LibraryClass {
    Scanner sc=new Scanner(System.in);
    public HashMap<String, TP09_4_BookCategoryClass> lib;
    public HashMap<TP09_1_StudentClass, ArrayList<TP09_3_BookClass>> std_list;

    
    public TP09_5_LibraryClass(HashMap<String, TP09_4_BookCategoryClass> lib, HashMap<TP09_1_StudentClass, ArrayList<TP09_3_BookClass>> std_list) {
        this.lib = lib;
        this.std_list = std_list;
    }

    public void List_all_category(){
        if(lib.size()==0) System.out.println("\t***No data");
        else {
            System.out.println(lib.keySet());
        }

    }

    private boolean Exist_Category(String ctg){
        for(String s: lib.keySet()){
            if(ctg.equals(s)){
                return true;
            }
        }
        return false;
    }

    public void	List_book_byCategory(){
        System.out.print("\t\tEnter catagory: ");
        String ctg=sc.nextLine();

        if(Exist_Category(ctg)){
         lib.get(ctg).List_all_Book();
        }else {
            System.out.println("\t***This category does not exist");
        }
    }

    public void List_book_by_year(){
        int Year;
        while(true){
            System.out.print("\t\tEnter year: ");
            if(sc.hasNextInt()){
                Year=sc.nextInt();
                break;
            }else{
                sc.nextLine();
                System.out.println("\t***Year is...number, check again!!!!");
            }
        }
        
        System.out.printf("\t%-18s %-18s %-17s %-17s %-12s %-9s %s","Titile", "Author","ISBN", "Category","Status", "Amount", "Publish date");
        for(String K: lib.keySet()){
            for(TP09_3_BookClass book : lib.get(K).list){
                String str=book.publish_date.toString();
                String  s=str.split(" ")[2];
                int y=Integer.valueOf(s);

                if(y==Year){
                    System.out.println();
                    book.Display_Book_Info();
                }
            }
        }
    }

    public void List_available_Book(){
        
        System.out.printf("\t%-18s %-18s %-17s %-17s %-12s %-9s %s","Titile", "Author","ISBN", "Category","Status", "Amount", "Publish date");
        for(String K: lib.keySet()){
            for(TP09_3_BookClass book : lib.get(K).list){
                if(book.status){
                    System.out.println();
                    book.Display_Book_Info();
                }
            }
        }
    }

    public void Add_new_Book()throws Exception{
        TP09_3_BookClass book = new TP09_3_BookClass();
        book.Data_Input();
        
        boolean b=true;
        for(String K: lib.keySet()){
            for(TP09_3_BookClass B: lib.get(K).list){
                if(book.isbn==B.isbn){
                    System.out.println("\t***The ISBN: "+B.isbn+" has already existed. check again!!!!");
                    b=false;
                    break;
                }
            }
        }

        if(b){
            if(!Exist_Category(book.category)){
               lib.put(book.category, new TP09_4_BookCategoryClass(book.category));
            }
            lib.get(book.category).list.add(book);
            
            System.out.println("\t>>>Completed");
        }
    }

    public void Decrease_book_byISBN(){
        int isbn=ISBN_input();

        TP09_3_BookClass book = getBook_byISBN(isbn);
        Scanner sc=new Scanner(System.in);
        if(book!=null){
            System.out.printf("%-18s %-18s %-17s %-17s %-12s %-9s %s","Titile", "Author","ISBN", "Category","Status", "Amount", "Publish date");
            System.out.println();
            book.Display_Book_Info();
            System.out.println("\t\tEnter new anount ");
            int n=sc.nextInt();
            if(n>book.amount){
                System.out.println("\t***Can not decrease with this amount as books only have "+book.amount);
            }else{
                book.amount=book.amount-n;
                System.out.println("\t>>>Completed");
            }
        }else{
            System.out.println("\t***Not found.");
        }
    }

    public void Remove_book_byISBN(){

        int isbn=ISBN_input();
        
        boolean b=true;
        for(String K: lib.keySet()){
            int s1=lib.get(K).list.size();
            lib.get(K).Remove_Book_fromCategory(isbn);
            if(lib.get(K).list.size()!=s1){
                b=false;
            }
        }

        if(b)  System.out.println("\t***ISBN not found.");
        else System.out.println("\t>>>Book removed");

    }

    private int ISBN_input(){
        int isbn;
        while(true){
            System.out.print("\t\tEnter ISBN: ");
            if(sc.hasNextInt()){
                isbn=sc.nextInt();
                sc.nextLine();
                break;
            }else{
                System.out.println("\t***Invalid input");
                sc.nextLine();
            }
        }

        return isbn;
   
    }

    public void Add_new_copyBook(){
        Scanner sc=new Scanner(System.in);
        int isbn=ISBN_input();
        TP09_3_BookClass book = getBook_byISBN(isbn);
        if(book!=null){
            System.out.print("\t\tEnter amount of copy: ");
            int n=sc.nextInt();
            book.amount=book.amount+n;
            System.out.println("\t>>>New amount of book updated.");
        }else{
            System.out.println("\t***Not found.");
        }
    }

    public void Mark_Book_notAvailable_toBorrwo(){
        for(String K: lib.keySet()){
            for(TP09_3_BookClass book: lib.get(K).list){
                if(book.amount<=2){
                    book.status=false;
                }else book.status=true;
            }
        }
    }

    private TP09_3_BookClass getBook_byISBN(int isbn){
        for(String K: lib.keySet()){
            for(TP09_3_BookClass book: lib.get(K).list){
                if(book.isbn==isbn){
                    return book;
                }
            }
        }
        return null;
    }

    public void Let_student_borrowBook()throws Exception{
        Scanner sc=new Scanner(System.in);
        TP09_1_StudentClass std=new TP09_1_StudentClass();
        std.dataInput();
        int isbn;
        do{
            
            isbn=ISBN_input();

            if(getBook_byISBN(isbn)==null){
                System.out.println("\t\tEnter 0 to quit!!");
            }
            if(isbn==0)  break;
        
        }while(getBook_byISBN(isbn)==null);

        if(isbn!=0){
            boolean b=true;
            for(TP09_1_StudentClass K:std_list.keySet()){
               if(std.equals(K)){
                std_list.get(K).add(getBook_byISBN(isbn));
                b=false;
                break;
               }
            }

            if(b){
                std_list.put(std, 	new ArrayList<>());
                std_list.get(std).add(getBook_byISBN(isbn));
            }
        }

    }

    public void List_students_that_borrowBook(){
        System.out.printf("\t%-15s %-15s %-15s %-15s %-15s", "Name", "Tel", "Group", "Title", "ISBN");
        for(TP09_1_StudentClass K: std_list.keySet()){
            for(TP09_3_BookClass book: std_list.get(K)){
                System.out.println();
                K.dataOutput();
                System.out.printf("\t%-15s %-15s", book.title, book.isbn);
            }
        }
    }

    private boolean compare_student(TP09_1_StudentClass s1, TP09_1_StudentClass s2){
        if(!s1.getName().equals(s2.getName())) return false;
        else if(!s1.getDob().equals(s2.getDob())) return false;
        else if(s1.getTelephone()!=s2.getTelephone()) return false;
        else if(!s1.getCity().equals(s2.getCity())) return false;
        else if(!s1.getCountry().equals(s2.getCountry())) return false;
        else if(!s1.getGroup().equals(s2.getGroup()))   return false;

        return true;
    }

    public void Let_student_returnBook() throws Exception{
        TP09_1_StudentClass s=new TP09_1_StudentClass();
        s.dataInput();
        boolean b=true;
        for(TP09_1_StudentClass K: std_list.keySet()){
            if(compare_student(K, s)){
                std_list.remove(K);
                System.out.print("\t>>>Successful!!");
                b=false;
                break;
            }
        }
        if(b)   System.out.println("\t***Not found, try again or view the list students");
    }


    public static void main(String[] args)throws Exception{
        TP09_5_LibraryClass L = new TP09_5_LibraryClass(new HashMap<>(), new HashMap<>());
        Scanner sc=new Scanner(System.in);


        while(true){
            System.out.println("""
                \t============================================================================================
                \t|\t\tMenu
                \t--------------------------------------------------------------------------------------------
                \t|1. List all categories.
                \t|2. List books by categories.
                \t|3. List books by year.
                \t|4. List available books.
                \t|5. Add new books.
                \t|6. Decrease book by ISBN.
                \t|7. Remove book by ISBN.
                \t|8. Add new copies of book.
                \t|9. Let student borrow the book, max 5 books, max 1 week
                \t|   (if the student not yet return the previous borrowed books, he/she can't borrow more).
                \t|10. List students that borrowed the books.
                \t|11. Let student to return book.
                \t|12. Quit.
                \t============================================================================================
                    """);


            int n;

            while(true){
                System.out.print("\t==> Choose an option: ");
                if(sc.hasNextInt()){
                    n=sc.nextInt();
                    break;
                }else{
                    System.err.println("\t***Check input again");
                    sc.nextLine();
                }
            }

            if(n==1){
                System.out.println("\t----- 1. List all categories -----");
                L.List_all_category();
            }else if(n==2){
                System.out.println("\t----- 2. List books by categories -----");
                L.List_book_byCategory();
            }else if(n==3){
                System.out.println("\t----- 3. List books by year -----");
                L.List_book_by_year();
            }else if(n==4){
                System.out.println("\t----- 4. List available books -----");
                L.List_available_Book();
            }else if(n==5){
                System.out.println("\t----- 5. Add new books -----");
                L.Add_new_Book();
            }else if(n==6){
                System.out.println("\t----- 6. Decrease book by isbn -----");
                L.Decrease_book_byISBN();
            }else if(n==7){
                System.out.println("\t----- 7. Remove book by isbn -----");
                L.Remove_book_byISBN();
            }else if(n==8){
                System.out.println("\t----- 8. Add new copies of book -----");
                L.Add_new_copyBook();
            }else if(n==9){
                System.out.println("\t----- 10. Let student borrow the book, max 5 books, max 1 week -----\n\t\tif the student not yet return the previous borrowed books, he/she can't borrow more)");
                L.Let_student_borrowBook();
            }else if(n==10){
                System.out.println("\t----- 11. List students that borrowed the books -----");
                L.List_students_that_borrowBook();
            }else if(n==11){
                System.out.println("\t -----12. Let student to return book -----");
                L.Let_student_returnBook();
            }else if(n==12){    
                System.out.println("\t -----Quit -----");
                break;
            }else System.out.println("\t>>>You chose the wrong option!!!!!");
            

            L.Mark_Book_notAvailable_toBorrwo();

            System.out.println("\n\t------------------------------------------------------------------------------------------------");
        }
        

        sc.close();

    }
}
