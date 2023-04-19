public class TP04_2_LuckyNumber {
    private int number;
    
    public int getNumber(){
        return number;
    }
    public TP04_2_LuckyNumber(int num){
        number = num;
    }
    public boolean isLucky(){
        int tmpNum = number;
        int SumLastDigit = 0;
        int SumFirstDigit = 0;

        for(int i=0; i<3; i++){
            SumFirstDigit += tmpNum%10;
            tmpNum = tmpNum/10;
        }

        for(int i=0; i<3; i++){
            SumLastDigit += tmpNum%10;
            tmpNum = tmpNum/10;
        }
        if(SumLastDigit == SumFirstDigit){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isValid(){
        int counter = 0;
        int tmpNum = number;

        while(tmpNum != 0){
            tmpNum = tmpNum/10;
            ++counter;
        }
        if(counter == 6){
            return true;
        }
        else{
            return false;
        }
    }
    

}

