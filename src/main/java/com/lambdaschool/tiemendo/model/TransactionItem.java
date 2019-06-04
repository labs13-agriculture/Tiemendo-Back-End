package com.lambdaschool.tiemendo.model;

import javax.persistence.*;

/*
 *
 *  These Transaction items tie a transaction, a qty, and an Inventory item
 *
 */

@Entity
@Table(name="transaction_item")
public class TransactionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantity;
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name="item")
    private InventoryItem item;

    @ManyToOne
    @JoinColumn(name="transaction")
    private Transaction transaction;

    public TransactionItem() {
    }

    public TransactionItem(int quantity, InventoryItem item, Transaction transaction) {
        this.quantity = quantity;
        this.item = item;
        this.transaction = transaction;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public InventoryItem getItem() {
        return item;
    }

    public void setItem(InventoryItem item) {
        this.item = item;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
