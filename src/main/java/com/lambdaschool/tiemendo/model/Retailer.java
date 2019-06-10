package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "retailers")
public class Retailer extends Client
{
    private int startyear;

    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "retailerlocationid", referencedColumnName = "retailerlocationid")
    @JsonIgnoreProperties("retailer")
    private RetailerLocation retailerlocation;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "retailercontactid", referencedColumnName = "retailercontactid")
    @JsonIgnoreProperties("retailer")
    private RetailerContact retailercontact;
    
    //todo - Annual turnover data
    @OneToMany
    @JsonIgnoreProperties("retailer")
    private List<Turnover> goals = new ArrayList<>();


    public Retailer(String name, boolean isLead, int startyear, RetailerLocation retailerlocation, RetailerContact retailercontact) {
        super(name, isLead);
        this.startyear = startyear;
        this.retailerlocation = retailerlocation;
        this.retailercontact = retailercontact;
    }

    public Retailer() {
    }

    public int getStartyear() {
        //front-end needs number of years in operation, achieve this by subtracting start year from this year
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - startyear;
    }
    
    //need a way to get actual start year to compare when updating retailer
    public int getStartYearUnchanged(){
        return startyear;
    }

    public void setStartyear(int startyear) {
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
