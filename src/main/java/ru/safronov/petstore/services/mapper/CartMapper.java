package ru.safronov.petstore.services.mapper;

import org.mapstruct.Mapper;
import ru.safronov.petstore.services.dto.CartDto;
import ru.safronov.petstore.models.Cart;

@Mapper(componentModel = "spring", uses = CartItemMapper.class)
public interface CartMapper extends DTOMapper<CartDto, Cart> {
}
