package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.models.auth.In;

import javax.persistence.*;

@Entity
@Table(name="yield")
public class Yield extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
//    Number of bags of crops (maize or sorghum) harvested
    private Integer numBags;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="crop_id")
    @JsonIgnoreProperties("yields")
    private CropType cropType;

//    Their goal for bags of crops to sell
    private Integer goal;
//    Farm size in acres
    private Integer farmAcres;
//    Year or season
    private String season;

    @ManyToOne
    @JoinColumn(name="farmer_id")
    @JsonIgnoreProperties("yieldHistory")
    private Farmer farmer;

    public Yield() {
    }

    public Yield(int numBags, CropType cropType, Integer goal, Integer farmAcres, String season, Farmer farmer) {
        this.numBags = numBags;
        this.cropType = cropType;
        this.goal = goal;
        this.farmAcres = farmAcres;
        this.season = season;
        this.farmer = farmer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getNumBags() {
        return numBags;
    }

    public void setNumBags(int numBags) {
        this.numBags = numBags;
    }

    public CropType getCropType() {
        return cropType;
    }

    public void setCropType(CropType cropType) {
        this.cropType = cropType;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public Integer getFarmAcres() {
        return farmAcres;
    }

    public void setFarmAcres(int farmAcres) {
        this.farmAcres = farmAcres;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
}
