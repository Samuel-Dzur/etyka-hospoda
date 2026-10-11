package samuel.dzur.hospoda.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samuel.dzur.hospoda.backend.dto.DrinkDto;
import samuel.dzur.hospoda.backend.service.DrinkService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DrinkController {

    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping("/drink-menu")
    public List<DrinkDto> getDrinkMenu() {
        return drinkService.getDrinkMenu();
    }
}
