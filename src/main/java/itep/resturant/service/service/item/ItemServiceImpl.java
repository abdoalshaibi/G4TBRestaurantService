package itep.resturant.service.service.item;

import itep.resturant.service.entity.Item;
import itep.resturant.service.repository.ItemRepository;
import itep.resturant.service.repository.MenuRepository;
import itep.resturant.service.service.dto.ItemRequestDto;
import itep.resturant.service.service.dto.ItemResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{


    ItemRepository repository;
    MenuRepository menuRepository;
    ModelMapper mapper;

    public ItemServiceImpl(ItemRepository repository, MenuRepository menuRepository, ModelMapper mapper) {
        this.repository = repository;
        this.menuRepository = menuRepository;
        this.mapper = mapper;
    }

    @Override
    public ItemResponseDto Create(long id ,ItemRequestDto request) {

        var item = mapper.map(request, Item.class);
        var menu=menuRepository.findById(id).get();
         item.setMenu(menu);
        return mapper.map(repository.save(item),ItemResponseDto.class);
    }



    @Override
    public ItemResponseDto Update(long id,ItemRequestDto request) {
        var item= repository.findById(id).get();
        mapper.map(request,item);
        return mapper.map(repository.save(item),ItemResponseDto.class);
    }

}
