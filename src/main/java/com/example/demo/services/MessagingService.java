package com.example.demo.services;

import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;

import java.util.concurrent.CompletableFuture;

public interface MessagingService {

    CompletableFuture<Message> sendMessage(MessageAuthor author, String title, String description, String footer, String thumbnail, TextChannel channel);
    void sendMessage(MessageAuthor author, String title, String description, String footer, String thumbnail, TextChannel textChannel, boolean withDelete);
}
