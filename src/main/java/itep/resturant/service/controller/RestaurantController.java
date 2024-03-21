package itep.resturant.service.controller;

import itep.resturant.service.service.dto.RestaurantRequestDto;
import itep.resturant.service.service.dto.RestaurantResponseDto;
import itep.resturant.service.service.restaurant.RestaurantService;
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

    @PostMapping
    public ResponseEntity<RestaurantResponseDto> Creat(@RequestBody  RestaurantRequestDto request) {

         return ResponseEntity.ok(service.Create(request));
    }

   @GetMapping
    public List<RestaurantResponseDto> getAll() {
        return service.GetAll();
    }


     @PutMapping("/{id}")
    public RestaurantResponseDto update(@PathVariable long id, @RequestBody RestaurantRequestDto request) {
        return service.Update(id,request);
    }

    @PutMapping()
    public RestaurantResponseDto ChangeStatus(@RequestParam long id, @RequestParam boolean status) {
        return service.ChangeStatus(id,status);
    }

}
