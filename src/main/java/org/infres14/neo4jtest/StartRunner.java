package org.infres14.neo4jtest;

import lombok.AllArgsConstructor;
import org.infres14.neo4jtest.services.ProductService;
import org.infres14.neo4jtest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StartRunner implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        productService.createProduct("Product 1", "Product 1 description", 100);
        userService.createUser("User 1");
    }
}
