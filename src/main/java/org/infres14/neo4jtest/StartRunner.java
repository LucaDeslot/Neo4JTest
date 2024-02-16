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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

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
//        Product product = productService.createProduct("Product 1", "Product 1 description", 100);
//        User user = userService.createUser("User 1");
//        user.buy(product);
//        product.boughtBy(user);
//        userService.save(user);
//        productService.save(product);
//
//        User user2 = userService.createUser("User 2");
//        user2.follow(user);
//        user.followedBy(user2);
//        userService.saveAll(user, user2);
        generateProducts(5);
        generateUsers(4);
    }

    private void generateProducts(int count) {
        for (int i = 0; i < count; i++) {
            Product product = new Product("Product " + i, "Description " + i, rand.nextDouble() * 100);
            productService.save(product);
        }
    }

    private void generateUsers(int count) {
        List<Product> products = productService.findAll();
        List<User> users = new ArrayList<>();

        // Créer les utilisateurs
        for (int i = 0; i < count; i++) {
            User user = new User("User " + i);
            users.add(user);
        }

        // Assigner les followers et les produits de façon aléatoire
        users.forEach(user -> {
            // Acheter des produits aléatoires
            IntStream.range(0, rand.nextInt(6)).forEach(k -> user.buy(products.get(rand.nextInt(products.size()))));

            // Suivre des utilisateurs aléatoires
            IntStream.range(0, rand.nextInt(21)).forEach(j -> {
                User userToFollow = users.get(rand.nextInt(users.size()));
                // Éviter que l'utilisateur se suive lui-même
                if (!user.equals(userToFollow)) {
                    if(userToFollow.getFollowerCount() < 20) {
                        user.follow(userToFollow);
                        userToFollow.followedBy(user);
                        }
                }
            });
        });

        // Sauvegarder les utilisateurs
        userService.saveAll(users);
    }
}
