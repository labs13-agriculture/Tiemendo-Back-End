package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long staffid;

    private String staffname;

    @OneToMany(mappedBy = "staff")
    @Cascade({CascadeType.MERGE, CascadeType.SAVE_UPDATE})
    @JsonIgnore
    private List<Client> clients = new ArrayList<>();

    public Staff()
    {
    }

    public Staff(String staffname, List<Client> clients)
    {
        this.staffname = staffname;
        this.clients = clients;
    }

    public long getStaffid()
    {
        return staffid;
    }

    public void setStaffid(long staffid)
    {
        this.staffid = staffid;
    }

    public String getStaffname()
    {
        return staffname;
    }

    public void setStaffname(String staffname)
    {
        this.staffname = staffname;
    }

    public List<Client> getClients()
    {
        return clients;
    }

    public void setClients(List<Client> clients)
    {
        this.clients = clients;
    }
}
