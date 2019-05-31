package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "retailers")
public class Retailer extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long retailerid;
    
    private String retailername;
    private long startyear;
    private boolean islead;
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
    
    
    public Retailer()
    {
    }
    
    public Retailer(String retailerName, long startyear, boolean islead, RetailerLocation retailerlocation, RetailerContact retailercontact)
    {
        this.retailername = retailerName;
        this.startyear = startyear;
        this.islead = islead;
        this.retailerlocation = retailerlocation;
        this.retailercontact = retailercontact;
    }
    
    public long getRetailerid()
    {
        return retailerid;
    }
    
    public void setRetailerid(long retailerid)
    {
        this.retailerid = retailerid;
    }
    
    public String getRetailername()
    {
        return retailername;
    }
    
    public void setRetailername(String retailerName)
    {
        this.retailername = retailerName;
    }
    
    public long getStartyear()
    {
        return startyear;
    }
    
    public void setStartyear(long startyear)
    {
        this.startyear = startyear;
    }
    
    public boolean isIslead()
    {
        return islead;
    }
    
    public void setIslead(boolean islead)
    {
        this.islead = islead;
    }
    
    public RetailerLocation getRetailerlocation()
    {
        return retailerlocation;
    }
    
    public void setRetailerlocation(RetailerLocation retailerlocation)
    {
        this.retailerlocation = retailerlocation;
    }
    
    public RetailerContact getRetailercontact()
    {
        return retailercontact;
    }
    
    public void setRetailercontact(RetailerContact retailercontact)
    {
        this.retailercontact = retailercontact;
    }
}
