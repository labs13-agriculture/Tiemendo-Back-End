package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Client;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashMap;

public interface ClientService {

    ArrayList<Client> findAll(Pageable pageable);
    ArrayList<Client> search(Pageable pageable, HashMap<String, String> searchFields);
    Client findById(long id);
    Client add(Client client);
    Client update(Client client);
    void delete(long id);
}
