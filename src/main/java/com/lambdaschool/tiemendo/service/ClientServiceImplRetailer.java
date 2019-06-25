package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service(value = "retailerService")
public class ClientServiceImplRetailer extends ClientServiceImpl {

    /*
        Only overrides methods that have type specific implementation here,
        look in ClientServiceImpl for inherited methods
     */

    public ClientServiceImplRetailer(ClientRepository clientRepository) {
        super(clientRepository);
    }

    @Override
    public Page<Client> findAll(Pageable pageable, Boolean isLead) {
        return clientRepository.findClientsByTypeAndLead(pageable, "RETAILER", isLead);
    }

    @Override
    public Client add(Client client) {
        client.setType("RETAILER");
        return clientRepository.save(client);
    }
}
