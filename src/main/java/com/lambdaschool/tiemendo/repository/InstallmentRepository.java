package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Installment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InstallmentRepository extends PagingAndSortingRepository<Installment, Long> {
}
