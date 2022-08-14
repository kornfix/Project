package pl.university.project.populators.impl;

import pl.university.project.models.User;
import pl.university.project.odata.UserData;
import pl.university.project.populators.Populator;

public class DefaultUserReversPopulator implements Populator<UserData, User> {

    @Override
    public void populate( UserData userData, User userModel) {
        if(userData.getId()!=null) {
            userModel.setId(Long.parseLong(userData.getId()));
        }
        userModel.setFirstName(userData.getFirstName());
        userModel.setLastName(userData.getLastName());
    }
}