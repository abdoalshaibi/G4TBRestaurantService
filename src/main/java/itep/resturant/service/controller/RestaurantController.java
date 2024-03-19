package itep.resturant.service.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itep.resturant.service.demo.entity.Restaurant;
import itep.resturant.service.demo.repository.RestaurantRepository;

@RestController
@RequestMapping("/api/v1/Restaurants")
public class RestaurantController {

     private final RestaurantRepository restaurantRepository;

     public RestaurantController(RestaurantRepository RestaurantInterface) {
        this.restaurantRepository = RestaurantInterface;
    }

     @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant Restaurant) {
        return restaurantRepository.save(Restaurant);
    }

   @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable int id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

     @PutMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable int id, @RequestBody Restaurant Restaurant) {
        Restaurant.Id = id;
        return restaurantRepository.save(Restaurant);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable int id) {
        restaurantRepository.deleteById(id);
    }

    
}
