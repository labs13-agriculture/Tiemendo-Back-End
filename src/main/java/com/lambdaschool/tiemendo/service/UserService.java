package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.User;

import java.util.List;

public interface UserService
{

    List<User> findAll();
    List<User> findUsersByUsernameIsLike(String user);

    User findUserById(long id);

    void delete(long id);

    User save(User user);

    User update(User user, long id);
}