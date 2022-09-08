package pl.university.project.converters;

import java.util.Collection;

public interface Converter<S, T> {

    T convert(S source);

    void convert(S source, T target);

    Collection<T> convertAll(Collection<S> source);

}
