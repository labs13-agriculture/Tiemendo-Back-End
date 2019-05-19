package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ClientRepository extends CrudRepository<Client, Long>
{

    ArrayList<Client> findClientByFirstnameContaining(String name);

    @Modifying
    @Query(value = "DELETE FROM client WHERE clientid = :clientid", nativeQuery = true)
    void deleteClientFromClient(long clientid);

}
