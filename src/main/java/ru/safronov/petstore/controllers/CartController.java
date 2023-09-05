package ru.safronov.petstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safronov.petstore.services.dto.CartItemRequestDto;
import ru.safronov.petstore.services.dto.CartDto;
import ru.safronov.petstore.services.CartService;

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

    @PatchMapping(path = "/add")
    public ResponseEntity<?> addProductToCart(@RequestBody CartItemRequestDto cartItemRequestDto) {
        cartService.addProductToCart(cartItemRequestDto);
        return ResponseEntity.ok().build();
    }
}
