package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;
import static org.hibernate.annotations.CascadeType.DELETE;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "organizations")
public class Organization extends Client
{
    @OneToMany(mappedBy = "organization")
            //, cascade = CascadeType.ALL)

    @JsonIgnoreProperties("organization")
    private List<OrganizationLocation> organizationlocations = new ArrayList<>();
    
    private String headquarters;
    private int beneficiaries;
    
    @OneToMany(mappedBy = "organization")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JsonIgnoreProperties("organization")
    private List<OrganizationContact> organizationcontacts = new ArrayList<>();

    public Organization() {
    }

    public Organization( String name, boolean isLead, List<OrganizationLocation> organizationlocations, String headquarters, int beneficiaries, List<OrganizationContact> organizationcontacts) {
        super("", false);
        this.organizationlocations = organizationlocations;
        this.headquarters = headquarters;
        this.beneficiaries = beneficiaries;
        this.organizationcontacts = organizationcontacts;
    }

    public List<OrganizationLocation> getOrganizationlocations() {
        return organizationlocations;
    }

    public void setOrganizationlocations(List<OrganizationLocation> organizationlocations) {
        this.organizationlocations = organizationlocations;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public int getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(int beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    public List<OrganizationContact> getOrganizationcontacts() {
        return organizationcontacts;
    }

    public void setOrganizationcontacts(List<OrganizationContact> organizationcontacts) {
        this.organizationcontacts = organizationcontacts;
    }


}
