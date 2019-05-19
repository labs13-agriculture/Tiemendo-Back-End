package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long clientid;

    @Column//(nullable = false)
    private String firstname;
    @Column//(nullable = false)
    private String lastname;
    @Column//(nullable = false)
    private String village;
    @Column//(nullable = false)
    private String loanamount;
    @Column//(nullable = false)
    private String lid; // LOAN INITIATION DATE
    @Column//(nullable = false)
    private String ldd; // LOAN DUE DATE
    @Column//(nullable = false)
    private int maizeinventory;
    @Column//(nullable = false)
    private int maizegoal;

    @ManyToOne
    @Cascade({CascadeType.MERGE, CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "staffid")
    @JsonIgnore
    private Staff staff;


    public Client()
    {
    }


    public Client(String firstname, String lastname, String village, String loanamount, String lid, String ldd, int maizeinventory, int maizegoal, Staff staff)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.village = village;
        this.loanamount = loanamount;
        this.lid = lid;
        this.ldd = ldd;
        this.maizeinventory = maizeinventory;
        this.maizegoal = maizegoal;
        this.staff = staff;
    }

    public long getClientid()
    {
        return clientid;
    }

    public void setClientid(long clientid)
    {
        this.clientid = clientid;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getVillage()
    {
        return village;
    }

    public void setVillage(String village)
    {
        this.village = village;
    }

    public String getLoanamount()
    {
        return loanamount;
    }

    public void setLoanamount(String loanamount)
    {
        this.loanamount = loanamount;
    }

    public String getLid()
    {
        return lid;
    }

    public void setLid(String lid)
    {
        this.lid = lid;
    }

    public String getLdd()
    {
        return ldd;
    }

    public void setLdd(String ldd)
    {
        this.ldd = ldd;
    }

    public int getMaizeinventory()
    {
        return maizeinventory;
    }

    public void setMaizeinventory(int maizeinventory)
    {
        this.maizeinventory = maizeinventory;
    }

    public int getMaizegoal()
    {
        return maizegoal;
    }

    public void setMaizegoal(int maizegoal)
    {
        this.maizegoal = maizegoal;
    }

    public Staff getStaff()
    {
        return staff;
    }

    public void setStaff(Staff staff)
    {
        this.staff = staff;
    }
}
