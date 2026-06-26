package com.courtademanuel.mi_api.service;

import com.courtademanuel.mi_api.model.Product;
import com.courtademanuel.mi_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product addProduct(String name, Double price) {
        Product p = new Product(name, price);
        return productRepository.save(p);
    }

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> removeById(Long id) {
        Optional<Product> p = productRepository.findById(id);
        p.ifPresent(product -> productRepository.deleteById(id));
        return p;
    }



    public Optional<Product> updateProduct(Long id, String name, Double price) {
        Optional<Product> p = productRepository.findById(id);
        if (p.isPresent()) {
            Product product = p.get();
            product.setName(name);
            product.setPrice(price);
            return Optional.of(productRepository.save(product));
        }
        return Optional.empty();
    }
}
