package samuel.dzur.hospoda.backend.repository.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drinks")
public class DrinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Boolean isForAdult;

    @OneToMany(mappedBy = "drink", fetch = FetchType.LAZY)
    @OrderBy("effectiveFrom DESC")
    private List<PriceRecordEntity> priceRecords = new ArrayList<>();

    public DrinkEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Boolean getIsForAdult() { return isForAdult; }
    public void setIsForAdult(Boolean isForAdult) { this.isForAdult = isForAdult; }

    public List<PriceRecordEntity> getPriceRecords() { return priceRecords; }
    public void setPriceRecords(List<PriceRecordEntity> priceRecords) { this.priceRecords = priceRecords; }
}
