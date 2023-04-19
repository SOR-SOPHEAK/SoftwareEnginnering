import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;


public class TP06_2_Student {
    private ArrayList<Student> studentList = new ArrayList<>();

    public void addNewStudent(Student student){
        studentList.add(student);
    }

    public void listStudents(){
        System.out.println("\n\tID\tName\tMajor\tAge\tGender\tTelephone");
        for(Student student: studentList){
            System.out.println("\t"+student.getID()+"\t"+student.getName()+"\t"+student.getMajor()+"\t"+student.getAge()+"\t"+student.getGender()+"\t"+student.getTelephone());
        }
    }

    public void removeStudentByName(String name){
        // for(Student student: studentList){
        //     if(student.getName().equals(name)){
        //         studentList.remove(student);
        //         System.out.println("\t^^^^^Remove successful!!");
        //     }
        // }

        for(int i=0; i<studentList.size(); i++){
            Student student = studentList.get(i);
            if(studentList.get(i).getName().equals(name)){
                studentList.remove(student);
                System.out.println("\t***Remove successful!!");
            }
        }
        
    }

    public void updateStudentInfoByID(String id){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\tEnter new information to update: ");
       // ListIterator<Student> stlist = studentList.listIterator();

        for(Student student: studentList){
            if(student.getID().equals(id)){
                System.out.print("\tInput NEW ID: ");
                id = sc.next();
                System.out.print("\tInput NEW name: ");
                String name = sc.next();
                System.out.print("\tInput NEW major: ");
                String major = sc.next();
                System.out.print("\tInput NEW age: ");
                int age = sc.nextInt();
                System.out.print("\tInput NEW gender: ");
                String gender = sc.next();
                System.out.print("\tInput NEW telephone: ");
                String telephone = sc.next();

                student.setID(id);
                student.setName(name);
                student.setMajor(major);
                student.setAge(age);
                student.setGender(gender);
                student.setTelephone(telephone);

                //stlist.set(new Student(name, id, major, gender, telephone, age));
                System.out.println("\tUpdate successfully!");
            }
        }
    }

    public Student inputStudent(){
        Scanner sc = new Scanner(System.in);

        System.out.print("\tInput student ID: ");
        String id = sc.nextLine();
        System.out.print("\tInput student name: ");
        String name = sc.nextLine();
        System.out.print("\tInput student major: ");
        String major = sc.nextLine();
        System.out.print("\tInput student age: ");
        int age = sc.nextInt();
        System.out.print("\tInput student gender: ");
        String gender = sc.nextLine();
        gender = sc.nextLine();
        System.out.print("\tInput student telephone: ");
        String telephone = sc.nextLine();

        Student newStudent = new Student(name, id, major, gender, telephone, age);
        return newStudent;
    }

    public void Menu(){
        System.out.println("\n\t==================================");
        System.out.println("\t\t\tMenu");
        System.out.println("\t==================================");
        System.out.println("\t1. Add student.");
        System.out.println("\t2. List students.");
        System.out.println("\t3. Remove student by name.");
        System.out.println("\t4. Update stuents infomation by id.");
        System.out.println("\t5. Quit");
        System.out.println("\t\t--------x--------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TP06_2_Student student = new TP06_2_Student();

        int option;

        do{
            student.Menu();
            System.out.print("\n-->  Chose an option: ");
            option = sc.nextInt();

            switch(option){
                case 1:
                //add new student
                    Student st = student.inputStudent();
                    student.addNewStudent(st);
                break;
                case 2:
                //list all students
                    student.listStudents();
                    break;
                case 3:
                //remove student by name
                    System.out.print("\tInput student name to remove: ");
                    //String newName = sc.next();
                    student.removeStudentByName(sc.next());
                    break;
                case 4:
                //update student by id
                    System.out.print("\tInput student ID to update: ");
                    student.updateStudentInfoByID(sc.next());
                    break;
                case 5:
                    System.out.println("....Program exit!!");
                    System.exit(1);
                    break;
            }
        }while(option != 5);
    }
}
