package com.courtademanuel.mi_api.service;

import com.courtademanuel.mi_api.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ArrayList<Product> products = new ArrayList<>();
    private long counterId = 1;

    public List<Product> getAll() {
        return this.products;
    }

    public Product addProduct(String name, Double price) {
        Product p = new Product(counterId++, name, price);
        products.add(p);
        return p;
    }

    public Optional<Product> getById(Long id) {
        return this.products.stream()
                .filter(p -> p.id().equals(id))
                .findFirst();
    }

    public Optional<Product> removeById(Long id) {
        Optional<Product> p = this.getById(id);
        p.ifPresent(product -> this.products.removeIf(product2 -> product2.id().equals(id)));
        return p;
    }



    public Optional<Product> updateProduct(Long id, String name, Double price) {
        for (int i = 0; i < this.products.size(); i++) {
            if (products.get(i).id().equals(id)) {
                Product newProduct = new Product(id, name, price);
                this.products.set(i, newProduct);
                return Optional.of(newProduct);
            }
        }
        return Optional.empty();
    }
}
