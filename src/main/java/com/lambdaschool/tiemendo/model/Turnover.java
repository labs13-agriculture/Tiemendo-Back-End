package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="turnover")
public class Turnover {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    //    Number of Times Inventory turned over in a year
    private Integer actual;
    //    Their goal for turnover
    private Integer goal;
    //    Shop size
    private Integer sizeAmount;
    //    Year or season
    private String sizeUnit;

    private Integer year;

    @ManyToOne
    @JoinColumn(name="retailer_id")
    @JsonIgnoreProperties("goals")
    private Retailer retailer;

    public Turnover() {
    }

    public Turnover(Integer actual, Integer goal, Integer sizeAmount, String sizeUnit, Integer year, Retailer retailer) {
        this.actual = actual;
        this.goal = goal;
        this.sizeAmount = sizeAmount;
        this.sizeUnit = sizeUnit;
        this.year = year;
        this.retailer = retailer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getActual() {
        return actual;
    }

    public void setActual(Integer actual) {
        this.actual = actual;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Integer getSizeAmount() {
        return sizeAmount;
    }

    public void setSizeAmount(Integer sizeAmount) {
        this.sizeAmount = sizeAmount;
    }

    public String getSizeUnit() {
        return sizeUnit;
    }

    public void setSizeUnit(String sizeUnit) {
        this.sizeUnit = sizeUnit;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }
}
