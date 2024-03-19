package itep.resturant.service.entity;


import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

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
    @OneToMany(mappedBy = "restaurant")
    private Set<Menu> menu;
}
