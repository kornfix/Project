package pl.university.project.services;

import java.util.List;

public interface DefaultService<T,S> {

    List<T> getAllObjects();

    T getObjectById(S id);

    S saveObject(T object);

    S updateObject(T object);

    void deleteObject(S id);
}
