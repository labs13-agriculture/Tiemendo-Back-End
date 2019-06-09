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
public class Organization extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private boolean isLead;
    private String headquarters;
    private int beneficiaries;


    @OneToMany(mappedBy = "organization")
    @JsonIgnoreProperties("organization")
    private List<OrganizationLocation> organizationlocations = new ArrayList<>();
    
    @OneToMany(mappedBy = "organization")
    @Cascade(SAVE_UPDATE)
    @JsonIgnoreProperties("organization")
    private List<OrganizationContact> organizationcontacts = new ArrayList<>();

    public Organization() {
    }

    public Organization(String name, boolean isLead, String headquarters, int beneficiaries, List<OrganizationLocation> organizationlocations, List<OrganizationContact> organizationcontacts) {
        this.name = name;
        this.isLead = isLead;
        this.headquarters = headquarters;
        this.beneficiaries = beneficiaries;
        this.organizationlocations = organizationlocations;
        this.organizationcontacts = organizationcontacts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLead() {
        return isLead;
    }

    public void setLead(boolean lead) {
        isLead = lead;
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

    public List<OrganizationLocation> getOrganizationlocations() {
        return organizationlocations;
    }

    public void setOrganizationlocations(List<OrganizationLocation> organizationlocations) {
        this.organizationlocations = organizationlocations;
    }

    public List<OrganizationContact> getOrganizationcontacts() {
        return organizationcontacts;
    }

    public void setOrganizationcontacts(List<OrganizationContact> organizationcontacts) {
        this.organizationcontacts = organizationcontacts;
    }
}
