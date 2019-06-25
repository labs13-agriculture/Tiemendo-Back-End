package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface ClientService {

    Page<Client> findAll(Pageable pageable, Boolean isLead);
    Page<Client> search(Pageable pageable, HashMap<String, String> searchFields);
    Client findById(long id);
    Client add(Client client);
    Client update(Client client);
    void delete(long id);
}
