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


public class Main {

    public static void main(String[] args) throws DoesNotExistException, TeacherException, ExistentElementException, CanNotRegister, IOException {
        TeacherRepository teachers = new TeacherRepository("teachers.json");
        CourseRepository courses = new CourseRepository("courses.json");
        StudentRepository students = new StudentRepository("students.json");

        Controller contr = new Controller(courses, teachers, students);
        ConsoleView consoleV = new ConsoleView(contr);

        consoleV.run();
    }
}
