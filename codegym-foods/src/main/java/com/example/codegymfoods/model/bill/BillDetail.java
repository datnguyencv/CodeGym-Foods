package com.example.codegymfoods.model.bill;

import com.example.codegymfoods.model.bill.Bill;
import com.example.codegymfoods.model.product.Product;

import javax.persistence.*;

@Entity
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantityBuy;
    @ManyToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "id")
    private Bill bill;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public BillDetail() {
    }

    public BillDetail(Integer id, Integer quantityBuy, Bill bill, Product product) {
        this.id = id;
        this.quantityBuy = quantityBuy;
        this.bill = bill;
        this.product = product;
    }

    public BillDetail(Integer quantityBuy, Bill bill, Product product) {
        this.quantityBuy = quantityBuy;
        this.bill = bill;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantityBuy() {
        return quantityBuy;
    }

    public void setQuantityBuy(Integer quantityBuy) {
        this.quantityBuy = quantityBuy;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
