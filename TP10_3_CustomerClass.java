import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TP10_3_CustomerClass {
    private int number;
    private Date date;
    private String [] statusList = {"waiting to order", "ordering", "waiting for food"};
    private String status = statusList[0];

    public TP10_3_CustomerClass(Date date) {
      this.date = date;
    }
    //constructor
    public TP10_3_CustomerClass(int number, Date date){
        this.number = number;
        this.date = date;
    }
    public TP10_3_CustomerClass() {}

    // number
    public void setNumber(int number) { this.number = number; }
    public int getNumber() { return number; }

    // Entering Date
    public void setdate(Date date) { this.date = date; }
    public Date getdate() { return date; }

    // Status
    public void waitingToOrder() { this.status = this.statusList[0]; }
    public void ordering() { this.status = this.statusList[1]; }
    public void waitingForFood() { this.status = this.statusList[2]; }
    public String getStatus() { return status; }

    // Date input
    public static TP10_3_CustomerClass dataInput() throws ParseException {
      SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
      Scanner sc = new Scanner(System.in);
    
      System.out.print("\n\tInput number: ");
      int number = sc.nextInt();
      System.out.print("\tInput date(dd-MM-yyyy): ");
      String date = sc.next();
      java.util.Date sdate = fmt.parse(date);
      // TP10_4_QueueClass d = new TP10_4_QueueClass(fmt.parse(date)); 

    Calendar temp = Calendar.getInstance();
    temp.setTime(sdate);
    TP10_3_CustomerClass cus = new TP10_3_CustomerClass(number, temp.getTime());
    return cus;
    }

    // Data output
    public void dataOutput() {
    SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
    System.out.println("\n\tNumber        : " +this.number);
    System.out.println("\tDate         : " +fmt.format(this.date));
    System.out.println("\tStatus        : " +this.status);
  }

  public static void main(String[] args) throws ParseException {
    TP10_3_CustomerClass stm = TP10_3_CustomerClass.dataInput();
    stm.dataOutput();

    Calendar test = Calendar.getInstance();
    test.set(Calendar.DATE, 17);
    System.out.println("\n\t"+test.getTime());

    Date fifo = test.getTime();
    System.out.println("\t"+fifo);
    System.out.println();
  }

}
