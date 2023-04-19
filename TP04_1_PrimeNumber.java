import java.util.LinkedList;

public class TP04_1_PrimeNumber {
    private int num;
    private LinkedList<Integer> divisibleNum = new LinkedList<>();
    public TP04_1_PrimeNumber(int num){
        this.num = num;
    }
    public int getNum(){return num;}
    public LinkedList<Integer> getDivisibleNum(){return divisibleNum;}
    public boolean isPrime(){
        for(int i=2; i<=num/2; i++){
            if(num%i == 0){
                divisibleNum.add(i);
            }
        }
        return divisibleNum.size()==0;
    }
}

