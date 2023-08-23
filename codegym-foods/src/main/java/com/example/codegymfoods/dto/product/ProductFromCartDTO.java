package com.example.codegymfoods.dto.product;

public class ProductFromCartDTO {
    private int id;
    private String picture;
    private String nameProductFromCartDTO;
    private double priceProductFromCartDTO;
    private int quantityProductFromCartDTO;
    private double totalPrice;

    public ProductFromCartDTO(int id, String picture, String nameProductFromCartDTO, double priceProductFromCartDTO, int quantityProductFromCartDTO, double totalPrice) {
        this.id = id;
        this.picture = picture;
        this.nameProductFromCartDTO = nameProductFromCartDTO;
        this.priceProductFromCartDTO = priceProductFromCartDTO;
        this.quantityProductFromCartDTO = quantityProductFromCartDTO;
        this.totalPrice = totalPrice;
    }

    public ProductFromCartDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNameProductFromCartDTO() {
        return nameProductFromCartDTO;
    }

    public void setNameProductFromCartDTO(String nameProductFromCartDTO) {
        this.nameProductFromCartDTO = nameProductFromCartDTO;
    }

    public double getPriceProductFromCartDTO() {
        return priceProductFromCartDTO;
    }

    public void setPriceProductFromCartDTO(double priceProductFromCartDTO) {
        this.priceProductFromCartDTO = priceProductFromCartDTO;
    }

    public int getQuantityProductFromCartDTO() {
        return quantityProductFromCartDTO;
    }

    public void setQuantityProductFromCartDTO(int quantityProductFromCartDTO) {
        this.quantityProductFromCartDTO = quantityProductFromCartDTO;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
