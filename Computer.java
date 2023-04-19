import java.net.PortUnreachableException;
import java.rmi.ConnectIOException;
import java.sql.Date;

public class Computer {
    private String serialNumber;
    public String purchaseDate;
    public boolean isGood;
    
    public Computer(String serialNumber, String purchaseDate) {
        this.serialNumber = serialNumber;
        this.purchaseDate = purchaseDate;
    }

    public Computer(String serialNumber, String purchaseDate, boolean isGood) {
        this.serialNumber = serialNumber;
        this.purchaseDate = purchaseDate;
        this.isGood = isGood;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean isGood) {
        this.isGood = isGood;
    }
    
}