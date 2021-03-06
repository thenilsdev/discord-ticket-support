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

package io.nilsdev.discordticketsupport.bot.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.jul.LevelTranslator;
import org.apache.logging.log4j.message.MessageFormatMessage;

import java.util.Map;
import java.util.MissingResourceException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Log4JLogHandler extends Handler {
    private final Map<String, Logger> cache = new ConcurrentHashMap();


    public void publish(LogRecord record) {
        if (!this.isLoggable(record)) {
            return;
        }

        Logger logger = this.cache.computeIfAbsent((record.getLoggerName() == null) ? "" : record.getLoggerName(), LogManager::getLogger);

        String message = record.getMessage();
        if (record.getResourceBundle() != null) {
            try {
                message = record.getResourceBundle().getString(message);
            } catch (MissingResourceException ignored) {
            }
        }


        Level level = LevelTranslator.toLevel(record.getLevel());
        if (record.getParameters() != null && record.getParameters().length > 0) {
            logger.log(level, new MessageFormatMessage(message, record.getParameters()), record.getThrown());
        } else {
            logger.log(level, message, record.getThrown());
        }
    }

    public void flush() {
    }

    public void close() {
    }
}
