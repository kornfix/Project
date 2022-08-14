package pl.university.project.services.impl;


import org.springframework.stereotype.Service;
import pl.university.project.converters.impl.UserConverter;
import pl.university.project.converters.impl.UserReversConverter;
import pl.university.project.models.User;
import pl.university.project.odata.UserData;
import pl.university.project.repositories.UserRepository;
import pl.university.project.services.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class DefaultUserService implements UserService {

    @Resource
    private UserConverter userConverter;

    @Resource
    private UserReversConverter userReversConverter;

    @Resource
    private UserRepository userRepository;

    @Override
    public List<UserData> getAllUsers() {
        return userConverter.convertAll(userRepository.findAll());
    }

    @Override
    public UserData getUserById(String id) {
        Long userId = Long.valueOf(id);
        return userConverter.convert(userRepository.findById(userId).orElse(null));
    }

    public UserData save(UserData userData) {

        User user = userReversConverter.convert(userData);
        userRepository.save(user);
        return userConverter.convert(user);
    }

}
