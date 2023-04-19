import java.util.ArrayList;

public class TP09_4_BookCategoryClass {
    public String ctg;
    public ArrayList<TP09_3_BookClass> list ;

    public TP09_4_BookCategoryClass(String ctg) {
        this.ctg = ctg;
        list=new ArrayList<>();
    }

    public boolean Exist_ISBN(int isbn){
        for(TP09_3_BookClass book: list ){
            if(book.isbn==isbn){
                return true;
            }
        }
        return false;
    }

    public void Add_book_toCategory(TP09_3_BookClass book){
        if(!Exist_ISBN(book.isbn)){
            list.add(book);
            System.out.println("\t***Book added");
        }else System.out.println("\t***Book has already existed.");
    }

    public void Remove_Book_fromCategory(int isbn){
        if(Exist_ISBN(isbn)){
            for(TP09_3_BookClass book: list){
                if(book.isbn==isbn){
                    list.remove(book);
                    break;
                }
            }
            System.out.println("\t***Book removed");
        }else{
            System.out.println("\t***The boook ISBN: "+isbn+" does not exit.");
        }
    }

    public void List_all_Book(){
        System.out.printf("\t%-18s %-18s %-17s %-17s %-12s %-9s %s","Titile", "Author","ISBN", "Category","Status", "Amount", "Publish date");
        for(TP09_3_BookClass book: list){
            System.out.println();
            book.Display_Book_Info();
        }
    }

    public int Count_Book_inCategory(String ctg){
        return list.size();
    }

    public void Find_Book_inCategory(String title, int isbn) {
        boolean b=true;
        for(TP09_3_BookClass book: list){
            if(title.toUpperCase().equals(book.title.toUpperCase())  || isbn==book.isbn){
                System.out.printf("%-15s %-15s %-15s %-15s %s","Titile", "Author","ISBN", "Category", "Published date");
                book.Display_Book_Info();
                b=false;
                break;
            }
        }
        if(b){
            System.out.println("\t***Not found.");
        }
    }

}
