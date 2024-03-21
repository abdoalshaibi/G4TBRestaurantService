package itep.resturant.service.repository;

import itep.resturant.service.entity.CompositeKeyItem;
import itep.resturant.service.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, CompositeKeyItem> {
}