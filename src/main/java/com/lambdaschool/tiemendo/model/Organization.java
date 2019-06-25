package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private boolean lead;
    private String headquarters;
    private int beneficiaries;
    // Todo: Organization Logo

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<OrganizationBranch> branches = new ArrayList<>();

    public Organization() {
    }

    public Organization(String name, boolean lead, String headquarters, int beneficiaries) {
        this.name = name;
        this.lead = lead;
        this.headquarters = headquarters;
        this.beneficiaries = beneficiaries;
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
        return lead;
    }

    public void setLead(boolean lead) {
        lead = lead;
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

    public List<OrganizationBranch> getBranches() {
        return branches;
    }

    public void setBranches(List<OrganizationBranch> branches) {
        this.branches = branches;
    }
}
