package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "farmers")
public class Farmer extends Client
{
   //dustinadded for yield saving
    @OneToMany(mappedBy="farmer",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("farmer")
    private List<Yield> yieldHistory = new ArrayList<>();

    //TODO payment schedule
    //TODO Yields

    public Farmer() {
    }

    public List<Yield> getYieldHistory() {
        return yieldHistory;
    }

    public void setYieldHistory(List<Yield> yieldHistory) {
        this.yieldHistory = yieldHistory;
    }
}
