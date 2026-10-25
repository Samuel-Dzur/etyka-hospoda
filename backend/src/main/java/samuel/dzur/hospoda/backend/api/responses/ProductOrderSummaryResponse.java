package samuel.dzur.hospoda.backend.api.responses;

import java.math.BigDecimal;

public record ProductOrderSummaryResponse(
        Long userId,
        Integer amount,
        BigDecimal price
) {
}
