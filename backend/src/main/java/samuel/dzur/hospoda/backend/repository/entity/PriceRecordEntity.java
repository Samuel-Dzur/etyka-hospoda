package samuel.dzur.hospoda.backend.repository.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "price_records",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_price_record_per_drink_and_effective_date",
                columnNames = {"drink_id", "effective_from"}
        )
)
public class PriceRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "drink_id", nullable = false)
    private DrinkEntity drink;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "effective_from", nullable = false)
    private LocalDateTime effectiveFrom;

    public PriceRecordEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public DrinkEntity getDrink() { return drink; }
    public void setDrink(DrinkEntity drink) { this.drink = drink; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public LocalDateTime getEffectiveFrom() { return effectiveFrom; }
    public void setEffectiveFrom(LocalDateTime effectiveFrom) { this.effectiveFrom = effectiveFrom; }
}
