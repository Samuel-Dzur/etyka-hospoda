package samuel.dzur.hospoda.backend.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samuel.dzur.hospoda.backend.api.responses.DrinkResponse;
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
    public List<DrinkResponse> getDrinkMenu() {
        return drinkService.getDrinkMenu();
    }
}
