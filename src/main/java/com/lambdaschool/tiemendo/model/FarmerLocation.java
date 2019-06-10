package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "farmerlocations")
public class FarmerLocation extends Auditable
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

    public FarmerLocation() {
    }

    public FarmerLocation(String community, String landmark, Farmer farmer) {
        this.community = community;
        this.landmark = landmark;
        this.farmer = farmer;
    }

    public FarmerLocation(String address, String region, String district, String community, String landmark, Farmer farmer) {
        this.address = address;
        this.region = region;
        this.district = district;
        this.community = community;
        this.landmark = landmark;
        this.farmer = farmer;
    }

    public long getFarmerlocationid() {
        return farmerlocationid;
    }

    public void setFarmerlocationid(long farmerlocationid) {
        this.farmerlocationid = farmerlocationid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
}
