package samuel.dzur.hospoda.backend.repository.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "drinks")
public class DrinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean isForAdult;

    public DrinkEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Boolean getIsForAdult() { return isForAdult; }
    public void setIsForAdult(Boolean isForAdult) { this.isForAdult = isForAdult; }
}
