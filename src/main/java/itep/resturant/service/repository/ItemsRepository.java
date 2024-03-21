package itep.resturant.service.repository;

import itep.resturant.service.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {
}
