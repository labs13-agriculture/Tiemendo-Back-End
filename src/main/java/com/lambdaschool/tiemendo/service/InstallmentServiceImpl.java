package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.Installment;
import com.lambdaschool.tiemendo.repository.ClientRepository;
import com.lambdaschool.tiemendo.repository.InstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;


@Service
public class InstallmentServiceImpl implements InstallmentService
{

    @Autowired
    private InstallmentRepository installmentrepos;

    @Autowired
    private ClientRepository clientRepos;



    @Override
    public Page<Installment> findAll(Pageable pageable)
    {
        return installmentrepos.findAll(pageable);
    }

    @Override
    public Page<Installment> findAllByClient(Pageable pageable, long id) {
        var client = clientRepos.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Could not find Client with id: " + id);
        });
        return installmentrepos.findAllByClient(pageable, client);
    }

    @Override
    public Installment findInstallmentById(long id) throws EntityNotFoundException
    {
        return installmentrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }


    @Override
    public Page<Installment> save(Pageable pageable, Installment installment, Long id)
    {
        Installment newInstallment = new Installment();
        Client client = clientRepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        newInstallment.setAmountPaid(installment.getAmountPaid());
        newInstallment.setMode(installment.getMode());
        newInstallment.setDatePaid(new Date());
        newInstallment.setOfficer(installment.getOfficer());
        newInstallment.setClient(client);

        // This previously returned a client not a list of installments
        client.getInstallments().add(newInstallment);
        clientRepos.save(client);

        return findAllByClient(pageable, client.getId());
    }

    @Override
    public Page<Installment> update(Pageable pageable, Installment installment, Long id)
    {
        Installment oldInstallment = findInstallmentById(id);


        oldInstallment.setAmountPaid(installment.getAmountPaid());
        oldInstallment.setMode(installment.getMode());
        oldInstallment.setDatePaid(new Date());
        oldInstallment.setOfficer(installment.getOfficer());
        installmentrepos.save(oldInstallment);

        return findAllByClient(pageable, oldInstallment.getClient().getId());
    }


    @Transactional
    @Override
    public Page<Installment> delete(Pageable pageable, long id) {
        long clientId;
        if (installmentrepos.findById(id).isPresent()) {
            clientId = installmentrepos.findById(id).get().getClient().getId();
            installmentrepos.deleteById(id);

        } else {
            throw new EntityNotFoundException(Long.toString(id));
        }

        return findAllByClient(pageable, clientId);
    }
}
