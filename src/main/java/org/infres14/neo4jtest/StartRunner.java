package org.infres14.neo4jtest;

import lombok.AllArgsConstructor;
import org.infres14.neo4jtest.data.Product;
import org.infres14.neo4jtest.data.User;
import org.infres14.neo4jtest.services.ProductService;
import org.infres14.neo4jtest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class StartRunner implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    private final Random rand = new Random();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userService.dump();
        productService.dump();
        Product product = productService.createProduct("Product 1", "Product 1 description", 100);
        User user = userService.createUser("User 1");
        user.buy(product);
        product.boughtBy(user);
        userService.save(user);
        productService.save(product);

        User user2 = userService.createUser("User 2");
        user2.follow(user);
        user.followedBy(user2);
        userService.saveAll(user, user2);
    }

    private void generateProducts(int count) {
        for (int i = 0; i < count; i++) {
            Product product = new Product("Product " + i, "Description " + i, rand.nextDouble() * 100);
            productService.save(product);
        }
    }

    private void generateUsers(int count) {
        List<Product> products = productService.findAll();
        for (int i = 0; i < count; i++) {
            User user = new User("User " + i);
            // Add random followers
            for (int j = 0; j < rand.nextInt(21); j++) {
                User follower = new User("User Follower " + i + " " + j);
                user.followedBy(follower);
            }
            // Buy random products
            for (int k = 0; k < rand.nextInt(6); k++) {
                user.buy(products.get(rand.nextInt(products.size())));
            }
            userService.save(user);
        }
    }
}
