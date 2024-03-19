package itep.resturant.service.controller;

import itep.resturant.service.service.dto.RestaurantRequestDto;
import itep.resturant.service.service.dto.RestaurantResponstDto;
import itep.resturant.service.service.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Restaurants")
public class RestaurantController {

    @Autowired
     private  RestaurantService service;



     @PostMapping
    public RestaurantResponstDto Creat(@RequestBody RestaurantRequestDto request) {

         return service.Create(request);
    }

   @GetMapping
    public List<RestaurantResponstDto> getAll() {
        return service.GetAll();
    }


     @PutMapping("/{id}")
    public RestaurantResponstDto update(@PathVariable long id, @RequestBody RestaurantRequestDto request) {
        return service.Update(id,request);
    }

}
