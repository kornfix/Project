package pl.university.project.populators.impl;


import org.springframework.stereotype.Component;
import pl.university.project.models.User;
import pl.university.project.odata.UserData;
import pl.university.project.populators.Populator;

@Component
public class DefaultUserPopulator implements Populator<User, UserData> {

    @Override
    public void populate(User userModel, UserData userData) {
        userData.setId(String.valueOf(userModel.getId()));
        userData.setFirstName(userModel.getFirstName());
        userData.setLastName(userModel.getLastName());
    }
}
