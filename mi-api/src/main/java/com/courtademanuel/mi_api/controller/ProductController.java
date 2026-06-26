package com.courtademanuel.mi_api.controller;

import com.courtademanuel.mi_api.dto.ProductDTO;
import com.courtademanuel.mi_api.model.Product;
import com.courtademanuel.mi_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {
        Product p = productService.addProduct(productDTO.name(), productDTO.price());
        return ResponseEntity.status(201).body(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Optional<Product> p = productService.getById(id);
        return p.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Optional<Product> p = productService.updateProduct(id, productDTO.name(), productDTO.price());
        return p.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> p = productService.removeById(id);
        return p.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
