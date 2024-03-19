package itep.resturant.service.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private String id;
    private String restaurantId;

    private String name;

    private String description;

    private double price;


}