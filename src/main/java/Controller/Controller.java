package Controller;

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
import java.util.List;

public class Controller {
    private CourseRepository coursesRep;
    private TeacherRepository teachersRep;
    private StudentRepository studentsRep;



    public Controller(CourseRepository coursesR, TeacherRepository teachersR, StudentRepository studentsR) {
        this.coursesRep = coursesR;
        this.teachersRep = teachersR;
        this.studentsRep = studentsR;
    }


    //1
    public void register(long courseId, long studentId) throws DoesNotExistException, CanNotRegister {
        Student student = null;
        for(Student stud: studentsRep.getAll())
            if(stud.getStudentId()==studentId){
                student = stud;
                break;
            }
        if(student==null)
            throw new DoesNotExistException("This student isn't in the repository!");

        Course course = null;
        for(Course co: coursesRep.getAll())
            if(co.getCourseId() ==courseId){
                course = co;
                break;
            }
        if(course==null)
            throw new DoesNotExistException("This course isn't in the repository!");

        if (course.getMaxEnrollment() > course.getStudentsEnrolled().size()
                && student.getTotalCredits() + course.getCredits() <31) {
            course.addStudents(student);
            student.addCourses(course);
            this.coursesRep.update(course);
            this.studentsRep.update(student);
        }
        else
            throw new CanNotRegister("The Student has to many credits or the curs is full!");
    }



/**
 * search through the courses repository to find te courses with free places, witch are added to the free list
 * maxEnrolled variable, used to get the max nr. of enrollments for each curse in the repo
 * nrStudents, used to find the number of student enrolled for each specific course in the repo
 * @return list of courses
 */

    //2
    public List<Course> retrieveCoursesWithFreePlaces(){
        List<Course> free= new ArrayList<>();
        for (int index =0; index < this.coursesRep.getAll().size(); index ++){
            int maxEnrolled = this.coursesRep.getAll().get(index).getMaxEnrollment();
            int nrStudents = this.coursesRep.getAll().get(index).getStudentsEnrolled().size();
            if (maxEnrolled > nrStudents ){
                free.add(this.coursesRep.getAll().get(index));
            }
        }
        return free;
    }

    //3
    public List<Student> retrieveStudentsEnrolledForACourse(long courseId){
        List<Student> enrolledStud = new ArrayList<>();
        for (Student student: this.studentsRep.getAll()){
            if (student.getEnrolledCourses().contains(courseId))
                enrolledStud.add(student);
        }
        return enrolledStud;
    }

    //4
    public List<Course> getAllCourses(){
        return this.coursesRep.getAll();
    }

    public List<Student> getAllStudents(){
        return this.studentsRep.getAll();
    }

    //5
    public void deleteCourse(long teacherId, long courseId) throws DoesNotExistException, TeacherException {
        Teacher teacher = null;
        for (Teacher teach : teachersRep.getAll())
            if (teach.getTeacherId() == teacherId) {
                teacher = teach;
                break;
            }
        if (teacher == null)
            throw new DoesNotExistException("This teacher isn't in the repository!");

        Course course = null;
        for (Course co : coursesRep.getAll())
            if (co.getCourseId() == courseId) {
                course = co;
                break;
            }
        if (course == null)
            throw new DoesNotExistException("This course isn't in the repository!");

        if (course.getTeacher() != teacher.getTeacherId())
            throw new TeacherException("This course isn't taught by the teacher given!");

        for (Student student : this.studentsRep.getAll()) {
            for (long cursId : student.getEnrolledCourses())
                if (courseId == cursId){
                    student.removeCourses(course);
                    break;
                }
            this.studentsRep.update(student);
        }
        teacher.removeCourses(course);
        this.teachersRep.update(teacher);

        this.coursesRep.delete(course);
    }

    //6
    public void addTeacher(String firstN, String lastN, long teachId) throws ExistentElementException {
        Teacher teacher = null;
        for (Teacher teach : teachersRep.getAll())
            if (teach.getTeacherId() == teachId) {
                teacher = teach;
                break;
            }
        if (teacher != null)
            throw new ExistentElementException("This teacher is already in the repository!");

        teachersRep.create(new Teacher(firstN, lastN, teachId, new ArrayList<>()));
    }


    //7
    public void addStudent(String firstN, String lastN, long studId) throws ExistentElementException {
        Student student = null;
        for(Student stud: studentsRep.getAll())
            if(stud.getStudentId()==studId){
                student = stud;
                break;
            }
        if(student!=null)
            throw new ExistentElementException("This student is already in the repository!");
        this.studentsRep.create(new Student(firstN, lastN,studId, 0, new ArrayList<>()));
    }

    //8
    public void addCourse(long cId, String nameC, long teacherId, int maxEnroll, int creditsNr) throws ExistentElementException, DoesNotExistException {
        Course course = null;
        for(Course co: coursesRep.getAll())
            if(co.getCourseId() ==cId){
                course = co;
                break;
            }
        if(course!=null)
            throw new ExistentElementException("This course already in the repository!");

        Teacher teacher = null;
        for (Teacher teach : teachersRep.getAll())
            if (teach.getTeacherId() == teacherId) {
                teacher = teach;
                break;
            }
        if (teacher == null)
            throw new DoesNotExistException("This teacher isn't in the repository!");

        Course course2 = new Course(cId, nameC, teacherId, maxEnroll, creditsNr, new ArrayList<>());
        this.coursesRep.create(course2);
        teacher.addCourses(course2);

        this.teachersRep.update(teacher);
    }


    public void readAllFiles(){
        try {
            this.studentsRep.readJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.coursesRep.readJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.teachersRep.readJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToAllFiles(){
        try {
            this.studentsRep.writeToJason();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.coursesRep.writeToJason();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.teachersRep.writeToJason();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //9
    public void sortStudentCredits(){
        this.studentsRep.sortRep();
    }

    //10
    public void sortCourseNrStudents(){
        this.coursesRep.sortRep();
    }

    //11
    public List<Student> filterStudents(){
        List<Student> students = this.studentsRep.getAll();
        return students.stream().filter(stud->stud.getTotalCredits() >= 6).toList();
    }

    //12
    public List<Course> filterCourse(){
        List<Course> courses = this.coursesRep.getAll();
        return courses.stream().filter(cour->cour.getMaxEnrollment() > 30).toList();
    }

}


