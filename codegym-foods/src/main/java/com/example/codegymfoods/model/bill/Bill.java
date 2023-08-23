package com.example.codegymfoods.model.bill;

import com.example.codegymfoods.model.customer.Customer;
import com.example.codegymfoods.model.employee.Employee;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "date")
    private String purchaseDate;
    private Boolean status;
    private Double totalPrice;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


    public Bill() {
    }

    public Bill(Integer id, String purchaseDate, Boolean status, Double totalPrice, Customer customer) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.totalPrice = totalPrice;
        this.customer = customer;
    }

    public Bill(String purchaseDate, Boolean status, Double totalPrice, Customer customer) {
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.totalPrice = totalPrice;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
