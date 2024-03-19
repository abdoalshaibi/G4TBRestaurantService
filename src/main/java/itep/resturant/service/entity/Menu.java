package itep.resturant.service.entity;
import jakarta.persistence.*;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private String id;
    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;
    private String name;
    private String description;
    private double price;

}