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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String image;
    private double price;
    private LocalDateTime CreatedAt;
    private LocalDateTime CreatedBy;
    private LocalDateTime UpdateAt;
    private LocalDateTime UpdateBy;


    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public Item() {
    }

}
