//no longer needed!!!!
/*
import static org.junit.jupiter.api.Assertions.*;

import Model.Course;
import Model.Student;
import Model.Teacher;
import Repository.CourseRepository;
import Repository.StudentRepository;
import Repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistrationSystemTest {
    private TeacherRepository teachers;
    private StudentRepository students;
    private CourseRepository courses;
    private RegistrationSystem reg;
    private Teacher teacher1;
    private Teacher teacher2;
    private Teacher teacher3;
    private Teacher teacher4;
    private Student student1;
    private Student student2;
    private Student student3;
    private Student student4;
    private Course course1;
    private Course course2;
    private Course course3;
    private Course course4;
    private Course course5;



    */
/**
     * object are created before each method
     *//*

    @BeforeEach
    public void initEach(){
        teacher1 = new Teacher("Daniel", "Moldovan");
        teacher2 = new Teacher("George", "Bucur");
        teacher3 = new Teacher("Marian", "Tudor");
        teacher4 = new Teacher("Liviu", "Maftei");

        course1 = new Course("Computer Science", teacher1, 30, 6);
        course2 = new Course("English", teacher2, 31, 6);
        course3 = new Course("French",teacher2, 2, 9);
        course4 = new Course("History", teacher3, 100, 6);
        course5 = new Course("Spanish", teacher4, 3, 10);

        student1 = new Student("Bill", "Burr", 121243);
        student2 = new Student("Virgil", "Balint", 45);
        student3 = new Student("Ioan", "Stefan", 43554);
        student4 = new Student("Constantin", "Dinescu", 1);

        teacher2.addCourses(course2);
        teacher2.addCourses(course3);

        teachers = new TeacherRepository();
        teachers.create(teacher1);
        teachers.create(teacher2);
        teachers.create(teacher3);
        teachers.create(teacher4);

        courses = new CourseRepository();
        courses.create(course1);
        courses.create(course2);
        courses.create(course3);
        courses.create(course4);
        courses.create(course5);

        students = new StudentRepository();
        students.create(student1);
        students.create(student2);
        students.create(student3);
        students.create(student4);


        reg = new RegistrationSystem(courses, teachers, students);

    }

    */
/**
     * first assertFalse tests the case in witch the student can't register to a new course, because his total credits will be over 30
     * second assertFalse tests the case, where no more students can be added to a specific course
     *//*

    @Test
    public void register() {

        reg.register(course1,student1);
        assertEquals(student1.getTotalCredits(), course1.getCredits());
        reg.register(course3, student1);
        assertEquals(student1.getTotalCredits(), course1.getCredits() + course3.getCredits());

        reg.register(course2, student1);
        assertFalse(reg.register(course5, student1));

        reg.register(course3, student2);
        assertEquals(course3.getStudentsEnrolled().size(), course3.getMaxEnrollment());
        assertFalse(reg.register(course3, student3));
    }

    @Test
    void retrieveCoursesWithFreePlaces() {
        assertEquals(reg.retrieveCoursesWithFreePlaces().size(), courses.getAll().size());

        reg.register(course3, student1);
        reg.register(course3, student2);
        reg.register(course5, student1);
        reg.register(course5, student2);
        reg.register(course5, student3);
        assertEquals(reg.retrieveCoursesWithFreePlaces().size(),  courses.getAll().size()-2);


    }

    @Test
    void retrieveStudentsEnrolledForACourse() {
        assertEquals(reg.retrieveStudentsEnrolledForACourse(course5).size(), 0);
        reg.register(course5, student1);
        reg.register(course5, student2);
        reg.register(course5, student3);
        assertEquals(reg.retrieveStudentsEnrolledForACourse(course5).size(), 3);
    }

    @Test
    void getAllCourses() {
        assertEquals(reg.getAllCourses().size(),courses.getAll().size());
    }

    */
/**
     * the last assertEquals shows that a student had to be removed from a course because the new credits where to many
     *//*

    @Test
    void changeCredits() {
        reg.register(course4, student4);
        reg.register(course3, student4);
        reg.register(course5, student4);

        reg.changeCredits(course4, 8);
        assertEquals(student4.getEnrolledCourses().size(), 3);

        reg.changeCredits(course4, 12);
        assertEquals(student4.getEnrolledCourses().size(), 2);

    }

    @Test
    void deleteCourse() {
        assertFalse(reg.deleteCourse(teacher2, course1));
        assertEquals(reg.getAllCourses().size(), 5);

        reg.deleteCourse(teacher2, course2);
        assertEquals(reg.getAllCourses().size(), 4);

    }
}*/
