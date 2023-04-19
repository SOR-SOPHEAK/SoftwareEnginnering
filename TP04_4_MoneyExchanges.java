public class TP04_4_MoneyExchanges {
    private double riel, dollar, baht;
    // public TP04_4_MoneyExchanges(double Riel, double Dollar, double Baht){
    //     riel = Riel;
    //     dollar = Dollar;
    //     baht = Baht;
    // }
    public double getRiel(){return riel;}
    public double getDollar(){return dollar;}
    public double getBaht(){return baht;}

    public void setBaht(double baht){this.baht = baht;}
    public void setDollar(double dollar){this.dollar = dollar;}
    public void setRiel(double riel){this.riel = riel;}
//1. riel to dollar
    public double RielToDollar(){
        return this.riel/4000;
    }

//2. riel to baht
    public double RielToBaht(){
        return (this.riel*30)/4000;
    }
    
//3. dollar to riel    
    public double DollarToRiel(){
        return this.dollar*4000;
    }
    
//4. doolar to baht
    public double DollarTOBaht(){
        return this.dollar*30;
    }

//5. baht to riel
    public double BahtToRiel(){
        return (this.baht*4000)/30;
    }
       
}
