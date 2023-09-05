package ru.safronov.petstore.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class Cart extends BaseUuidEntity {
    @Column(name = "create_date")
    protected LocalDateTime createDate;

    @Column(name = "discount_code")
    protected String discountCode;

    @Column(name = "discount_amount")
    protected BigDecimal discountAmount;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<CartItem> cartItems;

    @Column(name = "updated_date")
    protected LocalDateTime updatedDate;

}
