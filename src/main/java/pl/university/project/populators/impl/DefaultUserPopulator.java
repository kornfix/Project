package pl.university.project.populators.impl;


import pl.university.project.models.User;
import pl.university.project.odata.UserData;
import pl.university.project.populators.Populator;


public class DefaultUserPopulator implements Populator<User, UserData> {

    @Override
    public void populate(User userModel, UserData userData) {
        userData.setId(String.valueOf(userModel.getId()));
        userData.setUsername(userModel.getUsername());
        userData.setRole(userModel.getRole());
        userData.setEnabled(userModel.isEnabled());
    }
}
