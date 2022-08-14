package pl.university.project.services;

import java.util.List;

public interface DefaultService<T> {

    List<T> getAllObjects();

    T getObjectById(Long id);

    T saveObject(T object);

    T updateObject(T object);

    void deleteObject(Long id);
}
