package ru.safronov.petstore.services.mapper;

import org.mapstruct.Mapper;
import ru.safronov.petstore.models.Category;
import ru.safronov.petstore.services.dto.CategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDto, Category> {
}
