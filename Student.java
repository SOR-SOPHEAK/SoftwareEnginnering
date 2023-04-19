public class Student {
    private String Name;
    private String ID;
    private String Major;
    private String Gender;
    private String Telephone;
    private int Age;
    
    public Student(String name, String iD, String major, String gender, String telephone, int age) {
        Name = name;
        ID = iD;
        Major = major;
        Gender = gender;
        Telephone = telephone;
        Age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    @Override
    public String toString(){
        return "\t"+ID+"\t"+Name+"\t"+Major+"\t"+Age+"\t"+Gender+"\t"+Telephone;
    }
}