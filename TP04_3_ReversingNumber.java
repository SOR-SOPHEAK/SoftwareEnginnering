public class TP04_3_ReversingNumber {
    private int num;

    public int getNum(){
        return num;
    }
    public TP04_3_ReversingNumber(int number){
        num = number;
    }
    public int isReverse(){
        int tmpNum = num;
        int reverse = 0;
        int remainder;

        for(int i=0; i<4; i++){
            remainder = tmpNum%10;
            reverse = reverse*10 + remainder;
            tmpNum = tmpNum/10;
        }
        return reverse;
    } 

    public boolean isValid(){
        int counter = 0;
        int tmpNum = num;
         while(tmpNum != 0){
            tmpNum = tmpNum/10;
            ++counter;
         }

         if(counter == 4){
            return true;
         }
         else{
            return false;
         }
    }
}
