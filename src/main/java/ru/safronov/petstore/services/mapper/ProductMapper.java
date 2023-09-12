package ru.safronov.petstore.services.mapper;

import org.mapstruct.Mapper;
import ru.safronov.petstore.services.dto.ProductDto;
import ru.safronov.petstore.models.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDto, Product> {

}
