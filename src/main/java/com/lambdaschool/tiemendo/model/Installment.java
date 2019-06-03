package com.lambdaschool.tiemendo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="installment")
public class Installment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    Amount paid
    private double paid;
//    Date paid
    private Date datePaid;
//    Mode of payment
//    MTN mobile money, Bank payments, Cash payment
    private String mode;
//    Officer who logged the farmer’s payment
    private String officer;

//    @ManyToOne
//    @JoinColumn(name="client_id")
//    private Client client;

    public Installment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer;
    }

//    public Client getClient() {
//        return client;
//    }
//
//    public void setClient(Client client) {
//        this.client = client;
//    }
}
