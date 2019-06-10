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
    @JsonIgnoreProperties("yieldHistory")
    private Retailer retailer;


}
