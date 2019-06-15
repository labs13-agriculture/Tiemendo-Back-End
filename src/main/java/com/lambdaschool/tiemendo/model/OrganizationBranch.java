package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "organizationcontacts")
public class OrganizationBranch extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long branch_id;

    // Organization Branch Contact Info
    private String name;
    private String phone;
    private String email;
    private String position;

    // Organization Branch Location Info
    private String address;
    private String district;
    private String region;
    private String landmark;
    
    @ManyToOne
    @JoinColumn(name = "organizationcontacts")
    @JsonIgnore
    private Organization organization;
    
    public OrganizationBranch()
    {
    }
    
    public OrganizationBranch(String name, String phone, String email, String position, Organization organization)
    {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
        this.organization = organization;
    }

    public OrganizationBranch(String name, String phone, String email, String position, String address, String district, String region, String landmark, Organization organization) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
        this.address = address;
        this.district = district;
        this.region = region;
        this.landmark = landmark;
        this.organization = organization;
    }

    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getPosition()
    {
        return position;
    }
    
    public void setPosition(String position)
    {
        this.position = position;
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
