package com.example.codegymfoods.service.product.impl;

import com.example.codegymfoods.model.product.ProductType;
import com.example.codegymfoods.repository.product.IProductTypeRepository;
import com.example.codegymfoods.service.product.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService implements IProductTypeService {
    @Autowired
    IProductTypeRepository productTypeRepository;

    @Override
    public List<ProductType> getAll() {
        return productTypeRepository.findAll();
    }
}
