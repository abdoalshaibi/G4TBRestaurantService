package itep.resturant.service.service;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemsRequestDto {

   
     public Long MenuId;
     public String name;
     public String image;
     public double price;
     private LocalDateTime createdAt;
     private LocalDateTime updatedAt;

}
