package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "organizationcontacts")
public class OrganizationContact
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long organizationcontactid;
    
    private String name;
    private String phone;
    private String email;
    private String position;
    
    @ManyToOne
    @JoinColumn(name = "organizationcontacts")
    @JsonIgnoreProperties("organizationcontacts")
    private Organization organization;
    
}
