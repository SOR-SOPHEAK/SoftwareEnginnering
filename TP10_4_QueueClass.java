import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TP10_4_QueueClass {
  private Queue<TP10_3_CustomerClass> CusQueue = new LinkedList<>();
  private Object re;  
  public TP10_4_QueueClass(){
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4c?user=root&password=")) {
      var stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM customers WHERE statusID = 1");
      while(rs.next()) {
        TP10_3_CustomerClass cus = new TP10_3_CustomerClass();
        Date date = new Date(rs.getTimestamp(2).getTime());  
        cus.setdate(date);
        cus.setNumber(rs.getInt(1));
        cus.waitingToOrder();
        this.CusQueue.add(cus);
      }
    }catch (SQLException e) {
      e.printStackTrace();
    }
  }

    public boolean canAddNewCus() {
      if(this.countAllCustomer() < 100) return true;
      else return false;
    }

    public static SimpleDateFormat sqlWithTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat ReceiptFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void addNewCus() {
      try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4c?user=root&password=")) {
        if(this.canAddNewCus()) {
          var stmt = conn.createStatement();
            
          // get current date and time (date entering)
          Calendar now = Calendar.getInstance();
          java.sql.Timestamp currentDate = new java.sql.Timestamp((now.getTime()).getTime());
          Date date = new Date(currentDate.getTime()); 
          String enterdate = TP10_4_QueueClass.sqlWithTime.format(date);
          
          String sql = "INSERT INTO `customers` (number, date, statusID) VALUES (null, '"+ enterdate +"' , 1)";
          stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
          var rs = stmt.getGeneratedKeys();
          rs.next();
          TP10_3_CustomerClass cus = new TP10_3_CustomerClass();
          cus.setNumber(rs.getInt(1));
          cus.setdate(date);
        
          System.out.println("\n\tNew Customer is added.\n");
          System.out.printf("\tCustomer No.  : %05d\n", cus.getNumber());
          System.out.println("\tDate\t\t: " +ReceiptFormat.format(date));
          System.out.println("\tStatus        : " +cus.getStatus());
          
          this.CusQueue.add(cus);
        } else System.out.println("\tSorry, cannot be add any new customers since there are 100 customers need to be served.");
      }catch (SQLException e) {
          e.printStackTrace();
        }
    }
    
    // Remove customer
    public TP10_3_CustomerClass removeCustomer() {
      return this.CusQueue.poll();
    }
    
    // check card number is valid or not (16 digits)
    public boolean isCardNumberValid(String cardNnumber) {
      String temp = "";
        for(int i=0; i<cardNnumber.length(); i++) {
          if(cardNnumber.charAt(i) != ' ') {
            temp += cardNnumber.charAt(i);
          }
        }
    
        if(temp.length() != 15 && temp.length() != 16) {
          return false;
        } else {
          for(int i=0; i<temp.length(); i++) {
            if(temp.charAt(i) < 48 || temp.charAt(i) > 57) {
              return false;
            }
          }
        }
        return true;
      }
    
      // convert card number to hidden
      public String hideCardNumber(String cardNumber){
        String result = "**** **** **** ";
        for(int i=15; i<cardNumber.length(); i++) {
          result += cardNumber.charAt(i);
        }
        return result;
      }
    
      // Second to Time as String
      public static String secToTime(long second) {
        int hour = (int) (second / 3600);
        int min = (int)((second % 3600) / 60);
        int sec = (int)((second % 3600) % 60);
        String result = String.format("%02d", hour)+ ":" +String.format("%02d", min)+ ":" +String.format("%02d", sec);
        return result;
      }
    
      // formating for time
      public static String timeFormat = "HH:mm:ss";
      public static SimpleDateFormat calToTimeFormat = new SimpleDateFormat(timeFormat);
      public static SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
    
      // Serve the customer
      public void serveCustomer() {
        TP10_3_CustomerClass cus = new TP10_3_CustomerClass();
        Scanner sc = new Scanner(System.in);
        cus = this.CusQueue.poll();
    
        if(cus == null) {
          System.out.println("\tAll customers are served already.\n");
        } else {
          Util.clsScr();
          System.out.printf("\n\n\tServing Customer No. %05d\n",cus.getNumber());
          // get start serving time
          Calendar startServingTime = Calendar.getInstance();
          String startTime = calToTimeFormat.format(startServingTime.getTime());
          System.out.println("\tStart serving time: " +startTime);
    
          // what the customer want to order
          System.out.print("\n\tWhat do you want to order?   -> \t");
          String ordered = sc.nextLine();
    
          // price of the food
          double payAmount = Util.getDouble("\n\tAmount to pay ($): ", "\tInput only position number.\n");
          System.out.print("\tPay by (1: cash, 2: credit card): ");
          int payBy = Util.getOnly1or2("\tInput only 1 or 2.\n");
    
          // if case paying by Credit-Card
          String cardNumber = null;
          String passCode = null;
          if(payBy == 2) {
            System.out.print("\tIn case credit card, please input credit card number: ");
            while(true) {
              cardNumber = sc.nextLine();
              if(!this.isCardNumberValid(cardNumber)) {
                System.out.print("\tInvalid credit card, input again(16digits): ");
              } else break;
            }
            System.out.print("\tpasscode: ");
            passCode = sc.nextLine();
          }
    
          // display Receipt
          Util.enterPause("\n\tPress Enter to get receipt...");
          Util.clsScr();
          System.out.println("\n\tReceipt");
          System.out.println("\t---------------------------------------");
          System.out.printf("\tCustomer No. %05d\n", cus.getNumber());
          System.out.printf("\t%s = $%.2f\n", ordered, payAmount);
          if(payBy == 2) System.out.println("\t(In credit card " +hideCardNumber(cardNumber)+ ")");
          System.out.println("\n\t------------ Thanks you!!! ------------");
    
          // end serving time
          Calendar issueDate = Calendar.getInstance();
          String issueDateStr = sqlWithTime.format(issueDate.getTime());
          String endTime = calToTimeFormat.format(issueDate.getTime());
    
          // calculation to find duration time
          long durationMili = issueDate.getTime().getTime() - startServingTime.getTime().getTime();
          long durationSec = TimeUnit.SECONDS.convert(durationMili, TimeUnit.MILLISECONDS);
          String duration = secToTime(durationSec);
    
          System.out.println("\n\tIssue date: " +ReceiptFormat.format(issueDate.getTime()));
          System.out.println("\t---------------------------------------");
          System.out.println("\n\tEnd serving time: " +endTime);
          System.out.println("\tServing duration: " +duration);
    
          try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4c?user=root&password=")) {
            if(payBy == 2) {
              cardNumber = "'" +cardNumber+ "'";
              passCode = "'" +passCode+ "'";
            }
            var stmt = conn.createStatement();
            String query = "INSERT INTO orderdetails (number, foodDrinks, prices, payMetID, "
                      +"cardNumber, passCode, startServingTime, endServingTime, duration, issueDate)"
                      +"values ('" +cus.getNumber()+ "', '" +ordered+ "', '" +payAmount+ "', '" +payBy+ "', "
                      +cardNumber+ ", " +passCode+ ", '" +startTime+ "', '" +endTime+ "', '"
                      +duration+ "', '" +issueDateStr+ "')";
            stmt.executeUpdate(query);
            System.out.println("\n\tInsert successfully!");
    
            // mark this cus has order serve
            query = "UPDATE customers SET statusID = 3 WHERE `number` = " +cus.getNumber();
            stmt.executeUpdate(query);
          }
          catch (SQLException e) {
            e.printStackTrace();
          }
        }
        
      }
    
      // display customer who are waiting to order
      public void listCusWaitToOrder() {
        System.out.println("\n\tCustomers who are waiting to order...");
        System.out.println("""
          \n\t| Customer No. |    Date Entering    |
            \t+--------------+---------------------+""");
        for(var cus : this.CusQueue) {
          System.out.printf("\t     %05d       %s\n", cus.getNumber(), Util.dfm.format(cus.getdate()));
        }
      }
    
      // list all customers who are waiting for food
      public void listCusWaitForFood() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4c?user=root&password=")) {
          var stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery("SELECT * FROM customers NATURAL JOIN orderdetails WHERE statusID = 3;");
          // 1.NUMBER  4.foodDrinks 5.prices 2.EnteringDate 9.startServingTime 10.endServingTime 11.duraiton 12.issueDate
          System.out.println("""
            \n\t|  No.  |        Ordered        | Prices($) |    Date    |   Serving Time    | Duration |    Issue Date       |
            \t+-------+-----------------------+-----------+---------------------+-------------------+----------+---------------------+""");
          String ordered;
          while(rs.next()) {
            Date date = new Date(rs.getTimestamp(9).getTime());  
            Date issueDate = new Date(rs.getTimestamp(12).getTime());  
            ordered = Util.trimTo(rs.getString(4), 20);
            System.out.printf("\t  %05d   %-21s     %6.2f    %s   %s-%s   %s   %s\n", 
                rs.getInt(1),
                ordered,
                rs.getFloat(5),
                sqlFormat.format(date),
                rs.getString(9),
                rs.getString(10),
                rs.getString(11),
                sqlFormat.format(issueDate));
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
    
      // Represent the customer who are waiting
      public int countCusWaitToOrder() {
        return this.CusQueue.size();
      }
    
      // Count all customer from database (waiting for food + waiting to order)
      public int countAllCustomer() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4c?user=root&password=")) {
          var stmt = conn.createStatement();
          String sql = "select count(*) from customers";
          ResultSet rs = stmt.executeQuery(sql);
          rs.next();
          return rs.getInt(1);
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
         return -1;
      }
    
      // create databases if not exit
      public static void createDB() {
        try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          // System.out.println("\n\tConnecting to Database...");
        } catch(ClassNotFoundException e) {
          System.out.println("\tDriver found!");
        }
    
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4c?user=root&password=")) {
          var stmt = conn.createStatement();
    
          // Create database
          var sql = "CREATE DATABASE if NOT EXISTS i4c";
          stmt.executeUpdate(sql);
          
          // use database
          sql = "USE i4c";
          stmt.executeUpdate(sql);
    
          // Create table status 
          sql = "CREATE TABLE if NOT EXISTS `status` ("
              + " `statusID` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
              + " `description` VARCHAR(20) )";
          stmt.executeUpdate(sql);
    
          // Create table customers
          sql = "CREATE TABLE if NOT EXISTS `customers` ("
              + "`number` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
              + "`date` TIMESTAMP NOT NULL,"
              + "`statusID` INT ,"
              + "FOREIGN KEY (statusID) REFERENCES `status`(statusID) )";
          stmt.executeUpdate(sql);
    
          // Create table payment method
          sql = "CREATE TABLE if NOT EXISTS `paymentMethod` ("
              + "`payMetID` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
              + "`type` VARCHAR(20) )";
          stmt.executeUpdate(sql);
    
          // Create table orderDetails
          sql = "CREATE TABLE if NOT EXISTS `orderDetails` ("
              + "`number` INT,"
              + "`foodDrinks` VARCHAR(100), "
              + "`prices` DOUBLE(6,2), "
              + "`payMetID` INT , "
              + "`cardNumber` VARCHAR(100), "
              + "`passCode` VARCHAR(6), "
              + "`startServingTime` TIME, "
              + "`endServingTime` TIME, "
              + "`duration` TIME, "
              + "`issueDate` TIMESTAMP, "
              + "FOREIGN KEY (`number`) REFERENCES `customers`(`number`), "
              + "FOREIGN KEY (`payMetID`) REFERENCES `paymentmethod`(`payMetID`) )";
          stmt.executeUpdate(sql);
    
          // insert default value if not exist in the table status
          ResultSet rs = stmt.executeQuery("select count(*) from `status`");
          rs.next();
          int numberOfStatus = rs.getInt(1);
          if(numberOfStatus == 0) {
            // Insert default value to table status
            sql = "INSERT INTO `status` (statusID, description) VALUES "
                + "(NULL, 'waiting to order'), "
                + "(NULL, 'ordering'), "
                + "(NULL, 'waiting for food') ";
            stmt.executeUpdate(sql);
          }
    
          // insert default value if not exist in the table paymentmethod
          rs = stmt.executeQuery("select count(*) from `paymentmethod`");
          rs.next();
          int numberOfPaymentMethod = rs.getInt(1);
          if(numberOfPaymentMethod == 0) {
            // Insert default value to table payment method
            sql = "INSERT INTO `paymentmethod` (payMetID, `type`) VALUES "
                + "(null, 'cash'), "
                + "(null, 'credit card')";
            stmt.executeUpdate(sql);
          }
    
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      TP10_4_QueueClass q = new TP10_4_QueueClass();
      
      createDB();

      int opt;

      do{
        System.out.println("""
            \n\t----------------- Menu ------------------
            \t|\t1. Get customers count.\t\t|
            \t|\t2. Add new customer to queue.\t|
            \t|\t3. Remove a customer from queue\t|
            \t|\t4. Serve a customer.\t\t|
            \t|\t5. Exit!\t\t\t|
            \t-----------------------------------------\n
            """);
        System.out.print("\t   => Choose an opton: ");
        opt = sc.nextInt();
        System.out.println();

        switch(opt){
          case 1:
            System.out.println("\t\tThere are "+q.countAllCustomer()+" customers int the table customers.");
            break;
          case 2:
            q.addNewCus();
            break;
          case 3:
             TP10_3_CustomerClass re = q.removeCustomer();
            // TP10_3_CustomerClass.removeCustomer();
            break;
          case 4:
            q.serveCustomer();
          break;
          case 5:
          System.out.println("\tProgram exit!!");
          System.exit(1);
          break;
        }
      }while(opt != 6);
    sc.close();
  }
}
