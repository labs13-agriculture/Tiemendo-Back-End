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
    private ItemType item;

    @ManyToOne
    @JoinColumn(name="transaction")
    private Transaction transaction;

    public TransactionItem() {
    }

    public TransactionItem(int quantity, ItemType item, double unitPrice, Transaction transaction) {
        this.quantity = quantity;
        this.item = item;
        this.unitPrice = unitPrice;
        this.transaction = transaction;
    }

    //didn't have a constructor with unitProce

    public TransactionItem(int quantity, double unitPrice, ItemType item, Transaction transaction) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.item = item;
        this.transaction = transaction;
    }
    //didn't have getters or setters for price
    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
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

    public ItemType getItem() {
        return item;
    }

    public void setItem(ItemType item) {
        this.item = item;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

   
}
