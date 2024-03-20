package itep.resturant.service.entity;
import java.time.LocalDateTime;
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
public class Restaurant {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    private String name;
    private String location;
    private int phone;
    private String latitude;
    private String longitude;
    private boolean isOnline;
    private int createdBy;
    private int updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
