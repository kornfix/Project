package pl.university.project.services;

import java.util.List;

public interface DefaultService<T> {

    List<T> getAllObjects();

    T getObjectById(Long id);

    Long saveObject(T object);

    Long updateObject(T object);

    void deleteObject(Long id);
}
