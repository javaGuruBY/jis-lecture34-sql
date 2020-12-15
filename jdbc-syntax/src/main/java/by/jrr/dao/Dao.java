package by.jrr.dao;

import java.util.List;

public interface Dao<T, E> {

    //CRUD

    void save(T t); //save new if id !=null, otherwise update

    List<T> findAll();

    T findById(E id);

    void delete(T t);

    void deleteById(E id);
}
