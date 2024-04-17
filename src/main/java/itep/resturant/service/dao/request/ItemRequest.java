package itep.resturant.service.dao.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemRequest {
    @NotBlank(message = "name is required")
    @NotNull(message ="name is required" )
    @Size(min = 5, max = 50, message = "The field must be between {min} and {max} characters long")
    private String name;
    private String image;
    @Digits(integer = 5, fraction = 2, message = "Value must have up to {integer} digits in total, with {fraction} decimal places")
    private double price;
    @Size(min = 150, max = 2000, message = "The field must be between {min} and {max} characters long")
    private String description;

}
