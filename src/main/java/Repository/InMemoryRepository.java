package Repository;

import java.util.ArrayList;
import java.util.List;

public abstract class InMemoryRepository<T> implements ICrudRepository<T> {


    protected List<T> repoList;

    public InMemoryRepository() {
        this.repoList = new ArrayList<>();


    }

    /**
     *adds the obj to the list of T type elements, repoList
     * @param obj  element of a general type T
     * @return the object given as parameter, that was added to the list
     */
    @Override
    public T create(T obj) {
        this.repoList.add(obj);
        return obj;
    }

    /**
     * @return all elementes from the list repoList
     */
    @Override
    public List<T> getAll() {
        return this.repoList;
    }

    /**
     * removes the obj to the list of T type elements, repoList
     * @param obj  element of a general type T
     */
    @Override
    public void delete(T obj) {
        this.repoList.remove(obj);
    }

}

