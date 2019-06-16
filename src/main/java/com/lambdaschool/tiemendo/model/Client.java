package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "client")
@Inheritance(strategy = InheritanceType.JOINED)
public class Client extends Auditable {
    // Unique ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private boolean isLead;
    private String type;
    private long startyear;

    // Location Information
    private String address;
    private String region;
    private String district;
    private String community;
    private String landmark;

    // Contact Information
    private String title;
    private String firstName;
    private String secondName;
    private String gender;
    private String nationality;
    private String dateofbirth;
    private String educationlevel;
    private String position;
    // todo passport picture
    private String phone;
    private String email;
    // todo getOwed - calculates the totalOwed based on transactions and installments

    // Transactions
    @OneToMany(mappedBy="client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Transaction> transactions = new ArrayList<>();
    // Installments

    @OneToMany(mappedBy="client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Installment> installments = new ArrayList<>();

    //TODO PaymentSchedule


    public Client() {
    }

    public Client(String name) {
        this.firstName = name;
    }

    public Client(String name, boolean isLead) {
        this.firstName = name;
        this.isLead = isLead;
    }

    public Client(boolean isLead, long startyear, String address, String landmark, String name, String phone, String email) {
        this.isLead = isLead;
        this.startyear = startyear;
        this.address = address;
        this.landmark = landmark;
        this.firstName = name;
        this.phone = phone;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isLead() {
        return isLead;
    }

    public void setLead(boolean lead) {
        isLead = lead;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getStartyear() {
        return startyear;
    }

    public void setStartyear(long startyear) {
        this.startyear = startyear;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Installment> installments) {
        this.installments = installments;
    }
}
