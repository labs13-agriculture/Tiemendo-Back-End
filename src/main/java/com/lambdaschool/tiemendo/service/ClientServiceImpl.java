package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.repository.ClientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
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
        var clients = new ArrayList<Client>();
        clientRepository.findAll(pageable).iterator().forEachRemaining(clients::add);
        return clients;
    }

    @Override
    public ArrayList<Client> search(Pageable pageable, HashMap<String, String> searchFields) {
        var results = new ArrayList<Client>();
        var keys = searchFields.keySet();
        var lead = false;
        var type = "";

        // TODO - add in implementation for pageable and sort

        if (keys.contains("isLead") && searchFields.get("isLead") != null) {
            lead = Boolean.valueOf(searchFields.get("isLead"));
        }

        if (keys.contains("type") && searchFields.get("type") != null) {
            type = searchFields.get("type");
        }

        if (keys.contains("name") && keys.contains("location")){
            String loc = searchFields.get("location");
            String name = searchFields.get("name");
            clientRepository.searchByNameAndLocationFields(name, loc, lead, type).iterator().forEachRemaining(results::add);
        } else if (keys.contains("name")) {
            String name = searchFields.get("name");
            clientRepository.searchByNameFields(name, lead, type).iterator().forEachRemaining(results::add);
        } else if (keys.contains("location")) {
            String loc = searchFields.get("location");
            clientRepository.searchByLocationFields(loc, lead, type).iterator().forEachRemaining(results::add);
        }
        return results;
    }

    @Override
    public Client findById(long id) {
        return clientRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Could not find Client with id: " + id);
        });
    }

    @Override
    public Client add(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        Client current = findById(client.getId());


        // TODO: Pull this logic out into an object util class for reusability
        // REFLECTION API!!!! THIS IS AMAZING
        // get names of all fields of given class
        var fields = Client.class.getDeclaredFields();
        var ignore = new ArrayList<String>(Arrays.asList("transactions", "installments"));
        // iterate over the field names
        for(int i=0; i<fields.length; i++) {
            //save field name
            var field = fields[i].getName();

            //If field name is in ignore list, continue to next field;
            if (ignore.contains(field)) continue;

            try {
                // get the property descriptor and of the class based on field name
                PropertyDescriptor pd = new PropertyDescriptor(field, Client.class);
                // saves the getter method to variable getter
                Method getter = pd.getReadMethod();
                // invokes the method on the given instance of a class
                // after the class is the list of arguments if any
                // similar to if object.getField != null
                if (getter.invoke(client) != null){
                    // get the setter method
                    Method setter = pd.getWriteMethod();
                    // use the setter method to set the value of the current object
                    // to the value of the new object
                    setter.invoke(current, getter.invoke(client));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return clientRepository.save(current);
    }

    @Override
    public void delete(long id) {
        clientRepository.delete(findById(id));
    }
}
