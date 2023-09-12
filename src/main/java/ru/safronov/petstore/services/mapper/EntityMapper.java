package ru.safronov.petstore.services.mapper;

import java.util.List;

public interface EntityMapper<D, E> extends DTOMapper<D, E> {
    E toEntity(D dto);
    List<E> toEntity(List<D> dtoList);
}
