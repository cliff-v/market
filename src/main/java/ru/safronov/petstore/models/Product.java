package ru.safronov.petstore.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product extends BaseUuidEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

}
