import java.util.Scanner;

public class TP05_6_StringMirroring {
    private String string;

    public TP05_6_StringMirroring(String string){
        this.string = string;
    }

    public void MirrorString(){
        char ch[] = string.toCharArray();

        String rev = "";

        for(int i=ch.length-1; i>=0; i--){
            rev += ch[i];
        }
        System.out.println(string+""+rev+"\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nProgram Java to create a reverse copy of given string.");
        System.out.print("\nPlease enter a word: ");
        TP05_6_StringMirroring mirroring = new TP05_6_StringMirroring(sc.next());
        mirroring.MirrorString();

        sc.close();
    }
}
