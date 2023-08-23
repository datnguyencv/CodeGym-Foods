package com.example.codegymfoods.service.cart;

import com.example.codegymfoods.dto.cart.CartDTO;
import com.example.codegymfoods.dto.product.ProductFromCartDTO;
import com.example.codegymfoods.model.product.Product;

import java.util.List;

public interface ICartService {

    void addToCart(Product product, CartDTO cartDTO);
    void changeQuantity(int id, int quantity, CartDTO cartDTO);

    double totalBill(List<ProductFromCartDTO> productFromCartDTOList);

    boolean checkQuantity(int id, int quantity);
}
