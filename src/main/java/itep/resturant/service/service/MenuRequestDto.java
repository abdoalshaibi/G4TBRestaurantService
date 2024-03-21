package itep.resturant.service.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuRequestDto {

    public Long restaurantId;

    public String name;

    public String description;

    public double price;
    
}
