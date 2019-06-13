package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.PaymentSchedule;
import com.lambdaschool.tiemendo.model.Role;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface ClientService {

    Client findClientById(long id);
    void delete(long id);
    Client save(Client client);
    List<PaymentSchedule> getPaymentSchedule(long clientid);
    Client generateNewSchedule(Client client);
    List<PaymentSchedule> updatePayment(long clientid, LocalDate key, LocalDate payday);
    List<PaymentSchedule> deletePayment(long clientid, LocalDate date);
}
