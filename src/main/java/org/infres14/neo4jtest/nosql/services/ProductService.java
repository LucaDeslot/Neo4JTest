package org.infres14.neo4jtest.nosql.services;

import org.infres14.neo4jtest.nosql.data.Product;
import org.infres14.neo4jtest.nosql.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public ProductService() {
    }

    public Product createProduct(String s, String description, long price) {
        Product product = new Product(s, description, price);
        repo.save(product);
        return product;
    }

    public void dump() {
        this.repo.deleteAll();
    }

    public Product getProductByName(String s) {
        return this.repo.findByName(s);
    }

    public void save(Product product) {
        this.repo.save(product);
    }

    public List<Product> findAll() {
        return this.repo.findAll();
    }
}
