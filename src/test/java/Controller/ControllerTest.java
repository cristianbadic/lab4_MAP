package Controller;

import Repository.CourseRepository;
import Repository.StudentRepository;
import Repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exception.DoesNotExistException;
import Exception.TeacherException;
import Exception.ExistentElementException;
import Exception.CanNotRegister;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    @BeforeEach
    void setup() throws IOException {
        TeacherRepository tr = new TeacherRepository("teacherTest.json");
        CourseRepository cr = new CourseRepository("courseTest.json");
        StudentRepository sr = new StudentRepository("studentTest.json");
        Controller contrTest = new Controller(cr,tr,sr);

        
    }

    @Test
    void register() {
    }

    @Test
    void retrieveCoursesWithFreePlaces() {
    }

    @Test
    void retrieveStudentsEnrolledForACourse() {
    }

    @Test
    void deleteCourse() {
    }

    @Test
    void addTeacher() {
    }

    @Test
    void addStudent() {
    }

    @Test
    void addCourse() {
    }

    @Test
    void sortStudentCredits() {
    }

    @Test
    void sortCourseNrStudents() {
    }

    @Test
    void filterStudents() {
    }

    @Test
    void filterCourse() {
    }
}