package itep.resturant.service.controller;

import itep.resturant.service.dao.APIResponse;
import itep.resturant.service.dao.request.RestaurantRequest;
import itep.resturant.service.dao.response.RestaurantResponse;
import itep.resturant.service.service.restaurant.RestaurantService;
import jakarta.validation.Valid;
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
    public ResponseEntity<APIResponse<RestaurantResponse>> Creat(@PathVariable long id , @Valid @RequestBody RestaurantRequest request) {


            var result = service.Create(id, request);

           return ResponseEntity.status(HttpStatus.valueOf(result.getHttpStatus()))
                   .body(result);
    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {

        try {

            return ResponseEntity.ok( service.GetAll());

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(ex.getMessage());
        }
    }

//    @GetMapping
//    public ResponseEntity<Object> get() {
//
//        try {
//
//            return ResponseEntity.ok( service.Get());
//
//        } catch (Exception ex) {
//
//            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(ex.getMessage());
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByCuisine(@PathVariable long id) {

        try {

            return ResponseEntity.ok( service.getByCuisineId(id));

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id, @Valid @RequestBody RestaurantRequest request) {

        try {

            return ResponseEntity.ok(service.Update(id, request));

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(ex.getMessage());
        }
    }

    @PutMapping("/changStatus")
    public ResponseEntity<Object> ChangeStatus(@RequestParam long id, @Valid @RequestParam boolean status) {
        try {

            return ResponseEntity.ok(service.ChangeStatus(id, status));

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
