import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TP10_1_DateClass {

  private int id;
  private Date date_start, date_end;
  private int n_days;
  private String operation_type;
  private Date chang_date;

  public TP10_1_DateClass(Date date_start, Date date_end) {
    this.date_start = date_start;
    this.date_end = date_end;
  }

  public static final SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
  public static final SimpleDateFormat dbfmt = new SimpleDateFormat("yyyy-MM-dd");

  public TP10_1_DateClass(Date chang_date) {
    this.chang_date = chang_date;
  }

  public TP10_1_DateClass() {}

  public int subtract(){

    // TP10_1_DateClass d = new TP10_1_DateClass(date_start, date_end);
    
    long millis = date_end.getTime() - date_start.getTime();
    int days = (int)TimeUnit.DAYS.convert(millis, TimeUnit.MILLISECONDS);
    System.out.print("\t  => Number of subtract between two date: ");
    
    // Make connection
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      // System.out.println("Driver is loaded successfully.");
    }catch(ClassNotFoundException e){
      System.out.println("Driver failed to load.");
      System.exit(1);
    }
    try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4c?user=root&password=")){
      // System.out.println("connected to database");

      var sql = "CREATE DATABASE IF NOT EXISTS i4c";
      Statement stmt = conn.createStatement();

      // Create table in database
      sql = "CREATE TABLE IF NOT EXISTS dateutil("
          + "ID int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
          + "start_date DATE,"
          + "end_date DATE,"
          + "n_days INT,"
          + "operation_type VARCHAR(20) NOT NULL,"
          + "changed_date TIMESTAMP NOT NULL)";
      stmt.executeUpdate(sql);

      //Use database
      stmt.execute("USE i4c");

      //insert into database
      sql = "INSERT INTO dateutil VALUES(NULL"
      +",'"+dbfmt.format(date_start)+"'"
      +",'"+dbfmt.format(date_end)+"'"
      +","+days
      +",'Substraction'"
      +",NULL)";
      stmt.executeUpdate(sql);
    }catch(SQLException e){
      e.printStackTrace();
    }
    return days;
  }

  public void increase(int days){
    Calendar cal = Calendar.getInstance();
    cal.setTime(date_start);
    cal.add(Calendar.DATE, days);

    date_end = cal.getTime();
    System.out.println("\t  => The date after increase: "+date_end);

    // Make connection
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      // System.out.println("Driver is loaded successfully.");
    }catch(ClassNotFoundException e){
      System.out.println("Driver failed to load.");
      System.exit(1);
    }
    try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4c?user=root&password=")){
      // System.out.println("connected to database");

      var sql = "CREATE DATABASE IF NOT EXISTS i4c";
      Statement stmt = conn.createStatement();

      //Use database
      stmt.execute("USE i4c");

      //insert into database
      sql = "INSERT INTO dateutil VALUES(NULL"
      +",'"+dbfmt.format(date_start)+"'"
      +",'"+dbfmt.format(date_end)+"'"
      +","+days
      +",'Increment'"
      +",NULL)";
      stmt.executeUpdate(sql);
    }catch(SQLException e){
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws ParseException{
    Scanner sc = new Scanner(System.in);
    String start_date, end_date;
    
    int option;
    do{
      System.out.println("""
          \n\t----------------------------------
          \t\t 1. Substract date.
          \t\t 2. Increase date.
          \t\t 3. Quit!!
          \t----------------------------------
          """);
      System.out.print("\t=> Choose an option: ");
      option = sc.nextInt();
      System.out.println();

      switch(option){
        case 1:
          System.out.print("\t\tInput start date(dd-MM-yyyy): ");
          start_date = sc.next();
          System.out.print("\t\tInput end date(dd-MM-yyyy): ");
          end_date = sc.next();
          TP10_1_DateClass du = new TP10_1_DateClass(fmt.parse(start_date), fmt.parse(end_date));
          System.out.println(du.subtract());
          break;
        case 2:
          System.out.print("\t\tInput start date(dd-MM-yyyy): ");
          start_date = sc.next();
          System.out.print("\t\tInput number of day to increase: ");
          int days = sc.nextInt();
          TP10_1_DateClass d = new TP10_1_DateClass(fmt.parse(start_date), null);
          d.increase(days);
          break;
        case 3:
          System.out.println("\t\tQuit!!");
          System.exit(1);
          break;
      }

    }while(option != 3);
  }

}