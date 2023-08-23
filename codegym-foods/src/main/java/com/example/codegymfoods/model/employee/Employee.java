package com.example.codegymfoods.model.employee;

import com.example.codegymfoods.model.login.AppUser;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "Varchar(40)")
    private String name;
    @Column(columnDefinition = "Varchar(40)")
    private String address;
    @Column(columnDefinition = "Varchar(15)")
    private String phoneNumber;
    @Column(columnDefinition = "Varchar(40)")
    private String email;
    @Column(columnDefinition = "date")
    private String dateOfBirth;
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private Position position;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "app_user_id", referencedColumnName = "user_id", nullable = false)
    private AppUser appUser;

    public Employee() {
    }

    public Employee(Integer id, String name, String address, String phoneNumber, String email, String dateOfBirth, String avatar, Position position, AppUser appUser) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.avatar = avatar;
        this.position = position;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
