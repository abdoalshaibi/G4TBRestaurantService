package itep.resturant.service.dao.response;

import lombok.Data;

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
