package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.RetailerContact;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RetailerContactRepository extends CrudRepository<RetailerContact, Long>
{
    @Modifying
    @Query(value = "UPDATE retailercontacts SET dateofbirth=:dob, educationlevel=:education, email=:email, gender=:gender, name=:name, nationality=:nationality, phone=:phone, position=:position, title=:title WHERE retailercontactid=:id", nativeQuery = true)
    void update(long id, String dob, String education, String email, String gender, String name, String nationality, String phone, String position, String title);
}
