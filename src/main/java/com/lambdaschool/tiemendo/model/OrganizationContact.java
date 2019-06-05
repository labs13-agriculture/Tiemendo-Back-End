package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

import static org.hibernate.annotations.CascadeType.DELETE;
import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

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
            //(cascade = CascadeType.ALL)
    @JoinColumn(name = "organizationcontacts")
    @JsonIgnoreProperties("organizationcontacts")

    private Organization organization;
    
    public OrganizationContact()
    {
    }
    
    public OrganizationContact(String name, String phone, String email, String position, Organization organization)
    {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
        this.organization = organization;
    }
    
    public long getOrganizationcontactid()
    {
        return organizationcontactid;
    }
    
    public void setOrganizationcontactid(long organizationcontactid)
    {
        this.organizationcontactid = organizationcontactid;
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
