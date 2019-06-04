package com.lambdaschool.tiemendo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="croptype")
public class CropType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String cropName;
    private boolean active;

    @OneToMany(mappedBy = "cropType")
    private List<Yield> yields = new ArrayList<>();

    public CropType() {
    }

    public CropType(String cropName, boolean active) {
        this.cropName = cropName;
        this.active = active;
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
