package pl.university.populators;

public interface ObjectPopulator<S,T> {

    void populate(S S, T T);
}
