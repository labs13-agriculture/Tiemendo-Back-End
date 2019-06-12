package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.Role;

import java.time.LocalDate;
import java.util.HashMap;

public interface ClientService {

    Client findClientById(long id);
    void delete(long id);
    Client save(Client client);
    HashMap<LocalDate, LocalDate> getPaymentSchedule(long clientid);
    HashMap<LocalDate, LocalDate> generateNewSchedule(Client client);
    HashMap<LocalDate, LocalDate> updatePayment(long clientid, LocalDate key, LocalDate payday);
    HashMap<LocalDate, LocalDate> deletePayment(long clientid, LocalDate date);
}
