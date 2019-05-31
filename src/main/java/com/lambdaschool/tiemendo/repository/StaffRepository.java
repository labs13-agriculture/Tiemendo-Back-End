package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Staff;
import org.springframework.data.repository.CrudRepository;

public interface StaffRepository extends CrudRepository<Staff, Long>
{
}
