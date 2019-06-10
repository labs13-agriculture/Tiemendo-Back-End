package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.repository.ClientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
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

        // REFLECTION API!!!! THIS IS AMAZING
        // get names of all fields of given class
        var fields = Client.class.getDeclaredFields();
        // iterate over the field names
        for(int i=0; i<fields.length; i++) {
            //save field name
            var field = fields[i].getName();
            try {
                // get the property descriptor and of the class based on field name
                PropertyDescriptor pd = new PropertyDescriptor(field, Client.class);
                // saves the getter method to variable getter
                Method getter = pd.getReadMethod();
                // invokes the method on the given instance of a class
                // after the class is the list of arguments if any
                // if farmer.getField != null
                // TODO make an array of field names to ignore?
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

    }
}
