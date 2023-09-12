package ru.safronov.petstore.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    protected String id;
    protected String name;
    private String ident;
    private List<ProductCategoryDto> products;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductCategoryDto {
        private String id;
        private String title;
        private BigDecimal price;
    }

}
