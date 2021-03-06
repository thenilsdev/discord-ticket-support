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

package io.nilsdev.discordticketsupport.bot.utils;

import io.nilsdev.discordticketsupport.bot.Bot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class VersionUtil {

    public static String getVersion() {
        String version = "Unknown";

        try (InputStream inputStream = Bot.class.getClassLoader().getResourceAsStream("git.properties")) {
            Properties properties = new Properties();

            properties.load(inputStream);

            version = properties.getProperty("git.build.version");
        } catch (Throwable ignored) {
        }
        return version;
    }
}
