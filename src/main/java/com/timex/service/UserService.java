package com.timex.service;

import com.timex.model.User;

public interface UserService {

    void registerUser(User user);

    User findByEmail(String email);

    void save(User user);


}
