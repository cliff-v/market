package ru.safronov.petstore.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    protected String id;
    protected LocalDate createDate;
    protected String createBy;
    protected List<CartItemDto> cartItems;
}
