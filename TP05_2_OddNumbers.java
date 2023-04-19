public class TP05_2_OddNumbers {
    private int num = 500;

    public int getOddNum(){
        return num;
    }

    // public TP05_2_OddNumbers(int num){
    //     this.num = num;
    // }

    public void isOdd(){
        for(int i=1; i<=num; i++){
            if(i%2 == 0){
            }
            else{
                System.out.printf("%d ", i);
            }
        }
        
    }

    public static void main(String[] args) {
        System.out.println("\nProgram to display odd number located between 0 and 500:\n");
        
        TP05_2_OddNumbers odd = new TP05_2_OddNumbers();

        odd.isOdd();
    }
}
