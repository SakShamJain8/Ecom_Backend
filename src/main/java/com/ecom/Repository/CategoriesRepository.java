package com.ecom.Repository;

import com.ecom.Model.Categories;
import com.ecom.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

}
