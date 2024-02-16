package org.infres14.neo4jtest.data;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node
public class User {

    @Id @GeneratedValue private Long id;
    private String name;
    @Relationship(type = "BOUGHT", direction = Relationship.Direction.OUTGOING)
    private Set<Product> boughtProducts;

    @Relationship(type = "FOLLOWERS", direction = Relationship.Direction.INCOMING)
    private Set<User> followers;

    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.OUTGOING)
    private Set<User> follows;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

}
