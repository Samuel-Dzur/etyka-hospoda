package samuel.dzur.hospoda.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samuel.dzur.hospoda.backend.api.responses.OrderResponse;
import samuel.dzur.hospoda.backend.api.responses.UserResponse;
import samuel.dzur.hospoda.backend.api.responses.UserWithOrdersResponse;
import samuel.dzur.hospoda.backend.repository.UserRepository;
import samuel.dzur.hospoda.backend.repository.entity.UserEntity;

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
        return userRepository.findAll().stream()
                .map(this::toUserResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<UserWithOrdersResponse> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserWithOrdersResponse(
                        user.getId(),
                        user.getName(),
                        user.getIsActive(),
                        user.getIsAdult(),
                        user.getPocket(),
                        user.getOrders().stream()
                                .map(order -> new OrderResponse(
                                        order.getId(),
                                        order.getProductName(),
                                        order.getAmount(),
                                        order.getPrice()
                                ))
                                .toList()
                ));
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
