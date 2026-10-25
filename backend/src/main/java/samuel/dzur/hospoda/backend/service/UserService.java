package samuel.dzur.hospoda.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samuel.dzur.hospoda.backend.api.responses.OrderResponse;
import samuel.dzur.hospoda.backend.api.responses.UserResponse;
import samuel.dzur.hospoda.backend.api.responses.UserWithOrdersResponse;
import samuel.dzur.hospoda.backend.repository.UserRepository;
import samuel.dzur.hospoda.backend.repository.entity.OrderEntity;
import samuel.dzur.hospoda.backend.repository.entity.UserEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        List<UserResponse> userResponses = userRepository.findAll().stream()
            .map(this::toUserResponse)
            .toList();

        return userResponses;
    }

    @Transactional(readOnly = true)
    public Optional<UserWithOrdersResponse> getUserById(Long id) {
        return userRepository.findById(id).map(this::toUserWithOrdersResponse);
    }

    private UserWithOrdersResponse toUserWithOrdersResponse(UserEntity user) {
        List<OrderResponse> orders = user.getOrders().stream()
            .map(this::toOrderResponse)
            .toList();

        return new UserWithOrdersResponse(
            user.getId(),
            user.getName(),
            user.getIsActive(),
            user.getIsAdult(),
            user.getPocket(),
            orders
        );
    }

    private OrderResponse toOrderResponse(OrderEntity order) {
        BigDecimal totalPrice = order.getPriceRecord().getPrice()
            .multiply(BigDecimal.valueOf(order.getAmount()));

        return new OrderResponse(
            order.getId(),
            order.getPriceRecord().getDrink().getProductName(),
            order.getAmount(),
            totalPrice
        );
    }

    private UserResponse toUserResponse(UserEntity user) {
        return new UserResponse(
            user.getId(),
            user.getName(),
            user.getIsActive(),
            user.getIsAdult(),
            user.getPocket()
        );
    }
}
