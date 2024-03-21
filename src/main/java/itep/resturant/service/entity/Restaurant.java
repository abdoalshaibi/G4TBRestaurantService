package itep.resturant.service.entity;


import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
@AllArgsConstructor
public class Restaurant{

    public Restaurant() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long id;
    public String name;
    public String location;
    public String email;
    public byte[] logo;
    public int phone;
    public String latitude;
    public String longitude;
    public boolean isOnline;
    public LocalDateTime CreatedBy;
    public LocalDateTime UpdatedBy;
    public LocalDateTime CreatedAt ;
    public LocalDateTime UpdatedAt;

    @OneToMany(mappedBy = "restaurant")
    private Set<Menu> menu;

}
