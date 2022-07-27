package pl.university.services.impl;


import org.springframework.stereotype.Service;
import pl.university.odata.UserData;
import pl.university.project.repositories.UserRepository;
import pl.university.services.UserService;

import java.util.List;

@Service("userService")
public class DefaultUserService extends UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserData> getAllCustomers() {

        return null;
    }


}
