package com.lambdaschool.tiemendo.repository;


import com.lambdaschool.tiemendo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
