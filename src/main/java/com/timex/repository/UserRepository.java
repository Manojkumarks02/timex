package com.timex.repository;

import com.timex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameOrEmail(String usernameOrEmail, String orEmail);

    User findByEmail(String email);


}
