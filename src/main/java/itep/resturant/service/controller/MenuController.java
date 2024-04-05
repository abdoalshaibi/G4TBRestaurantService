package itep.resturant.service.controller;

import itep.resturant.service.service.dto.MenuRequestDto;
import itep.resturant.service.service.menu.MenuService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {

    private final MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> create(@PathVariable long id, @Valid @RequestBody MenuRequestDto request) {
        try {

            return ResponseEntity.ok(service.create(id, request));

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.CREATED).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}", name = "id is restaurant identity")
    public ResponseEntity<Object> getAll(@PathVariable long id) {
        try {

            return ResponseEntity.ok(service.getAllById(id));

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id, @Valid @RequestBody MenuRequestDto request) {
        try {

            return ResponseEntity.ok(service.update(id, request));

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        try {

            return ResponseEntity.ok(service.delete(id));

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
