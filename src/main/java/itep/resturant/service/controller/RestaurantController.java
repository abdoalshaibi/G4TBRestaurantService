package itep.resturant.service.controller;

import itep.resturant.service.service.dto.RestaurantRequestDto;
import itep.resturant.service.service.dto.RestaurantResponseDto;
import itep.resturant.service.service.restaurant.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {


    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> Creat(@PathVariable long id ,@Valid @RequestBody RestaurantRequestDto request) {

        try {
            var restaurant = service.Create(id,request);

            if (restaurant != null) return ResponseEntity.ok(restaurant);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping
    public List<RestaurantResponseDto> getAll() {
        return service.GetAll();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id, @Valid @RequestBody RestaurantRequestDto request) {

        try {
            return ResponseEntity.ok(service.Update(id, request));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(ex.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity<Object> ChangeStatus(@RequestParam long id, @Valid @RequestParam boolean status) {
        try {

            return ResponseEntity.ok(service.ChangeStatus(id, status));

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
