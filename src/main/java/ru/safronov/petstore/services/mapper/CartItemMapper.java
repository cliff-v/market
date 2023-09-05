package ru.safronov.petstore.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.safronov.petstore.models.Cart;
import ru.safronov.petstore.models.CartItem;
import ru.safronov.petstore.services.dto.CartItemDto;

@Mapper(componentModel = "spring")
public interface CartItemMapper extends DTOMapper<CartItemDto, CartItem> {
    @Mappings(value = {
            @Mapping(source = "product.title", target = "title")
    })
    CartItemDto toDto(CartItem cartItem);
}
