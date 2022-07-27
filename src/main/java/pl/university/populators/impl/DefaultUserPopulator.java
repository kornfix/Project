package pl.university.populators.impl;


import org.springframework.stereotype.Component;
import pl.university.models.User;
import pl.university.odata.UserData;
import pl.university.populators.ObjectPopulator;

@Component
public class DefaultUserPopulator implements ObjectPopulator<User, UserData> {

    @Override
    public void populate(User userModel, UserData userData) {
        userData.setFirstName(userModel.getFirstName());
        userData.setLastName(userModel.getLastName());
    }
}
