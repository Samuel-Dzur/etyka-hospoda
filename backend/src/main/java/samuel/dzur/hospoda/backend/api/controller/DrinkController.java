package samuel.dzur.hospoda.backend.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import samuel.dzur.hospoda.backend.api.responses.DrinkResponse;
import samuel.dzur.hospoda.backend.service.DrinkService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
public class DrinkController {

    private final DrinkService drinkService;
    private final Path storageRoot;

    public DrinkController(
            DrinkService drinkService,
            @Value("${app.storage.root:storage}") String storageRoot
    ) {
        this.drinkService = drinkService;
        this.storageRoot = Path.of(storageRoot).toAbsolutePath().normalize();
    }

    @GetMapping("/drink-menu")
    public ResponseEntity<List<DrinkResponse>> getDrinkMenu() {
        List<DrinkResponse> drinks = drinkService.getDrinkMenu();
        return ResponseEntity.ok(drinks);
    }

    @GetMapping(value = "/image/{id}", produces = "image/webp")
    public ResponseEntity<Resource> getDrinkImage(@PathVariable Long id) {
        Path image = storageRoot.resolve("drinks").resolve(id + ".webp").normalize();
        if (!image.startsWith(storageRoot) || !Files.isRegularFile(image) || !Files.isReadable(image)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/webp"))
                .body(new FileSystemResource(image));
    }
}
