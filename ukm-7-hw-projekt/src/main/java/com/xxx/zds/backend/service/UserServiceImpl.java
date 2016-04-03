package com.xxx.zds.backend.service;


import com.xxx.zds.backend.model.User;
import com.xxx.zds.backend.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@Service
@Validated
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional
    public User saveUser(@NotNull @Valid final User user) {
        LOGGER.debug("Creating user...");
//        User existing = repository.findOne(user.getId());
//        if (existing != null) {
////                user.setId(new Random().nextLong());
//            return repository.save(user);
////            throw new UserAlreadyExistsException(
////                    String.format("There already exists a user with id=%s", user.getId()));
//        }
        return repository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        LOGGER.debug("Retrieving the list of all users..");
        return repository.findAll();
    }

}
