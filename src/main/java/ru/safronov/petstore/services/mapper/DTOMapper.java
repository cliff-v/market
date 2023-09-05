package ru.safronov.petstore.services.mapper;

import java.util.List;

public interface DTOMapper<D, E> {
    D toDto(E entity);
    List<D> toDto(List<E> entities);
}
