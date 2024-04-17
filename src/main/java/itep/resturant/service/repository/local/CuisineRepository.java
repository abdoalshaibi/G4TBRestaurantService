package itep.resturant.service.repository.local;

import itep.resturant.service.entity.local.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine,Long> {
}
