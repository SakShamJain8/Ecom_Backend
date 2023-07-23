package com.ecom.Repository;

import com.ecom.Model.Categories;
import com.ecom.Model.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {



    @Override
    void flush();

    @Override
    <S extends Product> S saveAndFlush(S entity);

    @Override
    <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    default void deleteInBatch(Iterable<Product> entities) {
        JpaRepository.super.deleteInBatch(entities);
    }

    @Override
    void deleteAllInBatch(Iterable<Product> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Integer> integers);

    @Override
    void deleteAllInBatch();

    @Override
    Product getOne(Integer integer);

    @Override
    Product getById(Integer integer);

    @Override
    Product getReferenceById(Integer integer);

    @Override
    <S extends Product> List<S> findAll(Example<S> example);

    @Override
    <S extends Product> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends Product> List<S> saveAll(Iterable<S> entities);

    @Override
    List<Product> findAll();

    @Override
    List<Product> findAllById(Iterable<Integer> integers);

    @Override
    <S extends Product> S save(S entity);

    @Override
    Optional<Product> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Product entity);

    @Override
    void deleteAllById(Iterable<? extends Integer> integers);

    @Override
    void deleteAll(Iterable<? extends Product> entities);

    @Override
    void deleteAll();

    @Override
    List<Product> findAll(Sort sort);

    @Override
    Page<Product> findAll(Pageable pageable);

    @Override
    <S extends Product> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends Product> long count(Example<S> example);

    @Override
    <S extends Product> boolean exists(Example<S> example);

    @Override
    <S extends Product, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
