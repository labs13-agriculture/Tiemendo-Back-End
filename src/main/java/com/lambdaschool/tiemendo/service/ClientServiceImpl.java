package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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
}
