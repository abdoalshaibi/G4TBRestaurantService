package itep.resturant.service.controller;

import itep.resturant.service.dao.request.RestaurantRequestDto;
import itep.resturant.service.service.restaurant.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

             return ResponseEntity.ok(service.Create(id,request));
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
    public ResponseEntity<Object> getAll() {

        try {

            return ResponseEntity.ok( service.GetAll());

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByCuisine(@PathVariable long id) {

        try {

            return ResponseEntity.ok( service.getByCuisineId(id));

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(ex.getMessage());
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
