package itep.resturant.service.service.driver;

import itep.resturant.service.dao.APIResponse;
import org.springframework.stereotype.Service;

@Service
public interface DriverService {
    APIResponse<Object> getAll();
}
