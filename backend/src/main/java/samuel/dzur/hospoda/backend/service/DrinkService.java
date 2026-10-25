package samuel.dzur.hospoda.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samuel.dzur.hospoda.backend.api.responses.DrinkResponse;
import samuel.dzur.hospoda.backend.repository.DrinkRepository;
import samuel.dzur.hospoda.backend.repository.entity.DrinkEntity;
import samuel.dzur.hospoda.backend.repository.entity.PriceRecordEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrinkService {

    private final DrinkRepository drinkRepository;

    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Transactional(readOnly = true)
    public List<DrinkResponse> getDrinkMenu() {
        List<DrinkResponse> drinkResponses = drinkRepository.findAll().stream()
            .map(
                entity -> new DrinkResponse(
                    entity.getId(),
                    entity.getProductName(),
                    getCurrentPrice(entity),
                    entity.getIsForAdult()
                )
            )
            .collect(Collectors.toList());

        return drinkResponses;
    }

    private BigDecimal getCurrentPrice(DrinkEntity drink) {
        return drink.getPriceRecords().stream()
                .findFirst()
                .map(PriceRecordEntity::getPrice)
                .orElseThrow(() -> new IllegalStateException(
                        "Drink " + drink.getId() + " has no price records"
                ));
    }
}
