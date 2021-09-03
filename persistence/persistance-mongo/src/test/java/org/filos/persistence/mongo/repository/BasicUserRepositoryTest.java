package org.filos.persistence.mongo.repository;

import java.util.List;
import java.util.Optional;

import org.filos.persistence.mongo.config.BeanConfig;
import org.filos.persistence.mongo.model.User;
import org.filos.persistence.mongo.model.UserAudit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private BasicUserRepository repository;

    @BeforeEach
    void setup() {
        repository.save(new User( "firstName1", "lastName1", "testemail1@test.com", "password", new UserAudit()));
        repository.save(new User( "firstName2", "lastName2", "testemail2@test.com", "password", new UserAudit()));
        repository.save(new User( "firstName3", "lastName3", "testemail3@test.com", "password", new UserAudit()));
    }

    @AfterEach
    void cleanUp() {
        repository.deleteAll();
    }

    @Test
    void testThatExists3Users() {
        List<User> users = repository.findAll();
        Assertions.assertEquals(users.size(), 3);
    }

    @Test
    void checkThatThePasswordIsHashed() {
        repository.save(new User( "firstName4", "lastName4", "testemail4@test.com", "password", new UserAudit()));
        User user = repository.findByEmail("testemail4@test.com")
                              .get();
        Assertions.assertNotEquals(user.getPassword(), "password");
    }

    @Test
    void testThatFingByEmailFindTheCorrectUser() {
        repository.findAll()
                  .forEach(e -> System.out.println(e.getEmail() + "----" + e.getPassword()));
        Optional<User> users = repository.findByEmail("testemail1@test.com");
        Assertions.assertEquals(users.get()
                                     .getEmail(), "testemail1@test.com");
    }

    @Test
    void testThatFingByEmailShouldnFindAnUserWhenTheEmailIsWrong() {
        Optional<User> users = repository.findByEmail("dsada@gmail.com");
        Assertions.assertTrue(users.isEmpty());
    }

    @Test
    void testThatExistByEmailAndPasswordItCheckThatExists() {
        boolean users = repository.existsByEmailAndPassword("testemail1@test.com", "password");
        Assertions.assertTrue(users);
    }

    @Test
    void testThatExistByEmailItCheckThatExists() {
        boolean users = repository.existsByEmail("testemail1@gmail.com");
        Assertions.assertFalse(users);
    }
}
