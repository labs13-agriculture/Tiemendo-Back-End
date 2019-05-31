package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "retailerlocations")
public class RetailerLocation
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long retailerlocationid;
    
    private String address;
    private String region;
    private String district;
    private String community;
    private String landmark;
    
    @OneToOne(mappedBy = "retailerlocation")
    @JsonIgnoreProperties("retailerlocation")
    private Retailer retailer;
    
    public RetailerLocation()
    {
    }
    
    public RetailerLocation(String address, String region, String district, String community, String landmark, Retailer retailer)
    {
        this.address = address;
        this.region = region;
        this.district = district;
        this.community = community;
        this.landmark = landmark;
        this.retailer = retailer;
    }
    
    public long getRetailerlocationid()
    {
        return retailerlocationid;
    }
    
    public void setRetailerlocationid(long retailerlocationid)
    {
        this.retailerlocationid = retailerlocationid;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getRegion()
    {
        return region;
    }
    
    public void setRegion(String region)
    {
        this.region = region;
    }
    
    public String getDistrict()
    {
        return district;
    }
    
    public void setDistrict(String district)
    {
        this.district = district;
    }
    
    public String getCommunity()
    {
        return community;
    }
    
    public void setCommunity(String community)
    {
        this.community = community;
    }
    
    public String getLandmark()
    {
        return landmark;
    }
    
    public void setLandmark(String landmark)
    {
        this.landmark = landmark;
    }
    
    public Retailer getRetailer()
    {
        return retailer;
    }
    
    public void setRetailer(Retailer retailer)
    {
        this.retailer = retailer;
    }
}
