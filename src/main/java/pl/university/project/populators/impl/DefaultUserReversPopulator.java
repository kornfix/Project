package pl.university.project.populators.impl;

import pl.university.project.models.User;
import pl.university.project.odata.UserData;
import pl.university.project.populators.Populator;

public class DefaultUserReversPopulator implements Populator<UserData, User> {

    @Override
    public void populate( UserData target, User source) {
        target.setId(source.getId());
        target.setUsername(source.getUsername());
        target.setRole(source.getRole());
        target.setEnabled(source.isEnabled());
    }
}