package Controller;

import Repository.CourseRepository;
import Repository.StudentRepository;
import Repository.TeacherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exception.DoesNotExistException;
import Exception.TeacherException;
import Exception.ExistentElementException;
import Exception.CanNotRegister;


import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private TeacherRepository tr;
    private CourseRepository cr;
    private StudentRepository sr;
    private Controller contrTest;

    /**
     * before each test the controller is added and some valid Objects are added to all 3 repositories
     * 3 teachers, 3 students and 4 courses
     */
    @BeforeEach
    void setup(){
        tr = new TeacherRepository("teacherTest.json");
        cr = new CourseRepository("courseTest.json");
        sr = new StudentRepository("studentTest.json");
        contrTest = new Controller(cr,tr,sr);

        try {
            contrTest.addTeacher("Dan", "Stan", 1);
        } catch (ExistentElementException e) {
            Assertions.fail();
        }

        try {
            contrTest.addTeacher("Liviu", "Bran", 2);
        } catch (ExistentElementException e) {
            Assertions.fail();
        }

        try {
            contrTest.addTeacher("Dan", "Stefan", 3);
        } catch (ExistentElementException e) {
            Assertions.fail();
        }

        try {
            contrTest.addStudent("Bill", "Burr", 1);
        } catch (ExistentElementException e) {
            Assertions.fail();
        }

        try {
            contrTest.addStudent("Bob", "Balint", 2);
        } catch (ExistentElementException e) {
            Assertions.fail();
        }

        try {
            contrTest.addStudent("Constantin", "Dinescu", 3);
        } catch (ExistentElementException e) {
            Assertions.fail();
        }

        try {
            contrTest.addCourse(1, "Computer Science", 1, 30, 6);
        } catch (ExistentElementException | DoesNotExistException e) {
            Assertions.fail();
        }

        try {
            contrTest.addCourse(2, "English", 2, 31, 6);
        } catch (ExistentElementException | DoesNotExistException e) {
            Assertions.fail();
        }

        try {
            contrTest.addCourse(3, "French", 3, 2, 15);
        } catch (ExistentElementException | DoesNotExistException e) {
            Assertions.fail();
        }

        try {
            contrTest.addCourse(4, "History", 3, 32, 6);
        } catch (ExistentElementException | DoesNotExistException e) {
            Assertions.fail();
        }

        try {
            contrTest.addCourse(5, "Spanish", 1, 4, 15);
        } catch (ExistentElementException | DoesNotExistException e) {
            Assertions.fail();
        }

    }

    @Test
    /**
     * the first 2 calls of register, register the course
     * the 3rd call, doesn't register because course 3 has already a maximum number of students
     * the 4th call, doesn't register the student id is invalid (there isn't a student with id 4 in the repo)
     */
    void register() {
        try {
            contrTest.register(3, 1);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }

        try {
            contrTest.register(3, 2);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }

        try {
            contrTest.register(3, 3);
            Assertions.fail();
        } catch (DoesNotExistException e) {
            Assertions.fail();
        }catch(CanNotRegister e){
            Assertions.assertTrue(true);
        }

        try {
            contrTest.register(1, 4);
            Assertions.fail();
        } catch (CanNotRegister e) {
            Assertions.fail();
        }catch(DoesNotExistException e){
            Assertions.assertTrue(true);
        }

    }

    /**
     * at the first assertEquals, there aren't any courses with max number of students
     * at the second, after 2 register calls, there is one course
     */
    @Test
    void retrieveCoursesWithFreePlaces() {
        try {
            contrTest.register(3, 1);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }
        assertEquals(contrTest.retrieveCoursesWithFreePlaces().size(),  contrTest.getAllCourses().size());

        try {
            contrTest.register(3, 2);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }

        assertEquals(contrTest.retrieveCoursesWithFreePlaces().size(),  contrTest.getAllCourses().size()-1);
    }

    @Test
    void retrieveStudentsEnrolledForACourse() {
        try {
            contrTest.register(3, 1);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }
        assertEquals(contrTest.retrieveStudentsEnrolledForACourse(3).size(), 1);

        try {
            contrTest.register(3, 2);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }
        assertEquals(contrTest.retrieveStudentsEnrolledForACourse(3).size(), 2);

        assertEquals(contrTest.retrieveStudentsEnrolledForACourse(1).size(), 0);


    }

    /**
     * the 2 assertEquals between the first call of deleteCourse, show us that the course was deleted
     * the second call of deleteCourse, doesn't delete, because the courseId is invalid, (curse with id3 was already deleted)
     * the 3rd call fails to delete because, the teacher with the given id doesn't teach the course to be deleted
     */
    @Test
    void deleteCourse() {

        assertEquals(contrTest.getAllCourses().size(), 5);
        try {
            contrTest.deleteCourse(3, 3);
        } catch (DoesNotExistException | TeacherException e) {
            Assertions.fail();;
        }
        assertEquals(contrTest.getAllCourses().size(), 4);

        try {
            contrTest.deleteCourse(6, 3);
            Assertions.fail();
        } catch (TeacherException e) {
            Assertions.fail();
        }catch(DoesNotExistException e){
            Assertions.assertTrue(true);
        }

        try {
            contrTest.deleteCourse(2, 1);
            Assertions.fail();
        } catch (DoesNotExistException e) {
            Assertions.fail();
        }catch(TeacherException e){
            Assertions.assertTrue(true);
        }
    }

    /**
     * add fails, because there is already a teacher with id=2, in the teachers repo
     */
    @Test
    void addTeacher() {
        try {
            contrTest.addTeacher("Mister", "White", 2);
            Assertions.fail();
        } catch (ExistentElementException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    void addStudent() {
        try {
            contrTest.addStudent("Constantin", "Dinescu", 2);
            Assertions.fail();
        } catch (ExistentElementException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    void addCourse() {
        try {
            contrTest.addCourse(5, "Italian", 2, 10, 5);
            Assertions.fail();
        } catch (DoesNotExistException e) {
            Assertions.fail();
        }catch (ExistentElementException e) {
            Assertions.assertTrue(true);
        }

        try {
            contrTest.addCourse(7, "DSA", 6, 10, 5);
            Assertions.fail();
        } catch ( ExistentElementException e) {
            Assertions.fail();
        }catch (DoesNotExistException e) {
            Assertions.assertTrue(true);
        }

    }

    /**
     * student with id=1 is registered to 3 courses
     * student with id=2 is registered to 2 courses
     * student with id=3 is registered to 1 course
     * to test sorting of the students (by testing the places where the courses are in the students repository)
     */
    @Test
    void sortStudentCredits() {
        try {
            contrTest.register(3, 1);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }
        try {
            contrTest.register(3, 2);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }

        try {
            contrTest.register(1, 1);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }

        try {
            contrTest.register(1, 2);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }

        try {
            contrTest.register(1, 3);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }

        try {
            contrTest.register(2, 1);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }

        contrTest.sortStudentCredits();
        assertEquals(contrTest.getAllStudents().get(2).getStudentId(), 1);
        assertEquals(contrTest.getAllStudents().get(1).getStudentId(), 2);
        assertEquals(contrTest.getAllStudents().get(0).getStudentId(), 3);


    }

    /**
     * 2 students are registered to course with id =2
     * 3 students are registered to course with id =1
     * 1 student is registered to course with id =3
     * to test sorting of the courses (by testing the places where the courses are in the courses repository
     */
    @Test
    void sortCourseNrStudents() {
        try {
            contrTest.register(3, 1);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }

        try {
            contrTest.register(3, 2);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }

        try {
            contrTest.register(1, 1);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }

        try {
            contrTest.register(1, 2);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }

        try {
            contrTest.register(1, 3);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }

        try {
            contrTest.register(2, 1);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }

        contrTest.sortCourseNrStudents();
        assertEquals(contrTest.getAllCourses().get(4).getCourseId(), 1);
        assertEquals(contrTest.getAllCourses().get(3).getCourseId(), 3);
        assertEquals(contrTest.getAllCourses().get(2).getCourseId(), 2);

    }

    @Test
    void filterStudents() {
        assertEquals(contrTest.filterStudents().size(), 0);
        try {
            contrTest.register(1, 1);
        } catch (DoesNotExistException | CanNotRegister e) {
            Assertions.fail();
        }

        assertEquals(contrTest.filterStudents().size(), 1);

    }

    @Test
    void filterCourse() {
        assertEquals(contrTest.filterCourse().size(), contrTest.getAllCourses().size() -3);

        try {
            contrTest.addCourse(6, "DSA", 3, 35, 15);
        } catch (ExistentElementException | DoesNotExistException e) {
            Assertions.fail();
        }
        assertEquals(contrTest.filterCourse().size(), contrTest.getAllCourses().size() -3);

    }
}