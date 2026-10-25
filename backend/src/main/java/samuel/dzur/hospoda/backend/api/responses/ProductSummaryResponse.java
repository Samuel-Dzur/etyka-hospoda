package samuel.dzur.hospoda.backend.api.responses;

import java.math.BigDecimal;

public record ProductSummaryResponse(
        DrinkResponse product,
        Integer amount,
        BigDecimal unitPrice,
        BigDecimal summaryPrice
) {
}
