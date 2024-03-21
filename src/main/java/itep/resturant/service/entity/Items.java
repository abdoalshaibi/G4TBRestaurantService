package itep.resturant.service.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Items {
    @Id
     @GeneratedValue(strategy=GenerationType.AUTO)
     private Long id;

     private Long MenuId;
     private String name;
     private String image;
     private double price;
     private int CreatedBy;
     private int UpdateBy;
     private LocalDateTime UpdateAt;
     private LocalDateTime CreatedAt;

    public Items() {
    }

}
