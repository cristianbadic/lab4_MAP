package ConsoleView;

import Controller.Controller;

import java.io.IOException;
import java.util.Scanner;
import Exception.DoesNotExistException;
import Exception.TeacherException;
import Exception.ExistentElementException;
import Model.Course;
import Model.Student;
import Exception.CanNotRegister;

public class ConsoleView {
    private Controller controller;

    public ConsoleView(Controller controller) {
        this.controller = controller;
    }

    public void run() throws DoesNotExistException, TeacherException, ExistentElementException, IOException, CanNotRegister {
        Scanner scannerCV = new Scanner(System.in);
        boolean running = true;
        this.controller.readAllFiles();
        int option;
        while (running){
            this.CVMenu();
            System.out.println("Choose Option: ");
            option = scannerCV.nextInt();
            switch (option){
                case 0:
                    running = false;
                    break;
                case 1:
                    this.registerCV();
                    break;
                case 2:
                    this.retrieveFreeCourses();
                    break;
                case 3:
                    this.retrieveStudentsEnrolled();
                    break;
                case 4:
                    this.allCourses();
                    break;
                case 5:
                    this.deleteCo();
                    break;
                case 6:
                    this.addTe();
                    break;
                case 7:
                    this.addSt();
                    break;
                case 8:
                    this.addCo();
                    break;
                case 9:
                    this.sortStudents();
                    break;
                case 10:
                    this.sortCourses();
                    break;
                case 11:
                    this.filterStudents();
                    break;
                case 12:filterCourses();
                    break;
                default:
                    running = false;
            }
        }
        this.controller.writeToAllFiles();
        System.out.println("The Application was closed !\s");

    }
    public void CVMenu(){
        System.out.println("""
                0. Quit\s
                1. Register student\s
                2. Show courses with free places\s
                3. Retrieve students enrolled for course\s
                4. Show all courses\s
                5. Delete a course by a teacher\s
                6. Add a Teacher\s
                7. Add a student\s
                8. Add a course\s
                9. Sort students by the number of credits\s
                10. Sort courses by the number of enrolled students\s
                11. Filter students that are enrolled in at least a course  \s
                12. Filter courses, where more than 30 students are able to enroll\s
                """);
    }

    public void registerCV() throws DoesNotExistException, CanNotRegister {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Course id: ");
        long courseId = scanner.nextLong();

        System.out.println("Student id: ");
        long studentId = scanner.nextLong();

        this.controller.register(courseId, studentId);

    }

    public void retrieveFreeCourses(){
        for (Course course : this.controller.retrieveCoursesWithFreePlaces()){
            System.out.println(course);
        }
    }

    public void retrieveStudentsEnrolled(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Student id: ");
        long studentId = scanner.nextLong();

        for (Student stud : this.controller.retrieveStudentsEnrolledForACourse(studentId)){
            System.out.println(stud);
        }
    }

    public void allCourses(){
        for (Course course : this.controller.getAllCourses()){
            System.out.println(course);
        }
    }

    public void deleteCo() throws DoesNotExistException, TeacherException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Course id: ");
        long courseId = scanner.nextLong();

        System.out.println("Teacher id: ");
        long teacherId = scanner.nextLong();

        this.controller.deleteCourse(teacherId, courseId);


    }

    public void addTe() throws ExistentElementException {
        Scanner scanner = new Scanner(System.in);
        //scanner.nextLine();
        System.out.println("first Name: ");
        String firstN = scanner.nextLine();

        System.out.println("last Name: ");
        String lastN = scanner.nextLine();

        System.out.println("Teacher id: ");
        long teacherId = scanner.nextLong();

        this.controller.addTeacher(firstN, lastN, teacherId);

    }

    public void addSt() throws ExistentElementException {
        Scanner scanner = new Scanner(System.in);
        //scanner.nextLine();
        System.out.println("first Name: ");
        String firstN = scanner.nextLine();

        System.out.println("last Name: ");
        String lastN = scanner.nextLine();

        System.out.println("Student id: ");
        long studentId = scanner.nextLong();

        this.controller.addStudent(firstN, lastN, studentId);

    }

    public void addCo() throws DoesNotExistException, ExistentElementException {
        Scanner scanner = new Scanner(System.in);
        //scanner.nextLine();

        System.out.println(" Course Name: ");
        String courseN = scanner.nextLine();

        System.out.println("Course ID: ");
        long courseId = scanner.nextLong();

        System.out.println("Teacher ID: ");
        long teacherId = scanner.nextLong();

        System.out.println("max Enroll: ");
        int maxE = scanner.nextInt();

        System.out.println("Number of credits: ");
        int nrCred = scanner.nextInt();

        this.controller.addCourse(courseId, courseN, teacherId, maxE, nrCred);

    }

    public void sortStudents(){
        this.controller.sortStudentCredits();
        for (Student student : this.controller.getAllStudents()){
            System.out.println(student);
        }
    }

    public void sortCourses(){
        this.controller.sortCourseNrStudents();
        for (Course course : this.controller.getAllCourses()){
            System.out.println(course);
        }
    }

   public void filterCourses(){
       for (Course course : this.controller.filterCourse()){
           System.out.println(course);
       }
    }

    public void filterStudents(){
        for (Student student : this.controller.filterStudents()){
            System.out.println(student);
        }
    }
}
