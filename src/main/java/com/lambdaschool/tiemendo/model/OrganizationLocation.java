package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "organizationlocations")
public class OrganizationLocation
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long organizationlocationid;
    
    private String address;
    private String district;
    private String region;
    private String landmark;
    
    @ManyToOne(fetch = FetchType.LAZY)
    //, cascade = CascadeType.ALL)
    @JoinColumn(name = "organizationid")
    @JsonIgnoreProperties("organizationlocations")
    private Organization organization;
    
    public OrganizationLocation()
    {
    }
    
    public OrganizationLocation(String address, String district, String region, String landmark, Organization organization)
    {
        this.address = address;
        this.district = district;
        this.region = region;
        this.landmark = landmark;
        this.organization = organization;
    }
    
    public long getOrganizationlocationid()
    {
        return organizationlocationid;
    }
    
    public void setOrganizationlocationid(long organizationlocationid)
    {
        this.organizationlocationid = organizationlocationid;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getDistrict()
    {
        return district;
    }
    
    public void setDistrict(String district)
    {
        this.district = district;
    }
    
    public String getRegion()
    {
        return region;
    }
    
    public void setRegion(String region)
    {
        this.region = region;
    }
    
    public String getLandmark()
    {
        return landmark;
    }
    
    public void setLandmark(String landmark)
    {
        this.landmark = landmark;
    }
    
    public Organization getOrganization()
    {
        return organization;
    }
    
    public void setOrganization(Organization organization)
    {
        this.organization = organization;
    }
}
