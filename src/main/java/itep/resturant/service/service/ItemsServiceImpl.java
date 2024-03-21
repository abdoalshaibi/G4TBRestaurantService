package itep.resturant.service.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itep.resturant.service.entity.Items;
import itep.resturant.service.repository.ItemsRepository;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    ItemsRepository repository;

    @Autowired 
    ModelMapper mapper;

    @Override
    public ItemsResponseDto Create(ItemsRequestDto request) {

        var Items = mapper.map(request, Items.class);

        return mapper.map(repository.save(Items),ItemsResponseDto.class);
    }

    @Override
    public List<ItemsResponseDto> GetAll() {

        return repository.findAll()
                .stream()
                .map(e->mapper.map(e,ItemsResponseDto.class))
                .toList();


    }

    @Override
    public ItemsResponseDto Update(long id, ItemsRequestDto request) {


        var Items = repository.findById(id).get();


        mapper.map(request,Items);

         return mapper.map(repository.save(Items),ItemsResponseDto.class);
    }

    
}
