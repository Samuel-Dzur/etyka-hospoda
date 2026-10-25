package samuel.dzur.hospoda.backend.api.responses;

import java.math.BigDecimal;

public record UserOrderSummaryResponse(
        Long productId,
        String productName,
        Integer amount,
        BigDecimal price
) {
}
