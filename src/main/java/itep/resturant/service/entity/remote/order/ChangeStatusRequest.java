package itep.resturant.service.entity.remote.order;

import lombok.Data;

@Data
public class ChangeStatusRequest {

        private String status;
        private String deliveryId;
        private String deliveryNumber;
        private String note;
}
