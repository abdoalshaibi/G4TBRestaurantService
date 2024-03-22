package itep.resturant.service.entity;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    public String name;
    public String description;
    public String location;
    public String email;
    public byte[] logo;
    public int phone;
    public int mobile;
    public String latitude;
    public String longitude;
    public String tag;
    public boolean isOnline;
    @NotNull(message ="CreatedBy is required" )
    public LocalTime openingAt;
    public LocalTime closingAt;
    @NotNull(message ="CreatedBy is required" )
    public long createdBy;
    public long updatedBy;
    @NotNull(message ="CreatedBy is required" )
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
    @OneToMany(mappedBy = "restaurant")
    private Set<Menu> menu;

}
