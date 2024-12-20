package com.f5.delicious.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.Objects;

@Entity
public class Creator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private  Long id;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Creator(String name, String email, String phone, String address){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Creator() {}

    public Creator(long l, String alice, String mail) {
    }

    public Creator(long l, String existingCreator, String mail, String duplicatedPhone, String s) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
