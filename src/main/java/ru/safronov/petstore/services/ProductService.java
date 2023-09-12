package ru.safronov.petstore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.safronov.petstore.models.Product;
import ru.safronov.petstore.repositories.ProductRepository;
import ru.safronov.petstore.services.dto.ProductDto;
import ru.safronov.petstore.services.mapper.ProductMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(productMapper::toDto).toList();
    }

    @Transactional
    public ProductDto create(ProductDto productDto) {
        Product product = productRepository.save(productMapper.toEntity(productDto));

        return productMapper.toDto(product);
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
