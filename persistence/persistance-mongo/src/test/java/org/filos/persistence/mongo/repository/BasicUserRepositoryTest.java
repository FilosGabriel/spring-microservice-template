package org.filos.persistence.mongo.repository;

import java.util.List;
import java.util.Optional;

import org.filos.migrations.mongo.MongoMigrationRunner;
import org.filos.persistence.mongo.config.BeanConfig;
import org.filos.persistence.mongo.model.User;
import org.filos.persistence.mongo.model.UserAudit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@Import(BeanConfig.class)
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class BasicUserRepositoryTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
    static MongoMigrationRunner runner = new MongoMigrationRunner();

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeAll
    static void setup() {
        runner.init(mongoDBContainer.getReplicaSetUrl());
    }

    @Autowired
    private BasicUserRepository repository;



    @Test
    void testThatExists3Users() {
        List<User> users = repository.findAll();
        Assertions.assertEquals(users.size(), 100);
    }

    @Test
    void checkThatThePasswordIsHashed() {
        repository.save(new User("firstName4", "lastName4", "testemail4@test.com", "password", new UserAudit()));
        User user = repository.findByEmail("testemail4@test.com")
                              .get();
        Assertions.assertNotEquals(user.getPassword(), "password");
    }

    @Test
    void testThatFindByEmailFindTheCorrectUser() {
        Optional<User> users = repository.findByEmail("kian.waters@hotmail.com");
        Assertions.assertEquals(users.get()
                                     .getEmail(), "kian.waters@hotmail.com");
    }

    @Test
    void testThatFingByEmailShouldnFindAnUserWhenTheEmailIsWrong() {
        Optional<User> users = repository.findByEmail("dsada@gmail.com");
        Assertions.assertTrue(users.isEmpty());
    }

    @Test
    void testThatExistByEmailAndPasswordItCheckThatExists() {
        boolean users = repository.existsByEmailAndPassword("kian.waters@hotmail.com", "password--encoded");
        Assertions.assertTrue(users);
    }

    @Test
    void testThatExistByEmailItCheckThatExists() {
        boolean users = repository.existsByEmail("kian.waters@hotmail.com");
        Assertions.assertTrue(users);
    }
}
