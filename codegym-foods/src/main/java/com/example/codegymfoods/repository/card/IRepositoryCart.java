package com.example.codegymfoods.repository.card;

import com.example.codegymfoods.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryCart extends JpaRepository<Product,Integer> {

}
