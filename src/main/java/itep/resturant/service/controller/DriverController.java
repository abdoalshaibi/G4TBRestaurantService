package itep.resturant.service.controller;

import itep.resturant.service.dao.APIResponse;
import itep.resturant.service.service.driver.DriverService;
import itep.resturant.service.util.Constant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverController {

    private final DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    @GetMapping
    public APIResponse<Object> getAll() {

       return service.getAll();
    }
}
