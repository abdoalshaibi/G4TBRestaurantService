package itep.resturant.service.service.driver;

import itep.resturant.service.dao.APIResponse;
import itep.resturant.service.repository.remote.DriverRepository;
import itep.resturant.service.util.Constant;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

   private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public APIResponse<Object> getAll() {

        var driverOptional = driverRepository.getAll();

        if (driverOptional.isEmpty())
            return APIResponse.notFound(null, Constant.getLogResponseHashMap(),"");

        var driver = driverOptional.get();

        return APIResponse.ok(driver, Constant.getLogResponseHashMap(),"");
    }
}
