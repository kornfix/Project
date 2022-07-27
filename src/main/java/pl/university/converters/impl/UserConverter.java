package pl.university.converters.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.university.converters.Converter;
import pl.university.models.User;
import pl.university.odata.UserData;
import pl.university.populators.ObjectPopulator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter implements Converter<User, UserData> {


    @Qualifier("userPopulators")
    private List<ObjectPopulator> userPopulators;


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
        List<UserData> userDataList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(sourceList)) {
            userDataList.addAll(
                    sourceList.stream().map(this::convert).collect(Collectors.toList()));
        }
        return userDataList;
    }


}
