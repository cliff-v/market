package ru.safronov.petstore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.safronov.petstore.repositories.CartRepository;
import ru.safronov.petstore.services.dto.CartDto;
import ru.safronov.petstore.services.mapper.CartMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public Optional<CartDto> getCart(String cartId) {
        return cartRepository
                .findById(cartId)
                .map(cartMapper::toDto);
    }
}
