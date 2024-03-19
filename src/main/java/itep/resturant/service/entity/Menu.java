package itep.resturant.service.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue
    private String id;
    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;
    private String name;
    private String image;
    private String description;

    public Menu() {

    }
}