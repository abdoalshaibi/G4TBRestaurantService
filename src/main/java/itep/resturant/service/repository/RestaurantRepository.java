package itep.resturant.service.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import itep.resturant.service.demo.entity.Restaurant;



public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
