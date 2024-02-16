package org.infres14.neo4jtest.repos;

import org.infres14.neo4jtest.data.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends Neo4jRepository<User, Long> {
}
