package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public Client addNewClient(Client client)
    {
       Client newClient = new Client();

       newClient.setFirstname(client.getFirstname());
       newClient.setLastname(client.getLastname());
       newClient.setVillage(client.getVillage());
       newClient.setLoanamount(client.getLoanamount());
       newClient.setLid(client.getLid());
       newClient.setLdd(client.getLdd());
       newClient.setMaizeinventory(client.getMaizeinventory());
       newClient.setMaizegoal(client.getMaizegoal());

       return clientrepos.save(newClient);
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
