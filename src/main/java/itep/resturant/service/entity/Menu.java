package itep.resturant.service.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
public class Menu{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    public String name;
    public String description;
    private byte[] image;
    @NotNull
    public long createdBy;
    public long updatedBy;
    @NotNull
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menu")
    private Set<Item> items;

    public Menu() {

    }
}