package com.example.demo.listeners.impl;

import com.example.demo.listeners.RaceListener;
import com.example.demo.services.MessagingService;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RaceListenerImpl implements RaceListener {
    private static boolean active = false;

    @Autowired
    private MessagingService messagingService;

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        if(messageCreateEvent.getMessageContent().equals("!race")) {
            messageCreateEvent.getChannel().type();
            if(!active) {
                active = true;
                messagingService.sendMessage(messageCreateEvent.getMessageAuthor(),
                        "The race begins!",
                        "Be the first to **react** to this message to win!",
                        null,
                        "https://images.unsplash.com/photo-1457969414820-5fdd86fc0b84?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1048&q=80",
                        messageCreateEvent.getChannel())
                .thenAccept(message -> {
                    message.addReactionAddListener(listener -> {
                        if(active) {
                            message.edit(new EmbedBuilder()
                            .setTitle("The race ends!")
                            .setDescription("Congratulations! **" + listener.getUser().get().getName() + "** was first!\nThe race is now over!")
                            .setFooter("Race again?"));
                            active = false;
                        }
                    });
                });
            }
        }
    }
}
