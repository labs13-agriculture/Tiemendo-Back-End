package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.PaymentSchedule;
import com.lambdaschool.tiemendo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service(value = "clientService")
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client findClientById(long id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Client save(Client client) {
        return null;
    }

    @Override
    public List<PaymentSchedule> getPaymentSchedule(long clientid) {
        return findClientById(clientid).getPaymentSchedule();
    }

    @Override
    @Transactional
    public Client generateNewSchedule(Client client) {

        Client current = findClientById(client.getId());

        if (client.getPaymentAmount() != current.getPaymentAmount()) {
            current.setPaymentAmount(client.getPaymentAmount());
        }
        if (!client.getPaymentStartDate().equals(current.getPaymentStartDate())) {
            current.setPaymentStartDate(client.getPaymentStartDate());
        }
        if (client.getPaymentFrequency() != current.getPaymentFrequency()) {
            current.setPaymentFrequency(client.getPaymentFrequency());
        }
        if (!client.getFrequencyUnit().equals(current.getFrequencyUnit())) {
            current.setFrequencyUnit(client.getFrequencyUnit());
        }

        current.generatePaySchedule();
        System.out.println("Generated Payment Schedule");
        current.generatePaySchedule().forEach(sched -> {
            System.out.println("Due Date: " + sched.getDateDue());
        });
        clientRepository.save(current);
        System.out.println("Saved Client");

        return current;
    }

    @Override
    @Transactional
    public List<PaymentSchedule> updatePayment(long clientid, LocalDate key, LocalDate value) {
        /*
        * updates value stored by key if key is found
        */
        Client c = findClientById(clientid);
        for(PaymentSchedule sched: c.getPaymentSchedule()) {
            if (sched.getDateDue().equals(key)) {
                sched.setDatePaid(value);
                break;
            }
        }
        return clientRepository.save(c).getPaymentSchedule();
    }

    @Override
    public List<PaymentSchedule> deletePayment(long clientid, LocalDate date) {
        /*
        * Deletes a scheduled payment by removing date from clients payment schedule
        */
        Client c = findClientById(clientid);
        try {
            c.getPaymentSchedule().remove(date);
        } catch (Exception exc) {
            throw new ResourceNotFoundException("Could not find payment date scheduled for " + date);
        }
        return clientRepository.save(c).getPaymentSchedule();
    }
}
