package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.repository.ClientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service(value = "clientService")
public class ClientServiceImpl implements ClientService {

    // using constructor injection over autowired due to required nature of repo
    ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ArrayList<Client> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ArrayList<Client> search(Pageable pageable, HashMap<String, String> searchFields) {
        return null;
    }

    @Override
    public Client findById(long id) {
        return clientRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Could not find Client with id: " + id);
        });
    }

    @Override
    public Client add(Client client) {
        return null;
    }

    @Override
    public Client update(Client client) {
        Client current = findById(client.getId());
        // TODO Fill in the rest of the update logic
        return clientRepository.save(current);
    }

    @Override
    public void delete(long id) {

    }
}
