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
    @Column(name = "phone_carrier_id")
    private int phoneCarrierID;

    @Column(name = "phone_carrier")
    private String phoneCarrier;

    @OneToMany(mappedBy = "phoneCarrierId", cascade=CascadeType.ALL)
    private List<User> user;


    public PhoneCarrier() {
        this.phoneCarrierID  = 1;
        this.phoneCarrier  = "AT&T";
    }

    public int getPhoneCarrierID() {
        return phoneCarrierID;
    }

    public void setPhoneCarrierID(int phoneCarrierID) {
        this.phoneCarrierID = phoneCarrierID;
    }

    public String getPhoneCarrier() {
        return phoneCarrier;
    }

    public void setPhoneCarrier(String phoneCarrier) {
        this.phoneCarrier = phoneCarrier;
    }
}
