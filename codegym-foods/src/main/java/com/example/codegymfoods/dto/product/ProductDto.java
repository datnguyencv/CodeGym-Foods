package com.example.codegymfoods.dto.product;

import com.example.codegymfoods.model.product.ProductType;
import org.aspectj.bridge.Message;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

public class ProductDto {
    private Integer id;
    @Size(max = 40,min = 1,message = "Ít hơn 40 kí tự")
    @NotBlank(message = "Không được để trống")
    private String name;
    @Size(max = 255,min = 1,message = "Ít hơn 255 kí tự")
    @NotBlank(message = "Không được để trống")
    private String description;
    @DecimalMin("1.0")
    @NumberFormat()
    private Double price;
    @Min(value = 0,message = "Không được để trống")
    private int quantity;
    @NotBlank (message = "Không được để trống")
    private String picture;
    @NotBlank (message = "Không được để trống")
    private String dateExpiration;
    private ProductType productType;

    public ProductDto() {
    }

    public ProductDto(Integer id, String name, String description, Double price, int quantity, String picture, String dateExpiration, ProductType productType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.picture = picture;
        this.dateExpiration = dateExpiration;
        this.productType = productType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
