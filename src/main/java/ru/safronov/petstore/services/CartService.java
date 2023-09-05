package ru.safronov.petstore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.safronov.petstore.models.Cart;
import ru.safronov.petstore.models.CartItem;
import ru.safronov.petstore.repositories.CartRepository;
import ru.safronov.petstore.repositories.ProductRepository;
import ru.safronov.petstore.services.dto.CartDto;
import ru.safronov.petstore.services.dto.CartItemRequestDto;
import ru.safronov.petstore.services.exeptions.CartNotFoundException;
import ru.safronov.petstore.services.exeptions.ProductNotFoundException;
import ru.safronov.petstore.services.mapper.CartMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    public Optional<CartDto> getCart(String cartId) {
        return cartRepository
                .findById(cartId)
                .map(cartMapper::toDto);
    }

    public void addProductToCart(CartItemRequestDto cartItemRequestDto) {
        Cart cart = cartRepository.findById(cartItemRequestDto.getCartId()).orElseThrow(CartNotFoundException::new);
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(cartItemRequestDto.getProductId()))
                .findFirst()
                .orElseGet(() -> {
                    CartItem ci = createCartItem(cartItemRequestDto,cart);
                    cart.getCartItems().add(ci);
                    return ci;
                });
        cartItem.setQuantity(cartItemRequestDto.getQuantity() != null
                                    ? cartItemRequestDto.getQuantity()
                                    : cartItem.getQuantity().add(BigDecimal.ONE));
        cart.setUpdatedDate(LocalDateTime.now());
        cartItem.setUpdatedDate(LocalDateTime.now());

        cartRepository.save(cart);
    }

    private CartItem createCartItem(CartItemRequestDto cartItemRequestDto, Cart cart) {
        return CartItem.builder()
                .cart(cart)
                .product(productRepository.findById(cartItemRequestDto.getProductId())
                        .orElseThrow(ProductNotFoundException::new))
                .quantity(BigDecimal.ZERO)
                .build();
    }
}
