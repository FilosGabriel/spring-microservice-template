package org.filos.migrations.mongo;

import com.github.cloudyrock.mongock.driver.mongodb.sync.v4.driver.MongoSync4Driver;
import com.github.cloudyrock.standalone.MongockStandalone;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoMigrationRunner {
    public void init(String connection) {
        MongoClient mongoClient = MongoClients.create(connection);
        MongockStandalone.builder()
                         .setDriver(MongoSync4Driver.withDefaultLock(mongoClient, "test"))
                         .addChangeLogsScanPackage("org.filos.migrations.mongo.changelog")
                         .buildRunner().execute();
    }
}
