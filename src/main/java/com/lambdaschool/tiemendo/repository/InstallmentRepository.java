package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.Installment;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface InstallmentRepository extends PagingAndSortingRepository<Installment, Long> {

    List<Installment> findAllByClient(Client c);
}
