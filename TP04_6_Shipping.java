public class TP04_6_Shipping {
    
        private int i, j, k; 
    // i = distance A from B, j = distance B from C, k = weight to be loaded
    public TP04_6_Shipping(int x, int y, int z) {
        this.i = x;
        this.j = y;
        this.k = z;
    }
    int getX(){return i;}
    int getY(){return j;}
    int getZ(){return k;}
    
    void distance(int litter){
        int litterAB; //liter that spend on distance from A to B
        int litterBC; //liter that spend on distance from B to C
        int leftover; //liter that left after ship leave from A and reach B
        int res;      //liter to refill at B to reach C

        litterAB = i * litter;    
        litterBC = j * litter;    
        if(litterAB > 5000){
            System.out.print("\nNot enough liter to reach point B!\n");
        }
        else{
            if(litterBC <= 5000){
                leftover = 5000 - litterAB;
                if(leftover < litterBC) {
                    res = litterBC - leftover;
                    System.out.printf("\nThe minimum number of liters needed to refill at point B is: %dL\n",res);
                }
                else{
                    System.out.printf("\nSince the ship remain %dL after reaching point B and the amount of liter needed to reach point C is %dL",leftover, litterBC);
                    System.out.println("\nSo the ship doesn't need to be refill");
                }
            }
            else{
                System.out.print("\nNot enough liter to reach point C!");
            }
        }
    }
    
    
}
