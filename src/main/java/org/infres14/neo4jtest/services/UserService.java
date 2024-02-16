package org.infres14.neo4jtest.services;

import org.infres14.neo4jtest.data.User;
import org.infres14.neo4jtest.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public UserService() {
    }

    public void createUser(String s) {
        repo.save(new User("name"));
    }
}
