package pl.university.project.services.impl;


import org.springframework.stereotype.Service;
import pl.university.project.converters.impl.UserConverter;
import pl.university.project.converters.impl.UserReversConverter;
import pl.university.project.models.User;
import pl.university.project.odata.UserData;
import pl.university.project.repositories.UserRepository;
import pl.university.project.services.DefaultService;

import javax.annotation.Resource;
import java.util.Collection;

@Service("userService")
public class DefaultUserService implements DefaultService<UserData, Long> {

    @Resource
    private UserConverter userConverter;

    @Resource
    private UserReversConverter userReversConverter;

    @Resource
    private UserRepository userRepository;

    @Override
    public Collection<UserData> getAllObjects() {
        return userConverter.convertAll(userRepository.findAll());
    }

    @Override
    public UserData getObjectById(Long id) {
        User user = getUserById(id);
        if (user == null) {
            return null;
        }
        return userConverter.convert(user);
    }

    @Override
    public Long saveObject(UserData userData) {
        if (userData == null) {
            return null;
        }
        User user = userRepository.saveAndFlush(userReversConverter.convert(userData));
        return user.getId();
    }

    public boolean isUsernameUsed(String username) {
       return userRepository.findByUsername(username)!=null;
    }

    @Override
    public Long updateObject(UserData userData) {
        User user = getUserById(userData.getId());
        if (user == null) {
            return null;
        }
        userReversConverter.convert(userData, user);
        userRepository.saveAndFlush(user);
        return user.getId();
    }

    @Override
    public void deleteObject(Long id) {
        userRepository.deleteById(id);
    }

    private User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
