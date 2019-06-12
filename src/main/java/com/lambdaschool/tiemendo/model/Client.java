package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name= "client")
@Inheritance(strategy = InheritanceType.JOINED)
public class Client extends Auditable {
    // Unique ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private boolean isLead;
    private String type;

    // These are for payment schedules
    private double paymentAmount;
    private int paymentFrequency;
    private String frequencyUnit; //["DAYS", "WEEKS", "MONTHS"]
    private LocalDate paymentStartDate;

    @ElementCollection
    @MapKeyColumn(name="due_date")
    @Column(name="paid_date")
    @CollectionTable(name="payment_schedule", joinColumns=@JoinColumn(name="client_id"))
    private Map<LocalDate, LocalDate> paymentSchedule = new HashMap<>();

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

    public double getAmountOwed() {
        var totalOwed = 0.0;
        for (Transaction t: transactions) {
            totalOwed += t.getTotal();
        }

        for(Installment i: installments) {
            totalOwed -= i.getAmountPaid();
        }

        return totalOwed;
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

    public String getFrequencyUnit() {
        return frequencyUnit;
    }

    public void setFrequencyUnit(String frequencyUnit) {
        this.frequencyUnit = frequencyUnit;
    }

    public Map<LocalDate, LocalDate> getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(Map<LocalDate, LocalDate> paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public Map<LocalDate, LocalDate> generatePaySchedule() {
        /*
        * This method uses the following fields
        *
        * private double paymentAmount;
        * private int paymentFrequency;
        * private String frequencyUnit; //["DAYS", "WEEKS", "MONTHS"]
        * private LocalDate paymentStartDate;
        *
        * To create a payment schedule.
        *
        * */

        // Starting variables
        var newDates = new HashSet<LocalDate>();
        var currentDate = paymentStartDate;
        var owed = getAmountOwed();

        // Generate the list of new days
        while (owed > 0) {
            switch (frequencyUnit.toUpperCase()) {
                case "DAYS":
                    currentDate = currentDate.plusDays(paymentFrequency);
                    break;
                case "WEEKS":
                    currentDate = currentDate.plusWeeks(paymentFrequency);
                    break;
                case "MONTHS":
                    currentDate = currentDate.plusMonths(paymentFrequency);
                    break;
                default:
                    throw new IllegalArgumentException("Could not generate a schedule with frequency unit: " + frequencyUnit +
                            "Please try \"DAYS\", \"WEEKS\", or \"MONTHS\".");
            }

            owed -= paymentAmount;
            newDates.add(currentDate);
        }

        // remove any dates that have not passed from schedule to add new schedule
        /*
        *  Dates that are left will stay null to show that they were past due
        * */
        var keys = paymentSchedule.keySet();
        for (LocalDate d: keys) {
            if (d.isAfter(LocalDate.now())) {
                keys.remove(d);
            }
        }

        // add in new dates
        for (LocalDate d: newDates) {
            paymentSchedule.put(d, null);
        }

        return paymentSchedule;
    }
}
