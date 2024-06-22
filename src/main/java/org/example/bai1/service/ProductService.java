package org.example.bai1.service;

import org.example.bai1.entity.Product;
import org.example.bai1.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    public List<Product> findAll() {
        try {
            return productRepository.findAll();
        }catch (Exception e) {
            throw new RuntimeException("Failed to fetch all products: " + e.getMessage());
        }
    }
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
    public Product save(Product product) {
        return productRepository.save(product);
    }
    public void delete(Product product) {
        productRepository.delete(product);
    }
    public Optional<Product> updateProduct(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productOptional.get().setName(product.getName());
            productOptional.get().setPrice(product.getPrice());
            productOptional.get().setDescription(product.getDescription());
            productOptional.get().setQty(product.getQty());
            Product updatedProduct = productRepository.save(productOptional.get());
            return Optional.of(updatedProduct);
        }else{
            return Optional.empty();
        }
    }
    public List<Product> search(String name,Double minPrice,Double maxPrice) {
        return productRepository.search(name, minPrice, maxPrice);
    }
}
