package Repository;

import Model.Course;
import Model.Teacher;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseRepository extends InMemoryRepository<Course> implements FileRepository<Course>{
    private String fileName;
    public CourseRepository(String fileName){
        super();
        this.fileName = fileName;
    }

    /**
     *updates the name, teacher, number of Credits and maximum enrollment from a course
     * with the attributes of a course given as parameter
     * @param object
     * @return updated course
     */
    @Override
    public Course update(Course object){
        Course courseToUpdate = this.repoList.stream()
                .filter(course -> course.getCourseId() == object.getCourseId())
                .findFirst()
                .orElseThrow();
        courseToUpdate.setName(object.getName());
        courseToUpdate.setTeacher(object.getTeacher());
        courseToUpdate.setCredits(object.getCredits());
        courseToUpdate.setMaxEnrollment(object.getMaxEnrollment());
        courseToUpdate.setStudentsEnrolled(object.getStudentsEnrolled());

        return courseToUpdate;
    }

    @Override
    public void readJson() throws IOException {
        Reader reader = new BufferedReader(new FileReader(fileName));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode n: parser){
            long id = n.path("courseId").asLong();
            String cName = n.path("name").asText();
            long teacherId = n.path("teacher").asLong();
            int maxE = n.path("maxEnrollment").asInt();
            int nrCred = n.path("credits").asInt();

            List<Long> students = new ArrayList<>();
            for (JsonNode elem : n.path("studentsEnrolled"))
            {
                students.add(elem.asLong());
            }


            Course course = new Course(id, cName, teacherId, maxE, nrCred, students);
            this.repoList.add(course);
        }
        reader.close();
    }

    @Override
    public void writeToJason() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        writer.writeValue(new File(this.fileName), this.repoList);

    }
    @Override
    public void sortRep(){
        this.repoList.sort(Course::compareCourse);
    }

}
