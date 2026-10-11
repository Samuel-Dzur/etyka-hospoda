package samuel.dzur.hospoda.backend.service;

import org.springframework.stereotype.Service;
import samuel.dzur.hospoda.backend.dto.DrinkDto;
import samuel.dzur.hospoda.backend.repository.DrinkRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrinkService {

    private final DrinkRepository drinkRepository;

    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public List<DrinkDto> getDrinkMenu() {
        return drinkRepository.findAll().stream()
                .map(entity -> new DrinkDto(
                        entity.getId(),
                        entity.getProductName(),
                        entity.getPrice(),
                        entity.getIsForAdult()
                ))
                .collect(Collectors.toList());
    }
}
