package org.example.bai1.controller;

import org.example.bai1.entity.Product;
import org.example.bai1.model.responseModel.ProductResponse;
import org.example.bai1.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public List<ProductResponse> getProducts() {
        List<Product> ls = productService.findAll();
        List<ProductResponse> rs = ls.stream().map(
                p -> new ProductResponse(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getQty() >0,
                        p.getDescription(),
                        p.getCategory() != null ? p.getCategory().getName() : null
                )
        ).toList();
        return rs;
    }
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product p = productService.save(product);
        return ResponseEntity.ok().body(p);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product product) {
        Optional<Product> p = productService.findById(id);
        if(p.isPresent()) {
            product.setId(id);
            productService.save(product);
            return ResponseEntity.ok().body(product);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Optional<Product> p = productService.findById(id);
        if(p.isPresent()) {
            productService.delete(p.get());
            return ResponseEntity.ok("Product with ID " + id + " has been deleted successfully");
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        List<Product> products = productService.search(name, minPrice, maxPrice);
        return  ResponseEntity.ok(products);
    }
}
