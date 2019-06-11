package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="inventory")
public class Inventory extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long invid;
    private int quantity;

    @ManyToOne
    @JoinColumn(name="item")
    @JsonIgnoreProperties("inventory")
    private ItemType item;

    public Inventory() {
    }

    public Inventory(int quantity, ItemType item) {
        this.quantity = quantity;
        this.item = item;
    }

    public long getInvid() {
        return invid;
    }

    public void setInvid(long invid) {
        this.invid = invid;
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
}
