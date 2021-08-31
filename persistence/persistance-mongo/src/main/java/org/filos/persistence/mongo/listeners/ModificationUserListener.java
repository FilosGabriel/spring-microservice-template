package org.filos.persistence.mongo.listeners;

import org.filos.persistence.mongo.model.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ModificationUserListener extends AbstractMongoEventListener<User> {
    private final ApplicationEventPublisher publisher;

    @Override
    public void onAfterDelete(AfterDeleteEvent<User> event) {
        super.onAfterDelete(event);
    }
}
