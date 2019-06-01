package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizations")
public class Organization
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long organizationid;
    
    @OneToMany(mappedBy = "organization")
    @JsonIgnoreProperties("organization")
    private List<OrganizationLocation> organizationlocations = new ArrayList<>();
    
    private String headquarters;
    private int beneficiaries;
    private boolean islead;
    
    @OneToMany(mappedBy = "organization")
    @JsonIgnoreProperties("organization")
    private List<OrganizationContact> organizationcontacts = new ArrayList<>();
    
    public Organization()
    {
    }
    
    public Organization(List<OrganizationLocation> organizationlocations, String headquarters, int beneficiaries, boolean islead, List<OrganizationContact> organizationcontacts)
    {
        this.organizationlocations = organizationlocations;
        this.headquarters = headquarters;
        this.beneficiaries = beneficiaries;
        this.islead = islead;
        this.organizationcontacts = organizationcontacts;
    }
    
    public long getOrganizationid()
    {
        return organizationid;
    }
    
    public void setOrganizationid(long organizationid)
    {
        this.organizationid = organizationid;
    }
    
    public List<OrganizationLocation> getOrganizationlocations()
    {
        return organizationlocations;
    }
    
    public void setOrganizationlocations(List<OrganizationLocation> organizationlocations)
    {
        this.organizationlocations = organizationlocations;
    }
    
    public String getHeadquarters()
    {
        return headquarters;
    }
    
    public void setHeadquarters(String headquarters)
    {
        this.headquarters = headquarters;
    }
    
    public int getBeneficiaries()
    {
        return beneficiaries;
    }
    
    public void setBeneficiaries(int beneficiaries)
    {
        this.beneficiaries = beneficiaries;
    }
    
    public boolean isIslead()
    {
        return islead;
    }
    
    public void setIslead(boolean islead)
    {
        this.islead = islead;
    }
    
    public List<OrganizationContact> getOrganizationcontacts()
    {
        return organizationcontacts;
    }
    
    public void setOrganizationcontacts(List<OrganizationContact> organizationcontacts)
    {
        this.organizationcontacts = organizationcontacts;
    }
}
