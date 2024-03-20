package itep.resturant.service.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import itep.resturant.service.service.MenuRequestDto;
import itep.resturant.service.service.MenuResponseDto;
import itep.resturant.service.service.MenuService;

@RestController
@RequestMapping("/api/v1/Menu")
public class MenuController {
    
     @Autowired
     private  MenuService service;



     @PostMapping
    public MenuResponseDto Creat(@RequestBody MenuRequestDto request) {
        return service.Create(request);
    }

   @GetMapping
    public List<MenuResponseDto> getAll() {
        return service.GetAll();
    }


     @PutMapping("/{id}")
    public MenuResponseDto update(@PathVariable long id, @RequestBody MenuRequestDto request) {
        return service.Update(id,request);
    }

    
}
