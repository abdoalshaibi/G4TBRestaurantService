package itep.resturant.service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull(message = "")
    public String name;
    private byte[] image;
    private double price;
    public String description;
    @NotNull
    public long createdBy;
    public long updatedBy;
    @NotNull
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;


    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public Item() {
    }

}
