package itep.resturant.service.demo.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
public class Restaurant{

    public Restaurant() {
    }
   
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long Id;

    public String name;

    public String location;    

    public int phone;    

    public String latitude;

    public String longitude;

    public boolean isOnline;

    public int CreatedBy;

    public int UpdatedBy;
    
    public LocalDateTime CreatedAt ;

    public LocalDateTime UpdatedAt;
}
