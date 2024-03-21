package itep.resturant.service.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;
    private String name;
    private String image;
    private String description;
    private LocalDateTime CreatedAt;
    private LocalDateTime CreatedBy;
    private LocalDateTime UpdateAt;
    private LocalDateTime UpdateBy;

    @OneToMany(mappedBy = "menu")
    private Set<Item> items;

    public Menu() {

    }
}