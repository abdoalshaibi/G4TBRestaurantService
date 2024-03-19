package itep.resturant.service.service;

import lombok.Data;

@Data
public class  RestaurantRequestDto {

    public String name;
    public String location;
    public int phone;
    public String latitude;
    public String longitude;
    public boolean isOnline;
}
