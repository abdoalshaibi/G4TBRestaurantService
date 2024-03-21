package itep.resturant.service.controller;

import itep.resturant.service.service.dto.ItemRequestDto;
import itep.resturant.service.service.dto.ItemResponseDto;
import itep.resturant.service.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {

    @Autowired
    private ItemService service;

    @PostMapping("/{id}")
    public ItemResponseDto Create(@PathVariable long id,@RequestBody ItemRequestDto request)
    {
        return service.Create(id,request);
    }

    @PutMapping("/{id}")
    public ItemResponseDto Update(@PathVariable long id,@RequestBody ItemRequestDto request)
    {
        return service.Update(id,request);
    }

}
