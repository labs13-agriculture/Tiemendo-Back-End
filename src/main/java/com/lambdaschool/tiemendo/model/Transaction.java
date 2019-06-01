package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="transaction")
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
//    Total amount
    private double total;
//    Sales personnel (officer who make the payments for the farmer)
    private String personnel;

    public Transaction() {
    }

    public Transaction(String type, Date date, ArrayList<TransactionItem> inputs, double total, String personnel) {
        this.type = type;
        this.date = date;
        this.inputs = inputs;
        this.total = total;
        this.personnel = personnel;
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
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }
}
