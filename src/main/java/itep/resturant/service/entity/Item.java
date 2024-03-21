package itep.resturant.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
public class Item {

    @EmbeddedId
    private CompositeKeyItem id;
    private String name;
    private String image;
    private double price;
    private LocalDateTime CreatedAt;
    private LocalDateTime CreatedBy;
    private LocalDateTime UpdateAt;
    private LocalDateTime UpdateBy;

    @ManyToOne
    @MapsId("restaurantId")
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @MapsId("menuId")
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public Item() {
    }

}
