package org.filos.persistence.mongo.converter;

public interface PasswordConverter {
    String hashPassword(String password);
}
