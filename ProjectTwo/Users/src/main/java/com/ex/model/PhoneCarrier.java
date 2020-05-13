package com.ex.model;

import javax.persistence.*;
import java.util.List;

/**
 * This class is used for enumerated list to provide validation on the
 * phone carrier types within database read/write methods for the User class
 */
@Entity
@Table(name="\"PhoneCarriers\"", schema = "\"that-team_schema\"")
public class PhoneCarrier {
//    TMobile,
//    Virgin,
//    Cingular,
//    Sprint,
//    Verizon,
//    GoogleFi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int phoneCarrierID ;

    private String phoneCarrier ;

    @OneToMany(mappedBy = "phoneCarrierID", cascade=CascadeType.ALL)
    private List<User> user;


    public PhoneCarrier() {
        this.phoneCarrierID  = 1;
        this.phoneCarrier  = "AT&T";
    }
}
