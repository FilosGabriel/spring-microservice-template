package org.filos.persistence.mongo.listeners;

import org.filos.persistence.mongo.converter.PasswordConverter;
import org.filos.persistence.mongo.model.User;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class PasswordConverterListener extends AbstractMongoEventListener<User> {
    private final PasswordConverter passwordConverter;

    @Override
    public void onBeforeSave(BeforeSaveEvent<User> event) {
        User user = event.getSource();
        if (isANewUser(user)) {
            log.debug("Hashing password of the user {}.", user.getEmail());
            String hashedPassword = passwordConverter.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
        }
        super.onBeforeSave(event);
    }

    private boolean isANewUser(User user) {
        return user.getId() == null;
    }
}
