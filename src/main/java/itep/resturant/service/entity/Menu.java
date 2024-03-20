package itep.resturant.service.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Menu {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long restaurantId;

    private String name;

    private String description;

    private double price;



}