package itep.resturant.service.repository.local;

import itep.resturant.service.entity.local.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

   Optional<List<Restaurant>> findByCuisineId(long id);
   Optional<Restaurant> findRestaurantById(long id);
}
