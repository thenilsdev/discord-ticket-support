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

package io.nilsdev.discordticketsupport.bot.command;

import com.github.kaktushose.jda.commands.entities.CommandCallable;
import com.github.kaktushose.jda.commands.entities.CommandList;
import com.github.kaktushose.jda.commands.entities.CommandSettings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.List;

public class EmbedFactory extends com.github.kaktushose.jda.commands.api.EmbedFactory {

    public MessageEmbed getDefaultHelpEmbed(@Nonnull CommandList commands, @Nonnull CommandSettings settings, @Nonnull GuildMessageReceivedEvent event) {
        return HelpMessageSender.getMessageEmbed();
    }

    public MessageEmbed getSpecificHelpEmbed(@Nonnull CommandCallable commandCallable, @Nonnull CommandSettings settings, @Nonnull GuildMessageReceivedEvent event) {
        return HelpMessageSender.getMessageEmbed();
    }

    public MessageEmbed getCommandNotFoundEmbed(@Nonnull CommandSettings settings, @Nonnull GuildMessageReceivedEvent event) {
        return new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle("Unbekannter Befehl")
                .setDescription(event.getAuthor().getAsMention() + ", benutze `" + settings.getGuildPrefix(event.getGuild()) + "help` oder `@" + event.getJDA().getSelfUser().getAsTag() + " help` um die Liste aller Befehle anzuzeigen.")
                .build();
    }

    public MessageEmbed getInsufficientPermissionsEmbed(@Nonnull CommandCallable commandCallable, @Nonnull CommandSettings settings, @Nonnull GuildMessageReceivedEvent event) {
        return new EmbedBuilder().setDescription("").build();
    }

    public MessageEmbed getSyntaxErrorEmbed(@Nonnull CommandCallable commandCallable,
                                            @Nonnull List<String> arguments,
                                            @Nonnull CommandSettings settings,
                                            @Nonnull GuildMessageReceivedEvent event) {
        return HelpMessageSender.getMessageEmbed();
    }
}
