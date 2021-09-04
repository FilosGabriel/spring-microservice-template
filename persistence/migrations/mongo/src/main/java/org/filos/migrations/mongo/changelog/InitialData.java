package org.filos.migrations.mongo.changelog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.javafaker.Faker;
import com.mongodb.client.MongoDatabase;

@ChangeLog(order = "001")
public class InitialData {
    private final Faker faker = new Faker(new Random(1));

    @ChangeSet(order = "001", author = "Gabriel Filos", id = "add initial data to db")
    public void initialData(MongoDatabase db) {
        var users = db.getCollection("users");
        var userDocuments = createUserDocuments();
        users.insertMany(userDocuments);
    }

    private List<Document> createUserDocuments() {
        return LongStream.range(0, 100)
                         .mapToObj(e -> randomUserDocument())
                         .collect(Collectors.toList());
    }

    private Document randomUserDocument() {
        return new Document().append("id", new ObjectId())
                             .append("_class", "org.filos.persistence.mongo.model.User")
                             .append("first_name", faker.name()
                                                        .firstName())
                             .append("last_name", faker.name()
                                                       .lastName())
                             .append("email", faker.internet()
                                                   .emailAddress())
                             .append("password", "password--encoded")
                             .append("audit", Map.of("creationTime", LocalDateTime.now(), "modificationTime", LocalDateTime.now()));
    }
}
