package org.filos.persistence.mongo.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    @MongoId
    private ObjectId id;
    @Field("first_name")
    private String firstName;
    @Field("last_name")
    private String lastName;
    @Field("email")
    @Indexed(unique = true)
    private String email;
    @Field("password")
    private String password;
    private UserAudit audit;

    public User(String firstName, String lastName, String email, String password, UserAudit audit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.audit = audit;
    }
}
