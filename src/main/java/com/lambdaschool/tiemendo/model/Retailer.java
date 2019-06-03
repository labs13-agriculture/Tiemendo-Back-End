package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "retailers")
public class Retailer extends Client
{
    private long startyear;
    //todo - Annual turnover data
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "retailerlocationid", referencedColumnName = "retailerlocationid")
    @JsonIgnoreProperties("retailer")
    private RetailerLocation retailerlocation;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "retailercontactid", referencedColumnName = "retailercontactid")
    @JsonIgnoreProperties("retailer")
    private RetailerContact retailercontact;
    
    //todo link to transactions (with farmer)
    
    //todo link to account (with organization)


    public Retailer(String name, boolean isLead, long startyear, RetailerLocation retailerlocation, RetailerContact retailercontact) {
        super(name, isLead);
        this.startyear = startyear;
        this.retailerlocation = retailerlocation;
        this.retailercontact = retailercontact;
    }

    public Retailer() {
    }

    public long getStartyear() {
        return startyear;
    }

    public void setStartyear(long startyear) {
        this.startyear = startyear;
    }

    public RetailerLocation getRetailerlocation() {
        return retailerlocation;
    }

    public void setRetailerlocation(RetailerLocation retailerlocation) {
        this.retailerlocation = retailerlocation;
    }

    public RetailerContact getRetailercontact() {
        return retailercontact;
    }

    public void setRetailercontact(RetailerContact retailercontact) {
        this.retailercontact = retailercontact;
    }


}
