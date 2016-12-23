package com.bottles.five.dao;

import com.bottles.five.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginAndPassword(String login, String password);
    User findByLoginOrEmail(String login, String email);
    User findByLogin(String login);
}
