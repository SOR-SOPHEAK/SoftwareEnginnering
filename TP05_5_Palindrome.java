import java.util.Scanner;

public class TP05_5_Palindrome {
    private String string;
    private String reverseString = "";

    public TP05_5_Palindrome(String string){
        this.string = string;
    }

    public String getString(){
        return string;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof TP05_5_Palindrome){
            TP05_5_Palindrome Palindrom = (TP05_5_Palindrome)obj;
            return string.equals(Palindrom.string);
        }
        return false;
    }

    public void IsRevPalindrome(){
        StringBuffer sb = new StringBuffer(string);
        sb.reverse();

        String rev = sb.toString();

         if(string.equals(rev)){
            System.out.println(rev+" is a Palindrome.\n");
         }
         else{
            System.out.println(rev+" is NOT a Palindrome.\n");
         }
    }

    public void IsLoopPalindrome(){
        for(int i=string.length()-1; i>=0; i--){
            reverseString = reverseString + string.charAt(i);
        }
        if(string.equals(reverseString)){
            System.out.println(string+" is a Palindrome.\n");
        }
        else{
            System.out.println(string+" is NOT a Palindrome.\n");
        }
    }
    public static void main(String[] args) {
        String r = "REV";
        String l = "LOOP";
       // String str;

        Scanner sc = new Scanner(System.in);

        System.out.print("\nPlease gives a word to check: ");
        TP05_5_Palindrome p = new TP05_5_Palindrome(sc.next());

        System.out.print("Choose method (REV, LOOP): ");
        String check = sc.next();
    
            if(check.equals(r)){
                p.IsRevPalindrome();
            }
            else if(check.equals(l)){
                p.IsLoopPalindrome();
            }
            else{
                System.out.println("Worning! Choose the wrong method.");
            }
        sc.close();
    }
}
