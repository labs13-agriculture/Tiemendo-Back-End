package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    private Integer quantity;
    private Double unitPrice;

    @ManyToOne
    @JoinColumn(name="item")
    @JsonIgnoreProperties("transactions")
    private ItemType item;

    @ManyToOne
    @JoinColumn(name="transaction")
    @JsonIgnoreProperties("inputs")
    private Transaction transaction;

    public TransactionItem() {
    }

    //needed to change quantity and unit price to class/object type instead of primitive or update Controller null check
    public TransactionItem(Integer quantity, ItemType item, Double unitPrice, Transaction transaction) {
        this.quantity = quantity;
        this.item = item;
        this.unitPrice = unitPrice;
        this.transaction = transaction;
    }

    //didn't have a constructor with unitProce

    public TransactionItem(Integer quantity, Double unitPrice, ItemType item, Transaction transaction) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.item = item;
        this.transaction = transaction;
    }
    //didn't have getters or setters for price
    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
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
