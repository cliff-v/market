package ru.safronov.petstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safronov.petstore.models.Product;
import ru.safronov.petstore.services.ProductService;
import ru.safronov.petstore.services.dto.ProductDto;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.create(product));
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId) {
        return productService.remove(productId).isPresent()
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }


}
