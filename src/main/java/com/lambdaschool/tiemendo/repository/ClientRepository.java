package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long>
{
    @Modifying
    @Query(value = "UPDATE client SET is_lead=:islead, name=:name WHERE id=:id", nativeQuery = true)
    void updateClient(long id, boolean islead, String name);
}
