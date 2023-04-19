import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentSkipListMap;

public class TP06_4_Courses {
    private ArrayList<Courses> courseList = new ArrayList<>();
    
    Courses c1 = new Courses("DBMS");
    Courses c2 = new Courses("Networks");
    Courses c3 = new Courses("Software");
    Courses c4 = new Courses("Telecommunications");
    
    public TP06_4_Courses(){
        courseList.add(c1);
        courseList.add(c2);
        courseList.add(c3);
        courseList.add(c4);
    }
    
    public void listAllCourses(){
        System.out.println("\n\t________Course Name_________\n");
        for(Courses course: courseList){
            System.out.println("\t"+course.getCoures_name());
        }
    }

    public void findCourseByName(String name){
        for(int i=0; i<courseList.size(); i++){
            Courses course = courseList.get(i);
            if(courseList.get(i).getCoures_name().equals(name)){
                System.out.print("\tYou search for course ---> "+name);
                System.out.println("\n\t****FOUND!!!");
            }
        }
    }

    public void addNewCourse(Courses course){
        courseList.add(course);
    }

    public Courses inputCourses(){
        Scanner sc = new Scanner(System.in);

        System.out.print("\tInput course name: ");
        String name = sc.nextLine();

        Courses newCourse = new Courses(name);
        return newCourse;   
    }

    public void Menu(){
        System.out.println("\n\t==================================");
        System.out.println("\t\t\tMenu");
        System.out.println("\t==================================");
        System.out.println("\t1. List all courses.");
        System.out.println("\t2. Find courses by name.");
        System.out.println("\t3. Add new course.");
        System.out.println("\t4. Quit");
        System.out.println("\t\t--------x--------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TP06_4_Courses course = new TP06_4_Courses();

        int option;

        do{
            course.Menu();
            System.out.print("\n-->  Chose an option: ");
            option = sc.nextInt();

            switch(option){
                case 1:
                //list all courses
                    course.listAllCourses();
                    break;
                case 2:
                //find courses by name
                    System.out.print("\tInput course name that you want to find: ");
                    course.findCourseByName(sc.next());
                    break;
                case 3:
                //add new course
                    Courses c = course.inputCourses();
                    course.addNewCourse(c);
                    break;
                case 4:
                //quit
                    System.out.println("Quit!!");
                    System.exit(1);
                    break;
            }
        }while(option != 4);
    }
}
