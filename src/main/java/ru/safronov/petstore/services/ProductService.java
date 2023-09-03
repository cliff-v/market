package ru.safronov.petstore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.safronov.petstore.models.Product;
import ru.safronov.petstore.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Optional<Boolean> remove(String id) {
        return productRepository.findById(id)
                .map((product) -> {
                    productRepository.delete(product);
                    return Boolean.TRUE;
                });
    }
}
