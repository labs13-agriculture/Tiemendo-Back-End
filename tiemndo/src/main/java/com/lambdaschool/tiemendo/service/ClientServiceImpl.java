package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Service(value = "clientService")
public class ClientServiceImpl implements ClientService
{

    @Autowired
    private ClientRepository clientrepos;

    @Override
    public ArrayList<Client> findAllClients()
    {
        ArrayList<Client> list = new ArrayList<>();
        clientrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Client findClientById(long id) throws EntityNotFoundException
    {
        return clientrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public Client addNewClient(Client client)
    {
        return null;
    }

    @Override
    public Client updateClient(Client client, long id)
    {
        return null;
    }

    @Override
    public void deleteClient(long id)
    {

    }
}
