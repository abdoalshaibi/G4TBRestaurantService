package itep.resturant.service.controller;

import itep.resturant.service.service.dto.ItemRequestDto;
import itep.resturant.service.service.dto.ItemResponseDto;
import itep.resturant.service.service.item.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {


    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping("/{id}")
    public ResponseEntity<ItemResponseDto> Create(@PathVariable long id,@Valid @RequestBody ItemRequestDto request) {

        var item = service.Create(id, request);
        if (item != null)
            return ResponseEntity.ok(item);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ItemResponseDto Update(@PathVariable long id,@Valid @RequestBody ItemRequestDto request)
    {
        return service.Update(id,request);
    }

}
