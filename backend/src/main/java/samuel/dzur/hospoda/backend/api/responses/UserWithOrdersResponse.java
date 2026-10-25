package samuel.dzur.hospoda.backend.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public record UserWithOrdersResponse(
        Long id,
        String name,
        Boolean isActive,
        Boolean isAdult,
        BigDecimal pocket,
        @JsonProperty("Orders") List<OrderResponse> orders
) {
}
