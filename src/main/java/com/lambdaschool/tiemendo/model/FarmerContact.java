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

    public FarmerContact(String name, String gender, String phone, String email, Farmer farmer) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.farmer = farmer;
    }

    public long getFarmercontactid() {
        return farmercontactid;
    }

    public void setFarmercontactid(long farmercontactid) {
        this.farmercontactid = farmercontactid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getEducationlevel() {
        return educationlevel;
    }

    public void setEducationlevel(String educationlevel) {
        this.educationlevel = educationlevel;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
}
