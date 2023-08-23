package com.example.codegymfoods.service.product;

import com.example.codegymfoods.dto.product.ProductFromCartDTO;
import com.example.codegymfoods.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IProductService {
    List<Product> getALl();

    void delete(int id);

    void creat(Product product);

    Product getById(int id);

    void updateById(Product product);

    Page<Product> getBlogPage(Pageable pageableProduct);


    List<Product> getAllByType(int id);

    List<Product> getProductsById(Set<Integer> productsIds);


    Product getProductByName(String nameProductFromCartDTO);

    List<Product> findByName(String nameProduct);

    void reduceQauntity(List<ProductFromCartDTO> productFromCartDTOList);

    Page<Product> getProductPageName(String name,Pageable pageableProduct);
    Page<Product> getProductByIdName(String search, Integer typeId,Pageable pageableProduct);
}
