/*
 * Copyright (c) 2020 thenilsdev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 */

package io.nilsdev.discordticketsupport.common.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import io.nilsdev.discordticketsupport.common.Constants;
import io.nilsdev.discordticketsupport.common.config.Config;
import io.nilsdev.discordticketsupport.common.providers.MorphiaProvider;
import xyz.morphia.Datastore;
import xyz.morphia.Morphia;

import java.util.Collections;

public class CommonModule extends AbstractModule {

    private final Config config;

    public CommonModule(Config config) {
        this.config = config;
    }

    @Override
    protected void configure() {
        this.bind(Morphia.class).toProvider(MorphiaProvider.class).asEagerSingleton();
    }

    @Provides
    public MongoClient provideMongoClient() {
        ServerAddress serverAddress = new ServerAddress(this.config.getDatabaseHost(), this.config.getDatabasePort());

        MongoCredential credential = MongoCredential.createCredential(this.config.getDatabaseUser(), this.config.getDatabaseName(), this.config.getDatabasePassword().toCharArray());

        return new MongoClient(serverAddress, Collections.singletonList(credential));
    }

    @Provides
    @Named(Constants.DATASTORE)
    public Datastore provideDatastore(MongoClient mongoClient, Morphia morphia) {
        Datastore datastore = morphia.createDatastore(mongoClient, this.config.getDatabaseName());
        datastore.ensureIndexes();

        return datastore;
    }

    @Provides
    public Config provideConfig() {
        return this.config;
    }
}
