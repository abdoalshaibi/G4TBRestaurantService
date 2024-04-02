package itep.resturant.service.controller;

import itep.resturant.service.service.dto.RestaurantRequestDto;
import itep.resturant.service.service.dto.RestaurantResponseDto;
import itep.resturant.service.service.restaurant.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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

             return ResponseEntity.ok(restaurant);


        }
        catch (DataIntegrityViolationException ex) {
            if(ex.getMessage().contains("restaurant_mobile_key"))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("mobile already exist");
            if(ex.getMessage().contains("restaurant_phone_key"))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("phone already exist");
            if(ex.getMessage().contains("restaurant_email_key"))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email already exist");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown Error");
        }
    }

    @GetMapping
    public List<RestaurantResponseDto> getAll() {
        return service.GetAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByCuisine(@PathVariable long id) {

        try {
            return ResponseEntity.ok( service.getByCuisineId(id));
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_MODIFIED)
                    .body(ex.getMessage());
        }
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
