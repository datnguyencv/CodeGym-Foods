package com.example.codegymfoods.model.customer;

import com.example.codegymfoods.model.login.AppUser;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "Varchar(40)")
    private String name;
    @Column(columnDefinition = "date")
    private String dateOfBirth;
    @Column(columnDefinition = "Varchar(40)")
    private String address;
    @Column(columnDefinition = "Varchar(15)")
    private String phoneNumber;
    @Column(columnDefinition = "Varchar(40)")
    private String email;
    private String avatar;

    @OneToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "app_user_id", referencedColumnName = "user_id", nullable = false)
    private AppUser appUser;

    public Customer() {
    }

    public Customer(Integer id, String name, String dateOfBirth, String address, String phoneNumber, String email, String avatar, AppUser appUser) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.avatar = avatar;
        this.appUser = appUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
