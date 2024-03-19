package itep.resturant.service.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Items {
    @Id
    @GeneratedValue
    private Long id;
    private Long MenuId;
    private String name;
    private String image;
    private double price;
    private LocalDateTime CreatedAt;
    private LocalDateTime CreatedBy;
    private LocalDateTime UpdateAt;
    private LocalDateTime UpdateBy;

    public Items() {
    }

}
