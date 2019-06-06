package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.Installment;
import com.lambdaschool.tiemendo.repository.ClientRepository;
import com.lambdaschool.tiemendo.repository.InstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Installment> findAll()
    {
        List<Installment> list = new ArrayList<>();
        installmentrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Installment findInstallmentById(long id) throws EntityNotFoundException
    {
        return installmentrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }


    @Override
    public Client save(Installment installment, Long id)
    {
        Installment newInstallment = new Installment();
        Client client = clientRepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        newInstallment.setAmountPaid(installment.getAmountPaid());
        newInstallment.setMode(installment.getMode());
        newInstallment.setDatePaid(new Date());
        newInstallment.setOfficer(installment.getOfficer());
        newInstallment.setClient(client);

        client.getInstallments().add(newInstallment);

        return clientRepos.save(client);
    }

    @Override
    public Installment update(Installment installment, Long id)
    {
        Installment oldInstallment = findInstallmentById(id);


        oldInstallment.setAmountPaid(installment.getAmountPaid());
        oldInstallment.setMode(installment.getMode());
        oldInstallment.setDatePaid(new Date());
        oldInstallment.setOfficer(installment.getOfficer());



        return installmentrepos.save(oldInstallment);
    }


    @Transactional
    @Override
    public void delete(long id)
    {
    if (installmentrepos.findById(id).isPresent())
    {
        installmentrepos.deleteById(id);
    }else
    {
        throw new EntityNotFoundException(Long.toString(id));
    }
    }
}
