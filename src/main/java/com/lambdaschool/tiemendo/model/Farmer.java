package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "farmers")
public class Farmer extends Client
{
    private long startyear;
    //todo - Annual turnover data

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "farmerlocationid", referencedColumnName = "farmerlocationid")
    @JsonIgnoreProperties("farmer")
    private FarmerLocation farmerlocation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "farmercontactid", referencedColumnName = "farmercontactid")
    @JsonIgnoreProperties("farmer")
    private FarmerContact farmercontact;

    @OneToMany(mappedBy="farmer")
    @JsonIgnoreProperties("farmer")
    private List<Yield> yieldHistory = new ArrayList<>();

    //TODO payment schedule
    //TODO Yields

    public Farmer() {
    }

    public Farmer(long startyear, FarmerLocation farmerlocation, FarmerContact farmercontact, List<Yield> yieldHistory) {
        this.startyear = startyear;
        this.farmerlocation = farmerlocation;
        this.farmercontact = farmercontact;
        this.yieldHistory = yieldHistory;
    }

    public Farmer(String name, long startyear, FarmerLocation farmerlocation, FarmerContact farmercontact) {
        super(name);
        this.startyear = startyear;
        this.farmerlocation = farmerlocation;
        this.farmercontact = farmercontact;
    }

    public Farmer(String name, boolean isLead, long startyear, FarmerLocation farmerlocation, FarmerContact farmercontact, List<Yield> yieldHistory) {
        super(name, isLead);
        this.startyear = startyear;
        this.farmerlocation = farmerlocation;
        this.farmercontact = farmercontact;
        this.yieldHistory = yieldHistory;
    }

    public long getStartyear() {
        return startyear;
    }

    public void setStartyear(long startyear) {
        this.startyear = startyear;
    }

    public FarmerLocation getFarmerlocation() {
        return farmerlocation;
    }

    public void setFarmerlocation(FarmerLocation farmerlocation) {
        this.farmerlocation = farmerlocation;
    }

    public FarmerContact getFarmercontact() {
        return farmercontact;
    }

    public void setFarmercontact(FarmerContact farmercontact) {
        this.farmercontact = farmercontact;
    }

    public List<Yield> getYieldHistory() {
        return yieldHistory;
    }

    public void setYieldHistory(List<Yield> yieldHistory) {
        this.yieldHistory = yieldHistory;
    }
}
