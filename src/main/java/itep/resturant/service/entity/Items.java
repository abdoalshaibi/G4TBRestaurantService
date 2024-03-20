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

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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



}
