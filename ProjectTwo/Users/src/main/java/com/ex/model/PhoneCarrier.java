package com.ex.model;

import javax.persistence.*;
import java.util.List;

/**
 * This class is used for enumerated list to provide validation on the
 * phone carrier types within database read/write methods for the User class
 */
@Entity
@Table(name="phonecarriers", schema = "\"that-team_schema\"")
public class PhoneCarrier {
//    TMobile,
//    Virgin,
//    Cingular,
//    Sprint,
//    Verizon,
//    GoogleFi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int phonecarrierid;

    private String phonecarrier;

    @OneToMany(mappedBy = "phonecarrierid", cascade=CascadeType.ALL)
    private List<User> user;


    public PhoneCarrier() {
        this.phonecarrierid = 1;
        this.phonecarrier = "AT&T";
    }
}
