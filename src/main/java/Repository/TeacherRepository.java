package Repository;

import Model.Teacher;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class TeacherRepository extends InMemoryRepository<Teacher> implements FileRepository<Teacher> {
    private String fileName;
    public TeacherRepository(String fileName) {
        super();
        this.fileName = fileName;
    }


    /**
     *updates the firstName and lastName and the courses of a teacher in the repoList with the attributes of a teacher given as parameter
     * @param object teacher with new attributes
     * @return updated teacher
     */
    @Override
    public Teacher update(Teacher object) {
        Teacher teacherToUpdate = this.repoList.stream()
                .filter(teacher -> teacher.getTeacherId() == object.getTeacherId())
                .findFirst()
                .orElseThrow();
        teacherToUpdate.setFirstName(object.getFirstName());
        teacherToUpdate.setFirstName(object.getLastName());
        teacherToUpdate.setCourses(object.getCourses());
        return teacherToUpdate;
    }

    /**
     * reads the Teacher objects from a json file and adds them to the repoList
     * @throws IOException if the reading was unsuccessful
     */
    @Override
    public void readJson() throws IOException {
        Reader reader = new BufferedReader(new FileReader(fileName));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode n: parser){
            String firstN = n.path("firstName").asText();
            String lastN = n.path("lastName").asText();
            long ids = n.path("teacherId").asLong();

            List<Long> co = new ArrayList<>();
            for (JsonNode elem : n.path("courses"))
            {
                co.add(elem.asLong());
            }


            Teacher teacher = new Teacher(firstN, lastN, ids, co);
            this.repoList.add(teacher);
        }
        reader.close();
    }

    /**
     * writes all Teacher objects fro the repoList to a json
     * @throws IOException if the writing to the file was unsuccessful
     */
    @Override
    public void writeToJason() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        writer.writeValue(new File(this.fileName), this.repoList);

    }

    @Override
    public void sortRep() {
    }


}
