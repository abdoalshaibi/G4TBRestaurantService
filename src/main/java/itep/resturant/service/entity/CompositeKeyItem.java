package itep.resturant.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class CompositeKeyItem implements Serializable {

    @Column(name = "restaurant_id")
    private long restaurantId;
    @Column(name = "menu_id")
    private long menuId;
}
