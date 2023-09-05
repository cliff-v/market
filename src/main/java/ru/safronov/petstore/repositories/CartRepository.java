package ru.safronov.petstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.safronov.petstore.models.Cart;

public interface CartRepository extends JpaRepository<Cart, String> {}
