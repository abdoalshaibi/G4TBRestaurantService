package itep.resturant.service.controller;

import itep.resturant.service.service.dto.CuisineRequestDto;
import itep.resturant.service.service.cuisine.CuisineService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cuisine")
public class CuisineController {


    private final CuisineService service;

    public CuisineController(CuisineService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Object> Create(@Valid @RequestBody CuisineRequestDto request) {

        try {

            return ResponseEntity.ok(service.Create( request));

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> GetAll() {

        try {

            return ResponseEntity.ok(service.GetAll());

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Object Update(@PathVariable long id, @Valid @RequestBody CuisineRequestDto request) {

        try {

            return ResponseEntity.ok(service.Update(id, request));

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}

