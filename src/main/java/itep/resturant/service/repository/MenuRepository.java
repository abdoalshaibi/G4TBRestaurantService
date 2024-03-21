package itep.resturant.service.repository;

import itep.resturant.service.entity.Menu;
import itep.resturant.service.service.dto.MenuResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {

    List<Menu> findAllByRestaurantId(long id);
}
