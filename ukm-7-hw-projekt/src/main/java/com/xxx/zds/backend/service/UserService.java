package com.xxx.zds.backend.service;


import com.xxx.zds.backend.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

}
