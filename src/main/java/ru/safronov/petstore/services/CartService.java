package ru.safronov.petstore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.safronov.petstore.models.Cart;
import ru.safronov.petstore.models.CartItem;
import ru.safronov.petstore.repositories.CartRepository;
import ru.safronov.petstore.repositories.ProductRepository;
import ru.safronov.petstore.services.dto.CartDto;
import ru.safronov.petstore.services.dto.CartItemRequestDto;
import ru.safronov.petstore.services.exeptions.ArgumentNotValidException;
import ru.safronov.petstore.services.exeptions.CartNotFoundException;
import ru.safronov.petstore.services.exeptions.ProductNotFoundException;
import ru.safronov.petstore.services.mapper.CartMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Transactional
    public CartDto addProductToCart(CartItemRequestDto cartItemRequestDto) {
        Cart cart = cartRepository.findById(cartItemRequestDto.getCartId()).orElseThrow(CartNotFoundException::new);
        CartItem cartItem = getCartItemByProduct(cartItemRequestDto, cart);

        BigDecimal dtoQuantity = cartItemRequestDto.getQuantity();
        BigDecimal quantity;
        if (dtoQuantity == null) {
           quantity = cartItem.getQuantity().add(BigDecimal.ONE);
        } else if(dtoQuantity.compareTo(BigDecimal.ZERO) > 0) {
            quantity = dtoQuantity;
        } else {
            throw new ArgumentNotValidException();
        }

        cartItem.setQuantity(quantity);

        cart.setUpdatedDate(LocalDateTime.now());
        cartItem.setUpdatedDate(LocalDateTime.now());

        return cartMapper.toDto(cartRepository.save(cart));

    }

    @Transactional
    public CartDto removeProduct(CartItemRequestDto cartItemRequestDto) {
        Cart cart = cartRepository.findById(cartItemRequestDto.getCartId()).orElseThrow(CartNotFoundException::new);
        CartItem cartItem = getCartItemByProduct(cartItemRequestDto, cart);

        cart.getCartItems().remove(cartItem);

        return cartMapper.toDto(cartRepository.save(cart));
    }
    @Transactional
    public CartDto subtractProduct(CartItemRequestDto cartItemRequestDto) {
        Cart cart = cartRepository.findById(cartItemRequestDto.getCartId()).orElseThrow(CartNotFoundException::new);
        CartItem cartItem = getCartItemByProduct(cartItemRequestDto, cart);

        BigDecimal quantity = cartItemRequestDto.getQuantity();
        if (quantity == null) {
            quantity = cartItem.getQuantity().subtract(BigDecimal.ONE);
        } else {
            throw new ArgumentNotValidException();
        }

        if (quantity.compareTo(BigDecimal.ZERO) <= 0) {
            cart.getCartItems().remove(cartItem);
        } else {
            cartItem.setQuantity(quantity);
            cartItem.setUpdatedDate(LocalDateTime.now());

        }

        cart.setUpdatedDate(LocalDateTime.now());

        return cartMapper.toDto(cartRepository.save(cart));
    }

    @Transactional
    public CartItem getCartItemByProduct(CartItemRequestDto cartItemRequestDto, Cart cart) {
        return cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(cartItemRequestDto.getProductId()))
                .findFirst()
                .orElseGet(() -> {
                    CartItem ci = createCartItem(cartItemRequestDto, cart);
                    cart.getCartItems().add(ci);
                    return ci;
                });
    }

    @Transactional
    public CartDto clearCart(String cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        cart.getCartItems().clear();

        return cartMapper.toDto(cartRepository.save(cart));
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
