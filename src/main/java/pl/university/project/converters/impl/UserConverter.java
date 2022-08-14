package pl.university.project.converters.impl;

import org.apache.commons.collections4.CollectionUtils;
import pl.university.project.converters.Converter;
import pl.university.project.models.User;
import pl.university.project.odata.UserData;
import pl.university.project.populators.Populator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class UserConverter implements Converter<User, UserData> {

    private List<Populator> userPopulators;

    @Override
    public UserData convert(User source) {
        UserData target = new UserData();
        if (source != null) {
            userPopulators.forEach(populator -> populator.populate(source, target));
        }
        return target;
    }

    @Override
    public List<UserData> convertAll(List<User> sourceList) {
        List<UserData> targetList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(sourceList)) {
            targetList.addAll(sourceList.stream().map(this::convert).collect(Collectors.toList()));
        }
        return targetList;
    }

    public void setUserPopulators(List<Populator> userPopulators) {
        this.userPopulators = userPopulators;
    }
}
