package org.filos.persistence.mongo.repository;

import java.util.Optional;

import org.filos.persistence.mongo.model.User;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicUserRepository extends MongoRepository<User, String> {
    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @ExistsQuery(value = "{email:?0,password:?1}")
    boolean existsByEmailAndPassword(String email, String password);
}
