package com.lambdaschool.tiemendo.repository;


import com.lambdaschool.tiemendo.model.CropType;
import com.lambdaschool.tiemendo.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long>
{
    User findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE UPPER(username) LIKE CONCAT(UPPER(:username),'%')", nativeQuery = true)
    List<User> findByUsernameIgnoreCase(String username);
}
