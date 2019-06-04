package com.lambdaschool.tiemendo.model;

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
public class InventoryItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;
    private boolean active = true;

    @OneToMany(mappedBy = "item")
    private List<TransactionItem> transactions = new ArrayList<>();

    public InventoryItem() {
    }

    public InventoryItem(String name) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<TransactionItem> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionItem> transactions) {
        this.transactions = transactions;
    }
}
