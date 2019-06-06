package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
*
*  These Items are what allows Users to add different items types.
*  For Drop Downs to add transactions, you would hit an endpoint to get a list of possible items
*  these are those items.
*
*  These are NOT tied directly with transactions.
*  for the items tied to transaction see TransactionItem
*/

@Entity
@Table(name="inventory_item")
public class ItemType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;
    private Boolean active = true;

    @OneToMany(mappedBy = "item")
    @JsonIgnoreProperties("item")
    private List<TransactionItem> transactions = new ArrayList<>();

    public ItemType() {
    }

    public ItemType(String name) {
        this.name = name;
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

    public Boolean getActive() {
        return active;
    }//changed name for Service impl

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<TransactionItem> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionItem> transactions) {
        this.transactions = transactions;
    }



}
