package com.ecom.Controller;


import com.ecom.Services.ProductService;
import com.ecom.payload.AppConstants;
import com.ecom.payload.ProductDto;
import com.ecom.payload.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecom.Model.Product;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController{
    @Autowired
    private ProductService productService;

    @PostMapping("/create/{catId}")
    // create
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product , @PathVariable int catId){
        ProductDto createProduct = productService.createProduct(product , catId);
        return new ResponseEntity<ProductDto>(createProduct, HttpStatus.CREATED);
    }

    // view
    @GetMapping("/view")
    public ResponseEntity<List<ProductDto>> viewAllProduct( ){
        List<ProductDto> viewAll = productService.viewAll();
        return new ResponseEntity<List<ProductDto>>(viewAll , HttpStatus.ACCEPTED);
    }
    // viewById
    @ResponseBody
    @GetMapping("view/{productId}")
    public ResponseEntity<ProductDto> viewProductById(@PathVariable int productId){
        ProductDto viewProductById = productService.viewProductById(productId);
        return new ResponseEntity<ProductDto>(viewProductById , HttpStatus.OK);
    }

    // delete
    @DeleteMapping("delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<String>("Product Deleted Successfully", HttpStatus.OK);
    }

    // update
    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable int productId , @RequestBody ProductDto newProduct){
        ProductDto updateProduct = productService.updateProduct(productId , newProduct);
        return new ResponseEntity<ProductDto>(updateProduct , HttpStatus.ACCEPTED);
    }

    // find by category

   // @GetMapping("/category/{catId}")
   // public ProductResponse getProductByCategory(@PathVariable int catId, @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NO_STRING , required = false)int pageNumber,
    //                                            @RequestParam(value = "pageSize" , defaultValue = AppConstants.PAGE_SIZE_STRING , required = false)int pageSize ,
      //                                          @RequestParam(value = "sortBy" , defaultValue = AppConstants.SORT_BY_STRING , required = false)String sortBy ,
        //                                        @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR_STRING , required = false)String sortDir){
      //  ProductResponse findProductByCategory= this.productService.findProductByCategory(catId, pageSize, pageNumber , sortDir);
      //  return findProductByCategory;
    //}
}
