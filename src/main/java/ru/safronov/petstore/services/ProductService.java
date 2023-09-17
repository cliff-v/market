package ru.safronov.petstore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.safronov.petstore.models.Product;
import ru.safronov.petstore.repositories.ProductRepository;
import ru.safronov.petstore.services.dto.ProductDto;
import ru.safronov.petstore.services.exeptions.ProductNotFoundException;
import ru.safronov.petstore.services.mapper.ProductMapper;
import soap.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    public static final Function<Product, soap.Product> functionEntityToSoap = pe -> {
        var p = new soap.Product();
        p.setId(pe.getId());
        p.setTitle(pe.getTitle());
        p.setPrice(pe.getPrice());
        p.getCategory().addAll(pe.getCategory().stream()
                .map(categoryEntity -> {
                    Category category = new Category();
                    category.setId(categoryEntity.getId());
                    category.setName(categoryEntity.getName());
                    category.setIdent(categoryEntity.getIdent());
                    return category;
                }).toList());
        return p;
    };

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

    public soap.Product getById(String id) {
        return productRepository
                .findById(id)
                .map(functionEntityToSoap)
                .orElseThrow(ProductNotFoundException::new);
    }

    public List<soap.Product> getAllProducts() {
        return productRepository
                .findAll().stream()
                .map(functionEntityToSoap)
                .collect(Collectors.toList());

    }
}
