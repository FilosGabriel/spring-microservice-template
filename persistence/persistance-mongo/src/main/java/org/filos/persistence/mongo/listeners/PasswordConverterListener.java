package org.filos.persistence.mongo.listeners;

import java.util.Optional;

import org.filos.persistence.mongo.converter.PasswordConverter;
import org.filos.persistence.mongo.model.User;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class PasswordConverterListener extends AbstractMongoEventListener<User> {
    private final PasswordConverter passwordConverter;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<User> event) {
        super.onBeforeConvert(event);
        Option.of(event.getSource())
                .filter(this::isNewUser)
                .peek(this::hashThePasswordOfTheUser);
    }

    private void hashThePasswordOfTheUser(User user) {
        log.debug("Hashing password of the user {}.", user.getEmail());
        String hashedPassword = passwordConverter.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
    }

    private boolean isNewUser(User user) {
        return user.getId() == null;
    }
}
