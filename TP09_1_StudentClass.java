import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TP09_1_StudentClass {
    private String name;
    private Date dob;
    private String telephone;
    private String city;
    private String country;
    private String group;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        Calendar inputDate = Calendar.getInstance();
        inputDate.setTime(dob);

        this.dob = dob;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }

    public static TP09_1_StudentClass dataInput(){
        TP09_1_StudentClass student = new TP09_1_StudentClass();
        SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        System.out.printf("\nInput name: ");
        student.name = sc.nextLine();
        System.out.print("Input telephone number: ");
        student.telephone = sc.nextLine();
        System.out.print("Input city: ");
        student.city = sc.nextLine();
        System.out.print("Input country: ");
        student.country = sc.nextLine();
        System.out.print("Input group{A,B....}: ");
        student.group = sc.nextLine();
        while(true){
            try{
                System.out.print("Input date of birth(dd/MM/yyyy): ");
                student.setDob(format.parse(sc.nextLine()));
                break;
            }catch(ParseException e){
                System.out.print("Invalid date format!");
            }
        }
        return student;
    }

    public void dataOutput(){
        System.out.println("\n\nName: "+name+"\nBorn on: "+dob+"\nTelephone Number: "+telephone+"\nCity: "+city+"\nCountery: "+country+"\nGroup: "+group);
    }

    public static void main(String[] args) {
        TP09_1_StudentClass student = TP09_1_StudentClass.dataInput();
        student.dataOutput();
    }
}