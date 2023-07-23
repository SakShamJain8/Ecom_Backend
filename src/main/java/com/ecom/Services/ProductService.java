package com.ecom.Services;

import com.ecom.Exception.ResourceNotFoundException;
import com.ecom.Model.Categories;
import com.ecom.Model.Product;
import com.ecom.Repository.CategoriesRepository;
import com.ecom.Repository.ProductRepository;
import com.ecom.payload.CategoriesDto;
import com.ecom.payload.ProductDto;
import com.ecom.payload.ProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private ModelMapper mapper;
    public ProductDto createProduct(ProductDto productDto , int catId){
        // fetching

        Categories cat = this.categoriesRepository.findById(catId). orElseThrow(() -> new ResourceNotFoundException("This Category Doesn't Exist"));
        // ProductDto to product
        Product product = toEntity(productDto);

        product.setCategories(cat);
        Product save = this.productRepo.save(product);

        // Product to productDto
        ProductDto dto = toDto(save);
        return dto;
    }

    // view
    public List<ProductDto> viewAll(){
        List<Product> findAll = productRepo.findAll();
        List<ProductDto> findAllDto = findAll.stream().map(product -> this.toDto(product)).collect(Collectors.toList());
                return findAllDto;
    }

    // viewById
    public ProductDto viewProductById(int pid){
        Product findById = productRepo.findById(pid).orElseThrow(() -> new ResourceNotFoundException(+pid + " product not found"));
        ProductDto dto = this.toDto(findById);
        return dto;
    }

    public void deleteProduct(int pid){
        Product byId = productRepo.findById(pid).orElseThrow(() -> new ResourceNotFoundException(+pid + " Product not found"));
        productRepo.deleteById(pid);
    }

    // update
    public ProductDto updateProduct(int pid , ProductDto newProduct){
        Product product = productRepo.findById(pid).get();
        product.setProduct_name(newProduct.getProduct_name());
        product.setLive(newProduct.isLive());
        product.setStock(newProduct.isStock());
        product.setProduct_price(newProduct.getProduct_price());
        product.setProduct_desc(newProduct.getProduct_desc());
        product.setProduct_imageName(newProduct.getProduct_imageName());
        product.setProduct_quantity(newProduct.getProduct_quantity());
        Product save = productRepo.save(product);
        return toDto(save);
    }

    // find by Category

    //public ProductResponse findProductByCategory(int catId , int pageSize, int pageNumber, String sortDir){
     //   Categories cat=this.categoriesRepository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category Doesn't Exist"));
        //List<Product> findByCategory= this.productRepo.findByCategory(cat);
        //List<ProductDto> collected= findByCategory.stream().map(product -> toDto(product)).collect(Collectors.toList());

     //   Pageable pageable = PageRequest.of(pageNumber,pageSize);
    //    Page<Product> page = this.productRepo.findByCategory(cat,pageable);
     //   List<Product> product=page.getContent();
     //   List<ProductDto> productDto = product.stream().map(p -> toDto(p)).collect(Collectors.toList());


   //     ProductResponse response=new ProductResponse();
    //    response.setContent(productDto);
    //    response.setPageNumber(page.getNumber());
    //    response.setPageSize(page.getSize());
    //    response.setTotalPages(page.getTotalPages());
    //    response.setLastPage(page.isLast());

      //  return response;
    // }

    //ProductDto to product
    public Product toEntity(ProductDto pDto){

        return this.mapper.map(pDto , Product.class);
    }

    // product to productDto
    public ProductDto toDto(Product product){
        ProductDto pDto = new ProductDto();

        pDto.setProduct_id(product.getProduct_id());
        pDto.setLive(product.isLive());
        pDto.setProduct_desc(product.getProduct_desc());
        pDto.setProduct_name(product.getProduct_name());
        pDto.setProduct_price(product.getProduct_price());
        pDto.setProduct_imageName(product.getProduct_imageName());
        pDto.setProduct_quantity(product.getProduct_quantity());
        pDto.setStock(product.isStock());

        CategoriesDto catDto = new CategoriesDto();
        catDto.setCategoryId(product.getCategories().getCategoryId());
        catDto.setTitle(product.getCategories().getTitle());
         pDto.setCategory(catDto);
        return pDto;
    }
}
