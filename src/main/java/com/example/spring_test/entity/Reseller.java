package com.example.spring_test.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reseller")
public class Reseller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "name")
    String name;

    @Column(name = "primaryContact")
    String primaryContact;

    @Column(name = "contactEmail")
    String contactEmail;

    @Column(name = "msisdn")
    String msisdn;

    @Column(name = "address")
    String address;

    @Column(name = "email")
    String email;

    @Column(name = "status")
    Integer status;

}
