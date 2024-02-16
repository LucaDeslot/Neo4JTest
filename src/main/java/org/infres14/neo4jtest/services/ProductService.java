package org.infres14.neo4jtest.services;

import org.infres14.neo4jtest.data.Product;
import org.infres14.neo4jtest.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public ProductService() {
    }

    public void createProduct(String s, String description, long price) {
        repo.save(new Product(s, description, price));
    }
}
