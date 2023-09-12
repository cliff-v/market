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
public class ProductDto {
    protected String id;
    protected String title;
    protected BigDecimal price;
    protected List<CategoryProductDto> category;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryProductDto {
        protected String id;
        protected String name;
        private String ident;
    }

}
