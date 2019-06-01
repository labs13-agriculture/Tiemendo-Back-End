package com.lambdaschool.tiemendo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Installment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    Amount paid
    private double paid;
//    Date paid
    private Date datePaid;
//    Mode of payment
//    MTN mobile money, Bank payments, Cash payment
    private String mode;
//    Officer who logged the farmer’s payment
    private String officer;

}
