package com.ecommerce.service;

import com.ecommerce.entity.*;
import com.ecommerce.exception.ApiException;
import com.ecommerce.repository.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repo;
    private final CategoryService categories;

    @Transactional
    public Product create(Product p) {
        p.setProductId(null);
        p.setCategory(resolveCategory(p.getCategory()));
        return repo.save(p);
    }
    public List<Product> all() { return repo.findAll(); }
    public Product get(Long id) { return repo.findById(id).orElseThrow(() -> ApiException.notFound("Product not found: " + id)); }
    public List<Product> byCategory(Long categoryId) {
        categories.get(categoryId);
        return repo.findByCategoryCategoryId(categoryId);
    }

    @Transactional
    public Product update(Long id, Product in) {
        Product p = get(id);
        p.setName(in.getName()); p.setPrice(in.getPrice()); p.setDescription(in.getDescription());
        if (in.getCategory() != null) p.setCategory(resolveCategory(in.getCategory()));
        return repo.save(p);
    }
    public void delete(Long id) { repo.delete(get(id)); }

    public List<Product> resolveAll(List<Product> refs) {
        if (refs == null || refs.isEmpty()) throw ApiException.badRequest("At least one product is required");
        return refs.stream().map(r -> {
            if (r == null || r.getProductId() == null) throw ApiException.badRequest("products[].productId is required");
            return get(r.getProductId());
        }).collect(Collectors.toList());
    }
    private ProductCategory resolveCategory(ProductCategory ref) {
        if (ref == null) return null;
        if (ref.getCategoryId() == null) throw ApiException.badRequest("category.categoryId is required");
        return categories.get(ref.getCategoryId());
    }
}
