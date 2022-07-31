package pl.university.project.populators;

public interface ObjectPopulator<S,T> {

    void populate(S source, T target);
}
