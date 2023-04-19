import java.util.Scanner;

public class TP05_CE_2_MessageColderClass {
    String newLine = "\\n";
    String  tab = "\\t";
    String slash = "\\\\";
    String commentLine = "//";
    String smile = ":)";

    public String MessageEncoder(String string){
        string = string.replace(newLine, "(new_line)");
        string = string.replace(tab, "(tab)");
        string = string.replace(slash, "(slash)");
        string = string.replace(commentLine, "(comment_line)");
        string = string.replace(smile, "(smile)");
       
        return string;
    }
    public String MessageDecoder(String string){
        string = string.replace("(new_line)", newLine);
        string = string.replace("(tab)", tab);
        string = string.replace("(slash)", slash);
        string = string.replace("(comment_line)", commentLine);
        string = string.replace("(smile)", smile);
        
        return string;
    }

    public static void main(String[] args) {
        String str;

        Scanner sc = new Scanner(System.in);
        TP05_CE_2_MessageColderClass m = new TP05_CE_2_MessageColderClass();
        System.out.println("\nProgram in java to replace all escape characters\n");
        System.out.print("Please enter a sentence: ");
        str = m.MessageEncoder(sc.nextLine());
       
        System.out.print("\nDecode message: "+m.MessageDecoder(str));

        sc.close();
    }
}
