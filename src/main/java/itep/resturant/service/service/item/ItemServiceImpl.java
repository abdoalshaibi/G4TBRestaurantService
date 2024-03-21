package itep.resturant.service.service.item;

import itep.resturant.service.entity.Item;
import itep.resturant.service.repository.ItemRepository;
import itep.resturant.service.service.dto.ItemRequestDto;
import itep.resturant.service.service.dto.ItemResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemRepository repository;
    @Autowired
    ModelMapper mapper;
    @Override
    public ItemResponseDto Create(ItemRequestDto request) {

        var item = mapper.map(request, Item.class);

        return mapper.map(repository.save(item),ItemResponseDto.class);
    }

}
