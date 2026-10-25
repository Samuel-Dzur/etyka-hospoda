package samuel.dzur.hospoda.backend.api.responses;

import java.math.BigDecimal;

public record OrderResponse(
        Long id,
        String productName,
        Integer amount,
        BigDecimal price
) {
}
