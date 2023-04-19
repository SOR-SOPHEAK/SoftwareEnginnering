import java.lang.invoke.StringConcatFactory;
import java.security.Provider;
import java.util.function.ObjDoubleConsumer;

public class Product {
    private int pnumber;
    private String pname;
    private float price;
    private int pinstock;
    private int index;
    public Product(int pnumber, String pname, float price, int pinstock) {
        this.pnumber = pnumber;
        this.pname = pname;
        this.price = price;
        this.pinstock = pinstock;
    }
    public int getPnumber() {
        return pnumber;
    }
    public void setPnumber(int pnumber) {
        this.pnumber = pnumber;
    }
    public String getPname() {
        return pname;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getPinstock() {
        return pinstock;
    }
    public void setPinstock(int pinstock) {
        this.pinstock = pinstock;
    }

    public int getIndex(){
        return index;
    }
    public void setIndex(int index){
        this.index = this.index;
    }
    
    @Override
    public String toString(){
        return "\t"+pnumber+"\t"+pname+"\t"+price+"\t"+pinstock;
    }
}
