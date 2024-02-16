package org.infres14.neo4jtest.nosql.repos;

import org.infres14.neo4jtest.nosql.data.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends Neo4jRepository<User, Long> {
}
