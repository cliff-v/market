package ru.safronov.petstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.safronov.petstore.models.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
