package pl.university.project.converters;

import java.util.List;

public interface Converter<S, T> {

    T convert(S source);

    List<T> convertAll(List<S> source);
}
