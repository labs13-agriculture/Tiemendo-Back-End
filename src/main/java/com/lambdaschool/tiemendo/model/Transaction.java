package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //    Transaction ID
    private long id;
    //    Transaction type
    // This can be either Cash or credit
    private String type;
    //    Date of Transaction
    private Date date;
//    Inputs bought:
//    Item name + Quantity
    @OneToMany(mappedBy="transaction")
    @JsonIgnoreProperties("transaction")
    private List<TransactionItem> inputs = new ArrayList<>();
//    Sales personnel (officer who make the payments for the farmer)
    private String personnel;

    @ManyToOne
    @JoinColumn(name="client")
    private Client client;

    public Transaction() {
    }

    public Transaction(String type, Date date, ArrayList<TransactionItem> inputs, String personnel, Client client) {
        this.type = type;
        this.date = date;
        this.inputs = inputs;
        this.personnel = personnel;

        this.client = client;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<TransactionItem> getInputs() {
        return inputs;
    }

    public void setInputs(ArrayList<TransactionItem> inputs) {
        this.inputs = inputs;
    }

    public double getTotal() {
        var total = 0;
        if(inputs.size() > 0) {
            for(TransactionItem i: inputs) {
                total += (i.getQuantity() * i.getUnitPrice());
            }
        }
        return total;
    }


    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
