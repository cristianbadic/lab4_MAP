package Repository;

import java.io.IOException;
import java.util.List;

public interface FileRepository<T> extends ICrudRepository<T>{

    void readJson() throws IOException;

    void writeToJason() throws IOException;

    void sortRep();

}
