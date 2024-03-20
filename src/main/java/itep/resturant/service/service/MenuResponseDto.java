package itep.resturant.service.service;

import lombok.Data;

@Data
public class MenuResponseDto {

    public Long id;

    public Long restaurantId;

    public String name;

    public String description;

    public double price;
    
}
