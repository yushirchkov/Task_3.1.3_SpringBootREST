package ru.itmentor.spring.boot_security.demo.service;


import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    void createUser(User user);

    User readUser(long id);

    void updateUser(User user);

    void deleteUser(long id);

    List<User> getUsersAndRoles();
}
