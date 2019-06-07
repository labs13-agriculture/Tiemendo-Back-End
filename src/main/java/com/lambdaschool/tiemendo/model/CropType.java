package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="croptype")
public class CropType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String cropName;
    private boolean active;


    @OneToMany(mappedBy = "cropType")
    @JsonIgnoreProperties("cropType")
    private List<Yield> yields = new ArrayList<>();

    public CropType() {
    }

    public CropType(String cropName) {
        this.cropName = cropName;
        this.active = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Yield> getYields() {
        return yields;
    }

    public void setYields(List<Yield> yields) {
        this.yields = yields;
    }
}
