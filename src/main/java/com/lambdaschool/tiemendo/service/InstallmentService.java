package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Installment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface InstallmentService
{

    Page<Installment> findAll(Pageable pageable);

    Page<Installment> findAllByClient(Pageable pageable, long id);

    Installment findInstallmentById(long id);

    Page<Installment> save (Pageable pageable, Installment installment, Long id);

    Page<Installment> update(Pageable pageable, Installment installment, Long id);

    Page<Installment> delete(Pageable pageable, long id);

}
