package itep.resturant.service.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class RestaurantResponseDto  {
    public long Id;
    public String name;
    public String location;
    public String phone;
    public String latitude;
    public String longitude;
    public boolean isOnline;
}
