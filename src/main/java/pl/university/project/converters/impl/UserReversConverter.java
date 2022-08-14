package pl.university.project.converters.impl;

import org.apache.commons.collections4.CollectionUtils;
import pl.university.project.models.User;
import pl.university.project.odata.UserData;
import pl.university.project.populators.Populator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserReversConverter  {

    private List<Populator> userPopulators;


    public User convert(UserData source) {
        User target = new User();
        if (source != null) {
            userPopulators.forEach(populator -> populator.populate(source, target));
        }
        return target;
    }

    public List<User> convertAll(List<UserData> sourceList) {
        List<User> targetList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(sourceList)) {
            targetList.addAll(sourceList.stream().map(this::convert).collect(Collectors.toList()));
        }
        return targetList;
    }

    public void setUserPopulators(List<Populator> userPopulators) {
        this.userPopulators = userPopulators;
    }
}