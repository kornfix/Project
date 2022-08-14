package pl.university.project.services;

import pl.university.project.odata.UserData;

import java.util.List;

public interface UserService {

    List<UserData> getAllUsers();

    UserData getUserById(String id);

    UserData save(UserData user);
}
