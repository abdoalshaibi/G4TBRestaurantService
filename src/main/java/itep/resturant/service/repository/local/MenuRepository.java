package itep.resturant.service.repository.local;

import itep.resturant.service.entity.local.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> findAllByRestaurantId(long id);
}
