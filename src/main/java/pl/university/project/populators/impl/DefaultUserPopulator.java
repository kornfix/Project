package pl.university.project.populators.impl;


import pl.university.project.models.User;
import pl.university.project.odata.UserData;
import pl.university.project.populators.Populator;


public class DefaultUserPopulator implements Populator<User, UserData> {

    @Override
    public void populate(User source, UserData target) {
        target.setId(source.getId());
        target.setUsername(source.getUsername());
        target.setRole(source.getRole());
        target.setEnabled(source.isEnabled());
    }
}
