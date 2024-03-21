package itep.resturant.service.service.dto;

import lombok.Data;

@Data
public class ItemRequestDto {
    private String name;
    private String image;
    private double price;
    private long restaurantId;
    private long menuId;
}
