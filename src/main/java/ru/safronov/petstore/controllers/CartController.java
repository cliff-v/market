package ru.safronov.petstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.safronov.petstore.services.dto.CartDto;
import ru.safronov.petstore.services.CartService;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @GetMapping(path = "/{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable String cartId) {
        return cartService.getCart(cartId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
