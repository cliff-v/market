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
    public ResponseEntity<CartDto> addProductToCart(@RequestBody CartItemRequestDto cartItemRequestDto) {
        return ResponseEntity.ok(cartService.addProductToCart(cartItemRequestDto));
    }

    @PatchMapping(path = "/subtract")
    public ResponseEntity<CartDto> subtractProductFromCart(@RequestBody CartItemRequestDto cartItemRequestDto) {
        return ResponseEntity.ok(cartService.subtractProduct(cartItemRequestDto));
    }

    @PatchMapping(path = "/remove_product")
    public ResponseEntity<CartDto> removeProductFromCart(@RequestBody CartItemRequestDto cartItemRequestDto) {
        return ResponseEntity.ok(cartService.removeProduct(cartItemRequestDto));
    }

    @DeleteMapping(path = "/{cartId}/clear")
    public ResponseEntity<CartDto> clearCart(@PathVariable String cartId) {
        return ResponseEntity.ok(cartService.clearCart(cartId));
    }
}
