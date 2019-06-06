package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.Installment;

import java.util.List;

public interface InstallmentService
{

    List<Installment> findAll();

    Installment findInstallmentById(long id);

    Client save (Installment installment, Long id);

    Installment update(Installment installment, Long id);

    void delete(long id);

}
