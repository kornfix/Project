package pl.university.project.services;

import java.util.Collection;

public interface Service<T,S> {

    Collection<T> getAllObjects();

    T getObjectById(S id);

    S saveObject(T object);

    S updateObject(T object);

    void deleteObject(S id);
}
