package org.infres14.neo4jtest.services;

import org.infres14.neo4jtest.data.Product;
import org.infres14.neo4jtest.data.User;
import org.infres14.neo4jtest.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public UserService() {
    }

    public User createUser(String s) {
        User user = new User(s);
        repo.save(user);
        return user;
    }

    public void buyProduct(User user, Product product) {
        user.buy(product);
        repo.save(user);
    }

    public void dump() {
        repo.deleteAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public void saveAll(List<User> users) {
        repo.saveAll(users);
    }
}
