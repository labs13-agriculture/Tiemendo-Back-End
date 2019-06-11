package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name= "client")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Client extends Auditable {
    // Unique ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private boolean isLead;
    private String type;

    // These are for payment schedules
    private double paymentAmount;
    private boolean isPayFrequencyDate;
    private int paymentFrequency;
    private LocalDate paymentStartDate;

    @ElementCollection
    @MapKeyColumn(name="due_date")
    @Column(name="paid_date")
    @CollectionTable(name="example_attributes", joinColumns=@JoinColumn(name="example_id"))
    private HashMap<LocalDate, LocalDate> paymentSchedule = new HashMap<>();

    // Transactions
    @OneToMany(mappedBy="client", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("client")
    private List<Transaction> transactions = new ArrayList<>();
    // Installments

    @OneToMany(mappedBy="client", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("client")
    private List<Installment> installments = new ArrayList<>();


    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public Client(String name, boolean isLead) {
        this.name = name;
        this.isLead = isLead;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLead() {
        return isLead;
    }

    public void setLead(boolean lead) {
        isLead = lead;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public int getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(int paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public LocalDate getPaymentStartDate() {
        return paymentStartDate;
    }

    public void setPaymentStartDate(LocalDate paymentStartDate) {
        this.paymentStartDate = paymentStartDate;
    }

    public HashMap<LocalDate, LocalDate> getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(HashMap<LocalDate, LocalDate> paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

}
