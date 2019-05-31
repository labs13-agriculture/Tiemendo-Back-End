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
    @JoinColumn(name = "organizationid")
    @JsonIgnoreProperties("organizationlocations")
    private Organization organization;
}
