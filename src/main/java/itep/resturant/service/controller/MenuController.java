package itep.resturant.service.controller;

import itep.resturant.service.service.dto.MenuRequestDto;
import itep.resturant.service.service.dto.MenuResponseDto;
import itep.resturant.service.service.menu.MenuService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {


    MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    @PostMapping(value = "/{id}", name = "id is restaurant identity")
    public ResponseEntity<Object> Create(@PathVariable long id, @Valid @RequestBody MenuRequestDto request) {
        try {
            return ResponseEntity.ok(service.Create(id, request));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}", name = "id is restaurant identity")
    public ResponseEntity<Object> getAll(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.GetById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public MenuResponseDto update(@PathVariable long id, @Valid @RequestBody MenuRequestDto request) {
        return service.Update(id, request);
    }

    @DeleteMapping("/{id}")
    public void update(@PathVariable long id) {
        service.Delete(id);
    }
}
