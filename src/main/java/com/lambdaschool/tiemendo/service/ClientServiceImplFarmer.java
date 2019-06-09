package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.repository.ClientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "farmerService")
public class ClientServiceImplFarmer extends ClientServiceImpl {

    /*
        Only overrides methods that have type specific implementation,
        look in ClientServiceImpl for those methods
     */

    public ClientServiceImplFarmer(ClientRepository clientRepository) {
        super(clientRepository);
    }

    @Override
    public ArrayList<Client> findAll(Pageable pageable) {
        var farmers = new ArrayList<Client>();
        clientRepository.findClientsByTypeIgnoreCase(pageable, "FARMER").iterator()
                .forEachRemaining(farmers::add);
        return farmers;
    }

    @Override
    public Client add(Client client) {
        client.setType("FARMER");
        return clientRepository.save(client);
    }
}
