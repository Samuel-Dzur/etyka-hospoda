package samuel.dzur.hospoda.backend.api.responses;

import java.math.BigDecimal;

public record DrinkResponse(
    Long id,
    String productName,
    BigDecimal price,
    Boolean isForAdult
) {
}
