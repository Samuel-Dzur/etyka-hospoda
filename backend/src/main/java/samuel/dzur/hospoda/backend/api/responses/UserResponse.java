package samuel.dzur.hospoda.backend.api.responses;

import java.math.BigDecimal;

public record UserResponse(
        Long id,
        String name,
        Boolean isActive,
        Boolean isAdult,
        BigDecimal pocket
) {
}
