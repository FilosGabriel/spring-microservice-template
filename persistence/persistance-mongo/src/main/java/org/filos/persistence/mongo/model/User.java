package org.filos.persistence.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
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

}
