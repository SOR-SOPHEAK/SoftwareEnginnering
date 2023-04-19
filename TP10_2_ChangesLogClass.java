

import java.sql.*;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TP10_2_ChangesLogClass extends TP10_1_DateClass {

    public TP10_2_ChangesLogClass(Date chang_date) {
        super(chang_date);
    }

    public TP10_2_ChangesLogClass(Date date_start, Date date_end) {
        super(date_start, date_end);
    }

    public TP10_2_ChangesLogClass() {}

    @Override
    public String toString(){
        String sql = "SELECT * FROM dateutil ORDER BY ID DESC LIMIT 5;";
        return this.strDateList(sql);
    }
    //return as string
    private String strDateList(String sql) {
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4c?user=root&password=")){
            var stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.print("""
                    \n\t+----+---------------+---------------+-------+----------------+----------------------+
                    \t| ID |   Start Date  |   End Date    |  Days | Operation type |     Changed Date     |
                    \t+----+---------------+---------------+-------+----------------+----------------------+
                    """);
            String result = this.header();
            String operation_type = "Substraction";
            // return result;

            while(rs.next()){
                if(rs.getString(5).equals("Increment")){
                    operation_type = rs.getString(5)+"  ";
                }

                Date changed_date = new Date(rs.getTimestamp(6).getTime());
                System.out.print("\t| "+rs.getInt("id")+"\t"+rs.getString("start_date")+"\t"+rs.getString("end_date")+"\t"+rs.getInt("n_days")+"\t"+rs.getString("operation_type")+"\t\t"+rs.getDate("changed_date")+"   |\n");
            }
            return result;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private String header(){
        String result = "\t+----+---------------+---------------+-------+----------------+----------------------+\n";
        return result;
    }

    public void LastFiveDate(String sql){
        
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4c?user=root&password=")){
            Statement stmt = conn.createStatement();
            stmt.execute("USE i4c");
            //--------------------------------
            ResultSet rs = stmt.executeQuery(sql);
            System.out.print("""
                    \n\t+----+---------------+---------------+-------+----------------+----------------------+
                    \t| ID |   Start Date  |   End Date    |  Days | Operation type |     Changed Date     |
                    \t+----+---------------+---------------+-------+----------------+----------------------+
                    """);
            
            while(rs.next()){
                System.out.println("\t| "+rs.getInt("id")+"\t"+rs.getString("start_date")+"\t\t"+rs.getString("end_date")+"\t"+rs.getInt("n_days")+"\t"+rs.getString("operation_type")+"\t\t"+rs.getDate("changed_date")+"   |\n");
            }
            System.out.println("\t+----+---------------+---------------+-------+----------------+----------------------+\n");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void dateList(String sql){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4c?user=root")) {
            var stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.print("""
                \n\t+----+---------------+---------------+-------+----------------+----------------------+
                \t| ID |   Start Date  |   End Date    |  Days | Operation type |     Changed Date     |
                \t+----+---------------+---------------+-------+----------------+----------------------+
                """);
            while(rs.next()){
              Date changed_date = new Date(rs.getTimestamp(6).getTime());  
              System.out.println("\t| "+rs.getInt("id")+"\t"+rs.getString("start_date")+"\t\t"+rs.getString("end_date")+"\t"+rs.getInt("n_days")+"\t"+rs.getString("operation_type")+"\t\t"+rs.getDate("changed_date")+"   |\n");
            }
            System.out.println("\t+----+---------------+---------------+-------+----------------+----------------------+\n");
          }
          catch (SQLException e) {
            e.printStackTrace();
          }
    }

    public void ListChangeByDateRange(Date start_date, Date end_date){
        // String start = fmt.parse(start_date);
        // String end = Util.sqlDfm.format(end_date);
        var sql = "SELECT * FROM dateutil  WHERE changed_date >= '" +start_date+" 00:00:00' AND  changed_date <= '" +end_date+ " 23:59:59'";
        this.dateList(sql);
    }

    public String curWeek(){
        String sql = "SELECT * FROM dateutil WHERE week(changed_date)=week(now());";
        return this.strDateList(sql);
    }

    public void ListChangeByweek(){
        Calendar cal = Calendar.getInstance();
        while(cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY){
            cal.add(Calendar.DATE, -1);
        }
        System.out.println(cal.getTime());
        
         var sql = "SELECT * FROM dateutil WHERE WEEK(changed_date) = WEEK(NOW());";
        // var sql = "SELECT * FROM dateutil WHERE DateDiff(wk,getdate(),datefield) =  0;";

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4c?user=root&password=")){
            var stmt = conn.createStatement();
            stmt.execute("USE i4c");
            ResultSet rs = stmt.executeQuery(sql);
            System.out.print("""
                \n\t+----+---------------+---------------+-------+----------------+----------------------+
                \t| ID |   Start Date  |   End Date    |  Days | Operation type |     Changed Date     |
                \t+----+---------------+---------------+-------+----------------+----------------------+
                """);
            while(rs.next()){
                System.out.println("\t| "+rs.getInt("id")+"\t"+rs.getString("start_date")+"\t\t"+rs.getString("end_date")+"\t"+rs.getInt("n_days")+"\t"+rs.getString("operation_type")+"\t\t"+rs.getDate("changed_date")+"   |\n");
            }
            System.out.println("\t+----+---------------+---------------+-------+----------------+----------------------+\n");
        }catch(SQLException e){
            e.printStackTrace();
        }
        curWeek();
    }
    public void ListChangeBydate(String changed_date){
        String temp = dbfmt.format(changed_date);
        var sql = "SELECT * FROM dateutils WHERE changed_date >= '" +temp+" 00:00:00' AND  changed_date <= '" +temp+ " 23:59:59'";
        this.dateList(sql);
    }
    public void ListAllChanges(){
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/i4c?user=root&password=")){
            Statement stmt = conn.createStatement();
            stmt.execute("USE i4c");
            //--------------------------------
            ResultSet rs = stmt.executeQuery("SELECT * FROM dateutil;");
            System.out.print("""
                \n\t+----+---------------+---------------+-------+----------------+----------------------+
                \t| ID |   Start Date  |   End Date    |  Days | Operation type |     Changed Date     |
                \t+----+---------------+---------------+-------+----------------+----------------------+
                """);
            
            while(rs.next()){
                System.out.println("\t| "+rs.getInt("id")+"\t"+rs.getString("start_date")+"\t"+rs.getString("end_date")+"\t"+rs.getInt("n_days")+"\t"+rs.getString("operation_type")+"\t"+rs.getString("changed_date")+"  |");
            }
            System.out.println("\t+----+---------------+---------------+-------+----------------+----------------------+\n");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws ParseException {
        TP10_2_ChangesLogClass l = new TP10_2_ChangesLogClass();
        Scanner sc = new Scanner(System.in);
        
        String start_date, end_date, changed_date;
        int option;
        do{
            
            System.out.println("""
                \n\t-------------------- Menu ---------------------
                \t    1. list last 5 changes in date history.
                \t    2. Listing changes by date.
                \t    3. Listing changes by week.
                \t    4. Listing changes by date range.
                \t    5. List all changes.
                \t    6. Exit the program.
                \t-----------------------------------------------
                    """);
            System.out.print("\t   => Choose an option: ");
            option = sc.nextInt();
            System.out.println();

            switch(option){
                case 1:
                    System.out.println("\t\tLast 5 changed date");
                    System.out.println(l);
                    break;
                case 2:
                    System.out.print("\t\tInput changed date(dd-MM-yyyy): ");
                    changed_date = sc.next();
                    System.out.println();

                    TP10_2_ChangesLogClass d = new TP10_2_ChangesLogClass(fmt.parse(changed_date));
                    d.ListChangeBydate(changed_date);
                    // System.out.println();
                    // l.ListChangeBydate(changed_date);
                    break;
                case 3:
                    System.out.println("\t\tAll changed in this week.....");
                    l.ListChangeByweek();
                    
                    break;
                case 4:
                    System.out.print("\t\tInput start date(dd-MM-yyyy): ");
                    start_date = sc.next();
                    System.out.print("\t\tInput end date(dd-MM-yyyy): ");
                    end_date = sc.next();
                    System.out.println();
                    
                    TP10_2_ChangesLogClass ls = new TP10_2_ChangesLogClass(fmt.parse(start_date), fmt.parse(end_date));
                    ls.ListChangeByDateRange(start_date, end_date);
                    break;
                case 5: 
                    l.ListAllChanges();
                    break;
                case 6:
                    System.out.println("\t\tExit Programm!!");
                    System.exit(1);
                break;
            }
        }while(option != 6);
    }


private void ListChangeByDateRange(String start_date, String end_date) {}
}