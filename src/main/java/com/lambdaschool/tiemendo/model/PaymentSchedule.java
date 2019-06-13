package com.lambdaschool.tiemendo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="payment_schedule")
public class PaymentSchedule {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(nullable = false)
    private LocalDate dateDue;
    private LocalDate datePaid;

    public PaymentSchedule() {
    }

    public PaymentSchedule(Client client, LocalDate dateDue, LocalDate datePaid) {
        this.client = client;
        this.dateDue = dateDue;
        this.datePaid = datePaid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDateDue() {
        return dateDue;
    }

    public void setDateDue(LocalDate dateDue) {
        this.dateDue = dateDue;
    }

    public LocalDate getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(LocalDate datePaid) {
        this.datePaid = datePaid;
    }
}
