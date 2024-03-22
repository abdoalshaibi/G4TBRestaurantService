package itep.resturant.service.repository;

import itep.resturant.service.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

   Optional<List<Restaurant>> findByCuisineId(long id);
}
