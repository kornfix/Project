package pl.university.project.converters.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import pl.university.project.converters.Converter;
import pl.university.project.models.User;
import pl.university.project.odata.UserData;
import pl.university.project.populators.Populator;
import pl.university.project.populators.impl.DefaultUserPopulator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter implements Converter<User, UserData> {

    //    @Resource
    private DefaultUserPopulator defaultUserPopulator = new DefaultUserPopulator();

    //    @Qualifier("userPopulators")
    private List<Populator> userPopulators = List.of(defaultUserPopulator);


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
            userDataList.addAll(sourceList.stream().map(this::convert).collect(Collectors.toList()));
        }
        return userDataList;
    }


}
