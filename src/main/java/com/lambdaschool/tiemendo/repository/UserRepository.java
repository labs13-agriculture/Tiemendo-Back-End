package com.lambdaschool.tiemendo.repository;


import com.lambdaschool.tiemendo.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long>
{
    User findByUsername(String username);
}
