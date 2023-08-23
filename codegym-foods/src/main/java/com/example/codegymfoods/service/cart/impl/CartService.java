package com.example.codegymfoods.service.cart.impl;

import com.example.codegymfoods.dto.cart.CartDTO;
import com.example.codegymfoods.dto.product.ProductFromCartDTO;
import com.example.codegymfoods.model.product.Product;
import com.example.codegymfoods.service.cart.ICartService;
import com.example.codegymfoods.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartService implements ICartService {
    @Autowired
    private IProductService productService;

    @Override
    public double totalBill(List<ProductFromCartDTO> productFromCartDTOList) {
        long totalBill =0;
        for (int i = 0; i < productFromCartDTOList.size(); i++) {
            totalBill+= productFromCartDTOList.get(i).getTotalPrice();
        }
        return totalBill;
    }

    @Override
    public boolean checkQuantity(int id, int quantity) {
        Product product = productService.getById(id);
        if (product.getQuantity() >= quantity){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void addToCart(Product product, CartDTO cartDTO) {
        boolean flag = false;
        for (Map.Entry<Integer, Integer> entry : cartDTO.getSelectedProducts().entrySet()) {
            if (entry.getKey().equals(product.getId())) {
                entry.setValue(entry.getValue() + 1);
                flag = true;
            }
        }
        if (!flag) {
            cartDTO.getSelectedProducts().put(product.getId(), 1);
        }
    }

    @Override
    public void changeQuantity(int id, int quantity, CartDTO cartDTO) {
        for (Map.Entry<Integer, Integer> entry : cartDTO.getSelectedProducts().entrySet()) {
            if (entry.getKey() == id) {
                if (quantity <= 0) {
                    Map<Integer, Integer> cartMap = cartDTO.getSelectedProducts();
                    cartMap.remove(id);
                    return;
                } else {
                    entry.setValue(quantity);
                }
            }
        }
    }
}
