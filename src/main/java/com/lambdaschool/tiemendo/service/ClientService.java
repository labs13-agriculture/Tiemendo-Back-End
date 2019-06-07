package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.Role;

public interface ClientService {

    Client findClientById(long id);

    void delete(long id);

    Client save(Client client);
}
