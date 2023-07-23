package com.ecom.Services;


import com.ecom.Exception.ResourceNotFoundException;
import com.ecom.Model.Categories;
import com.ecom.Repository.CategoriesRepository;
import com.ecom.payload.CategoriesDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private ModelMapper mapper;
    public CategoriesDto create(CategoriesDto dto){
        // catDto to cat
         Categories cat = this.mapper.map(dto, Categories.class);
         Categories save = this.categoriesRepository.save(cat);

        // cat to catDto

         return this.mapper.map(save , CategoriesDto.class);
    }

    public CategoriesDto update(CategoriesDto newCat , int catId){
        Categories oldCat= this.categoriesRepository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("This Category Doesn't Exist"));

        oldCat.setTitle(newCat.getTitle());
        Categories upCat = this.categoriesRepository.save(oldCat);
        return this.mapper.map(upCat , CategoriesDto.class);
    }

    public void delete(int catId){
        Categories Cat= this.categoriesRepository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("This Category Doesn't Exist"));
        this.categoriesRepository.delete(Cat);
    }

    public CategoriesDto getById(int catId){
        Categories getById= this.categoriesRepository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("This Category Doesn't Exist"));

        return this.mapper.map(getById , CategoriesDto.class);
    }

    public List<CategoriesDto> getAll(){
        List<Categories> findAll = this.categoriesRepository.findAll();
        List<CategoriesDto> allDto = findAll.stream().map(cat -> this.mapper.map(cat , CategoriesDto.class)).collect(Collectors.toList());
        return allDto;
    }
}
