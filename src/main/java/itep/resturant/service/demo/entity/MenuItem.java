package itep.resturant.service.demo.entity;
import org.springframework.data.annotation.Id;

public class MenuItem {

    @Id
    private String id;

    private String restaurantId;

    private String name;

    private String description;

    private double price;
}