package itep.resturant.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itep.resturant.service.entity.Menu;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
