package pl.university.project.converters.impl;

import org.apache.commons.collections4.CollectionUtils;
import pl.university.project.converters.Converter;
import pl.university.project.models.User;
import pl.university.project.odata.UserData;
import pl.university.project.populators.Populator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserReversConverter implements Converter<UserData, User> {

    private List<Populator> populators;

    @Override
    public User convert(UserData source) {
        User target = new User();
        if (source != null) {
            populators.forEach(populator -> populator.populate(source, target));
        }
        return target;
    }

    @Override
    public User convert(UserData source, User target) {
        if (source != null) {
            populators.forEach(populator -> populator.populate(source, target));
        }
        return target;
    }

    @Override
    public Collection<User> convertAll(Collection<UserData> source) {
        Collection<User> targetList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(source)) {
            targetList.addAll(source.stream().map(this::convert).collect(Collectors.toList()));
        }
        return targetList;
    }

    @Override
    public Collection<User> convertAll(Collection<UserData> source, Collection<User> target) {
        if (CollectionUtils.isNotEmpty(source)) {
            target.addAll(source.stream().map(this::convert).collect(Collectors.toList()));
        }
        return target;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
