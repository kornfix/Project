package pl.university.project.populators.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.university.project.models.User;
import pl.university.project.odata.UserData;
import pl.university.project.populators.Populator;

import javax.annotation.Resource;

public class UserReversPopulator implements Populator<UserData, User> {

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void populate(UserData source, User target) {
        target.setId(source.getId());
        target.setUsername(source.getNewUsername());
        target.setRole(source.getRole());
        target.setEnabled(source.isEnabled());
        if (source.getId() == null) {
            target.setRole("ROLE_USER");
        }
        target.setPassword(bCryptPasswordEncoder.encode(source.getPassword()));
    }
}