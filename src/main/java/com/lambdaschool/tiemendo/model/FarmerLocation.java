package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "farmerlocations")
public class FarmerLocation
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long farmerlocationid;

    private String address;
    private String region;
    private String district;
    private String community;
    private String landmark;

    @OneToOne(mappedBy = "farmerlocation")
    @JsonIgnoreProperties("farmerlocation")
    private Farmer farmer;

}
