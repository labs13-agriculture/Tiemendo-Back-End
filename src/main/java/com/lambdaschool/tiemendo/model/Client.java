package com.lambdaschool.tiemendo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    @OneToMany(mappedBy="client", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("client")
    private List<PaymentSchedule> paymentSchedule = new ArrayList<>();

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
            if (t.getType().equalsIgnoreCase("credit")) {
                totalOwed += t.getTotal();
            }
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

    public List<PaymentSchedule> getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(List<PaymentSchedule> paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public ArrayList<PaymentSchedule> generatePaySchedule(int customOwed) {
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
        var owed = customOwed > 0 ? customOwed : getAmountOwed();
        System.out.println("Amount owed at start of payment calculations: " + getAmountOwed());

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
        System.out.println("Amount owed at end of payment calculations: " + getAmountOwed());
        System.out.println("There should be no change");

        // remove any dates that have not passed from schedule to add new schedule
        /*
        *  Dates that are left will stay null to show that they were past due
        * */

        var updateSchedule = new ArrayList<PaymentSchedule>();

        for (PaymentSchedule p: paymentSchedule) {
            if (p.getDateDue().isBefore(LocalDate.now())) {
                updateSchedule.add(p);
            }
        }

        // add in new dates
        for (LocalDate d: newDates) {
            updateSchedule.add(new PaymentSchedule(this, d, null));
        }

        this.setPaymentSchedule(updateSchedule);
        return new ArrayList<>(paymentSchedule);
    }
}
