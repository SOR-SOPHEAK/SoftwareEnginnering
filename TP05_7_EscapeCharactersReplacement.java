import java.util.Scanner;

public class TP05_7_EscapeCharactersReplacement {
    String newLine = "\\n";
    String  tab = "\\t";
    String slash = "\\\\";
    String commentLine = "//";
    String smile = ":)";
    String string;

    public TP05_7_EscapeCharactersReplacement(String string){
        this.string = string;
    }

    public void RelaceCharacter(){
        
        string = string.replace(newLine, "(new_line)");
        string = string.replace(tab, "(tab)");
        string = string.replace(slash, "(slash)");
        string = string.replace(commentLine, "(comment_line)");
        string = string.replace(smile, "(smile)");
        System.out.println("\n");
        System.out.println(string);
        System.out.println("\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nProgram in java to replace all escape characters\n");
        System.out.print("Please enter a sentence: ");
        TP05_7_EscapeCharactersReplacement eschar = new TP05_7_EscapeCharactersReplacement(sc.nextLine());
        
        eschar.RelaceCharacter();

        sc.close();
    }
}

