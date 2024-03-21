package itep.resturant.service.service;

import lombok.Data;

@Data
public class ItemsResponseDto {
    
    public Long id;
     public Long MenuId;
     public String name;
     public String image;
     public double price;
    
}
