package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "retailercontacts")
public class RetailerContact extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long retailercontactid;
    
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
    
    @OneToOne(mappedBy = "retailercontact")
    @JsonIgnoreProperties("retailercontact")
    private Retailer retailer;
    
    public RetailerContact()
    {
    }
    
    public RetailerContact(String title, String name, String gender, String nationality, String dateofbirth, String educationlevel, String position, String phone, String email, Retailer retailer)
    {
        this.title = title;
        this.name = name;
        this.gender = gender;
        this.nationality = nationality;
        this.dateofbirth = dateofbirth;
        this.educationlevel = educationlevel;
        this.position = position;
        this.phone = phone;
        this.email = email;
        this.retailer = retailer;
    }
    
    public long getRetailercontactid()
    {
        return retailercontactid;
    }
    
    public void setRetailercontactid(long retailercontactid)
    {
        this.retailercontactid = retailercontactid;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getGender()
    {
        return gender;
    }
    
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    
    public String getNationality()
    {
        return nationality;
    }
    
    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }
    
    public String getDateofbirth()
    {
        return dateofbirth;
    }
    
    public void setDateofbirth(String dateofbirth)
    {
        this.dateofbirth = dateofbirth;
    }
    
    public String getEducationlevel()
    {
        return educationlevel;
    }
    
    public void setEducationlevel(String educationlevel)
    {
        this.educationlevel = educationlevel;
    }
    
    public String getPosition()
    {
        return position;
    }
    
    public void setPosition(String position)
    {
        this.position = position;
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
    
    public Retailer getRetailer()
    {
        return retailer;
    }
    
    public void setRetailer(Retailer retailer)
    {
        this.retailer = retailer;
    }
}
