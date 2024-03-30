package itep.resturant.service.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class RestaurantResponseDto {
    public long Id;
    public String name;
    public String location;
    public int phone;
    public String latitude;
    public String longitude;
    public boolean isOnline;
}
