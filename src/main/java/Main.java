import ConsoleView.ConsoleView;
import Controller.Controller;
import Model.Course;
import Model.Student;
import Model.Teacher;
import Repository.CourseRepository;
import Repository.StudentRepository;
import Repository.TeacherRepository;
import Exception.DoesNotExistException;
import Exception.TeacherException;
import Exception.ExistentElementException;
import Exception.CanNotRegister;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws DoesNotExistException, TeacherException, ExistentElementException, CanNotRegister, IOException {
      /*  TeacherRepository teachers;
        StudentRepository students;
        CourseRepository courses;
        Teacher teacher1;
        Teacher teacher2;
        Teacher teacher3;
        Teacher teacher4;
        Student student1;
        Student student2;
        Student student3;
        Student student4;
        Course course1;
        Course course2;
        Course course3;
        Course course4;
        Course course5;

        teacher1 = new Teacher("Daniel", "Moldovan", 1, new ArrayList());
        teacher2 = new Teacher("George", "Bucur", 2,new ArrayList());
        teacher3 = new Teacher("Marian", "Tudor", 3,new ArrayList());
        teacher4 = new Teacher("Liviu", "Maftei", 4,new ArrayList());

        course1 = new Course(1,"Computer Science", 1, 30, 6, new ArrayList());
        course2 = new Course(2,"English", 2, 31, 6, new ArrayList());
        course3 = new Course(3,"French",2, 2, 9, new ArrayList());
        course4 = new Course(4,"History", 3, 100, 6, new ArrayList());
        course5 = new Course(5,"Spanish", 4, 3, 10, new ArrayList());

        student1 = new Student("Bill", "Burr", 1, 0, new ArrayList());
        student2 = new Student("Virgil", "Balint", 2, 0, new ArrayList());
        student3 = new Student("Ioan", "Stefan", 3, 0, new ArrayList());
        student4 = new Student("Constantin", "Dinescu", 4, 0, new ArrayList());

        teacher2.addCourses(course2);
        teacher2.addCourses(course3);
        course2.addStudents(student1);
        student1.addCourses(course2);

        teachers = new TeacherRepository("teachers.json");
        teachers.create(teacher1);
        teachers.create(teacher2);
        teachers.create(teacher3);
        teachers.create(teacher4);

        courses = new CourseRepository("courses.json");
        courses.create(course1);
        courses.create(course2);
        courses.create(course3);
        courses.create(course4);
        courses.create(course5);

        students = new StudentRepository("students.json");
        students.create(student1);
        students.create(student2);
        students.create(student3);
        students.create(student4);

        try {
            students.writeToJason();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            courses.writeToJason();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            teachers.writeToJason();
        } catch (IOException e) {
            e.printStackTrace();
        }

        TeacherRepository teachers2 = new TeacherRepository("teachers.json");
        CourseRepository courses2 = new CourseRepository("courses.json");
        StudentRepository students2 = new StudentRepository("students.json");
        try {
            students2.readJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            courses2.readJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            teachers2.readJson();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int idx = 0; idx < teachers2.getAll().size(); idx++){
            System.out.println(teachers2.getAll().get(idx).toString());
        }
        for (int idx = 0; idx < courses2.getAll().size(); idx++){
            System.out.println(courses2.getAll().get(idx).toString());
        }
        for (int idx = 0; idx < students2.getAll().size(); idx++){
            System.out.println(students2.getAll().get(idx).toString());
       }
    }*/
        TeacherRepository teachers = new TeacherRepository("teachers.json");
        CourseRepository courses = new CourseRepository("courses.json");
        StudentRepository students = new StudentRepository("students.json");

        Controller contr = new Controller(courses, teachers, students);
        ConsoleView consoleV = new ConsoleView(contr);

        consoleV.run();
    }
}
