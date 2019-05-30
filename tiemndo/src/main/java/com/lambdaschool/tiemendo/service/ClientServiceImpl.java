package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
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


    @Transactional
    @Override
    public Client updateClient(Client client, long id)
    {
        var current = clientrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find client with the id \"" + id + "\" to update"));
                if (client.getFirstname() != null)
                    current.setFirstname(client.getFirstname());

                if (client.getLastname() != null)
                    current.setLastname(client.getLastname());

                if (client.getVillage() != null)
                    current.setVillage(client.getVillage());

                if (client.getLoanamount() != null)
                    current.setLoanamount(client.getLoanamount());

                if (client.getLid() != null)
                    current.setLid(client.getLid());

                if (client.getLdd() != null)
                    current.setLdd(client.getLdd());

                if (client.getMaizeinventory() > 0)
                    current.setMaizeinventory(client.getMaizeinventory());

                if (client.getMaizegoal() >= 0)
                    current.setMaizegoal(client.getMaizegoal());

                return clientrepos.save(current);

    }

    @Override
    public void deleteClient(long id)
    {
        clientrepos.delete(findClientById(id));
    }
}
