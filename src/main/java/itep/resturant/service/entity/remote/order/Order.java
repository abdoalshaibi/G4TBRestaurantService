package itep.resturant.service.entity.remote.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.UUID;


public record Order (
    @JsonProperty UUID id,
    @JsonProperty UUID customerId,
    @JsonProperty String customerNumber,
    @JsonProperty Double customerLocationLat,
    @JsonProperty Double customerLocationLon,
    @JsonProperty UUID restaurantId,
    @JsonProperty String restaurantName,
    @JsonProperty Double restaurantLocationLat,
    @JsonProperty Double restaurantLocationLon,
    @JsonProperty UUID deliveryId,
    @JsonProperty String deliveryNumber,
    @JsonProperty Double totalPrice,
    @JsonProperty Long paymentMethodId,
    @JsonProperty String paymentReference,
    @JsonProperty Long orderStatusId,
    @JsonProperty Long createdAt,
    @JsonProperty Long updatedAt){}
