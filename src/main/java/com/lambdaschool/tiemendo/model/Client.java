package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "client")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Client extends Auditable {
    // Unique ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private boolean isLead;
    private String type;

    // Transactions
    @OneToMany(mappedBy="client", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("client")
    private List<Transaction> transactions = new ArrayList<>();
    // Installments

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("client")
    private List<Installment> installments = new ArrayList<>();

    //TODO Contact
    //TODO Location


    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public Client(String name, boolean isLead) {
        this.name = name;
        this.isLead = isLead;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLead() {
        return isLead;
    }

    public void setLead(boolean lead) {
        isLead = lead;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Installment> installments) {
        this.installments = installments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
