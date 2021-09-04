package org.filos.persistence.mongo.config;

import org.filos.persistence.mongo.converter.PasswordConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
@ComponentScan(value = "org.filos.persistence.mongo")
public class BeanConfig {

    @Bean
    public PasswordConverter initConverter() {
        return e -> e + "-encoded";
    }
}
