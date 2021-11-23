/*
import Model.Course;
import Model.Student;
import Model.Teacher;
import Repository.CourseRepository;
import Repository.StudentRepository;
import Repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

public class RegistrationSystem {
    private CourseRepository courses;
    private TeacherRepository teachers;
    private StudentRepository students;



    public RegistrationSystem(CourseRepository courses, TeacherRepository teachers, StudentRepository students) {
        this.courses = courses;
        this.teachers = teachers;
        this.students = students;
    }

    */
/**
     *if the course still has places and the total credits of the student added with the credits of the new course don't exceed 30
     * the student is added to the courses student list and vice versa
     * @param course where the student wants to be enrolled
     * @param student object supposed to be enrolled
     * @return true if the student is enrolled, else otherwise
     *//*

    public boolean register(Course course, Student student){
        if (course.getMaxEnrollment() > course.getStudentsEnrolled().size()
                && student.getTotalCredits() + course.getCredits() <31){
            course.addStudents(student);
            student.addCourses(course);
            return true;
        }
        return false;
    }


    */
/**
     * search through the courses repository to find te courses with free places, witch are added to the free list
     * maxEnrolled variable, used to get the max nr. of enrollments for each curse in the repo
     * nrStudents, used to find the number of student enrolled for each specific course in the repo
     * @return list of courses
     *//*

    public List<Course> retrieveCoursesWithFreePlaces(){
        List<Course> free= new ArrayList<>();
        for (int index =0; index < this.courses.getAll().size(); index ++){
            int maxEnrolled = this.courses.getAll().get(index).getMaxEnrollment();
            int nrStudents = this.courses.getAll().get(index).getStudentsEnrolled().size();
            if (maxEnrolled > nrStudents ){
                free.add(this.courses.getAll().get(index));
            }
        }
        return free;
    }

    public List<Student> retrieveStudentsEnrolledForACourse(Course course){
        return course.getStudentsEnrolled();
    }


    public List<Course> getAllCourses(){
        return this.courses.getAll();
    }

    */
/**
     *the number of credits for th course is changed with the help of the method setCredits, implemented in the class Course
     * @param course Course object
     * @param newNrCredits integer, the new number of credits for course
     *//*

    public void changeCredits(Course course,int newNrCredits){
        course.setCredits(newNrCredits);
    }

    */
/**
     * if the teacher teaches the course(given as param), the course is dek=leted from the course repository
     * and from the Courses list from each Student (with the help of the removeCourses and updateCourses methods) and from the teacher
     * @param teacher Teacher object
     * @param course Course object
     * @return true if the course is deleted, else otherwise
     *//*

    public boolean deleteCourse(Teacher teacher, Course course){
        if (course.getTeacher() == teacher){
            for (int idx = 0; idx < course.getStudentsEnrolled().size(); idx++) {
                course.getStudentsEnrolled().get(idx).updateCourses(course, 0);
                course.getStudentsEnrolled().get(idx).removeCourses(course);
                teacher.removeCourses(course);
            }

            this.courses.delete(course);
            return true;
        }
        return false;
    }
}
*/
