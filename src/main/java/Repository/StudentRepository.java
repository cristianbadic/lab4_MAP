package Repository;
import Model.Student;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class StudentRepository extends InMemoryRepository<Student> implements FileRepository<Student>{

    private String fileName;
    public StudentRepository(String fileName){
        super();
        this.fileName = fileName;
    }


    /**
     *updates the firstName and lastName of a student in the repoList with the attributes of a student given as parameter
     * @param object
     * @return updated student
     */
    @Override
    public Student update(Student object){
        Student studentToUpdate = this.repoList.stream()
                .filter(student -> student.getStudentId() == object.getStudentId())
                .findFirst()
                .orElseThrow();
        studentToUpdate.setFirstName(object.getFirstName());
        studentToUpdate.setLastName(object.getLastName());
        studentToUpdate.setTotalCredits(object.getTotalCredits());
        studentToUpdate.setEnrolledCourses(object.getEnrolledCourses());

        return studentToUpdate;
    }

    @Override
    public void readJson() throws IOException {
        Reader reader = new BufferedReader(new FileReader(fileName));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode n: parser){
            String firstN = n.path("firstName").asText();
            String lastN = n.path("lastName").asText();
            long id = n.path("studentId").asLong();
            int credits = n.path("totalCredits").asInt();

            List<Long> courses = new ArrayList<>();
            for (JsonNode elem : n.path("enrolledCourses"))
            {
                courses.add(elem.asLong());
            }


            Student student = new Student(firstN, lastN, id, credits, courses);
            this.repoList.add(student);
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
        this.repoList.sort(Student::compareStudent);
    }
}