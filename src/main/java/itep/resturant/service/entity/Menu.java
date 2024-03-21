package itep.resturant.service.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Builder
@Data
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long restaurantId;

    private String name;
    private String image;
    private String description;

    public Menu() {

    }
}