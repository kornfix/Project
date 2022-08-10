package pl.university.project.populators;

public interface Populator<S,T> {

    void populate(S source, T target);
}
