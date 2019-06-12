package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;

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
    public HashMap<LocalDate, LocalDate> getPaymentSchedule(long clientid) {
        return new HashMap<>(findClientById(clientid).getPaymentSchedule());
    }

    @Override
    @Transactional
    public HashMap<LocalDate, LocalDate> generateNewSchedule(Client client) {

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
        current.setPaymentSchedule(current.generatePaySchedule());

        clientRepository.save(current);

        return new HashMap<>(current.getPaymentSchedule());
    }

    @Override
    @Transactional
    public HashMap<LocalDate, LocalDate> updatePayment(long clientid, LocalDate key, LocalDate value) {
        /*
        * updates value stored by key if key is found
        */
        Client c = findClientById(clientid);
        if (c.getPaymentSchedule().containsKey(key)){
            c.getPaymentSchedule().put(key, value);
        } else {
            throw new ResourceNotFoundException("Could not find a scheduled payment for that day");
        }
        return new HashMap<>(clientRepository.save(c).getPaymentSchedule());
    }

    @Override
    public HashMap<LocalDate, LocalDate> deletePayment(long clientid, LocalDate date) {
        /*
        * Deletes a scheduled payment by removing date from clients payment schedule
        */
        Client c = findClientById(clientid);
        if (c.getPaymentSchedule().containsKey(date)){
            c.getPaymentSchedule().remove(date);
        } else {
            throw new ResourceNotFoundException("Could not find a scheduled payment for that day");
        }
        return new HashMap<>(clientRepository.save(c).getPaymentSchedule());
    }
}
