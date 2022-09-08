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

public class UserConverter implements Converter<User, UserData> {

    private List<Populator> populators;

    @Override
    public UserData convert(User source) {
        UserData target = new UserData();
        if (source != null) {
            populators.forEach(populator -> populator.populate(source, target));
        }
        return target;
    }

    @Override
    public void convert(User source, UserData target) {
        if (source != null) {
            populators.forEach(populator -> populator.populate(source, target));
        }
    }

    @Override
    public Collection<UserData> convertAll(Collection<User> source) {
        Collection<UserData> targetList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(source)) {
            targetList.addAll(source.stream().map(this::convert).collect(Collectors.toList()));
        }
        return targetList;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
