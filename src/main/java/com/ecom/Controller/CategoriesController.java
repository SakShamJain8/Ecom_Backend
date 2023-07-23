package com.ecom.Controller;


import com.ecom.Services.CategoriesService;
import com.ecom.payload.ApiResponse;
import com.ecom.payload.CategoriesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;
    private CategoriesDto create;

    // create
    @PostMapping("/create")
    public ResponseEntity<CategoriesDto> create(@RequestBody CategoriesDto catDto){
        CategoriesDto create = this.categoriesService.create(catDto);
        return new ResponseEntity<CategoriesDto>(create , HttpStatus.CREATED);
    }

    // update
    @PostMapping("/update/{catId}")
    public ResponseEntity<CategoriesDto> update(@RequestBody CategoriesDto catDto , @PathVariable int catId){
         CategoriesDto update = this.categoriesService.update(catDto , catId);
        return new ResponseEntity<CategoriesDto>(update , HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/delete/{catId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable int catId){
        this.categoriesService.delete(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted successfully" , true), HttpStatus.OK);
    }

    //getById
    @GetMapping("getById/{catId}")
    public ResponseEntity<CategoriesDto> getById(@PathVariable int catId){
        CategoriesDto getById = this.categoriesService.getById(catId);
        return new ResponseEntity<CategoriesDto>(getById , HttpStatus.ACCEPTED);
    }

    // getAll
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoriesDto>> getAll(){
        List<CategoriesDto> getAll = this.categoriesService.getAll();
        return new ResponseEntity<List<CategoriesDto>>(getAll , HttpStatus.OK);
    }
}
