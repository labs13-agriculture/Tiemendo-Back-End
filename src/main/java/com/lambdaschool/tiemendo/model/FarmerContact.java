package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "farmercontacts")
public class FarmerContact
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long farmercontactid;

    private String title;
    private String name;
    private String gender;
    private String nationality;
    private String dateofbirth;
    private String educationlevel;
    private String position;
    // todo passport picture
    private String phone;
    private String email;

    @OneToOne(mappedBy = "farmercontact")
    @JsonIgnoreProperties("farmercontact")
    private Farmer farmer;

    public FarmerContact()
    {
    }

}
