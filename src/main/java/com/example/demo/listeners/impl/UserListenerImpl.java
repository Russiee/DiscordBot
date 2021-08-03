package com.example.demo.listeners.impl;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserExistsException;
import com.example.demo.listeners.UserListener;
import com.example.demo.services.MessagingService;
import com.example.demo.services.UserService;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserListenerImpl implements UserListener {
    @Autowired
    private MessagingService messagingService;
    @Autowired
    private UserService userService;

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        if(messageCreateEvent.getMessageContent().startsWith("!user")) {
            messageCreateEvent.getChannel().type();
            if(messageCreateEvent.getMessageContent().equalsIgnoreCase("!user create")) {
                // Create the user
                User user;
                try {
                    user = userService.createUser(messageCreateEvent.getMessageAuthor().getIdAsString(), messageCreateEvent.getMessageAuthor().getDisplayName());
                } catch (UserExistsException e) {
                    messagingService.sendMessage(messageCreateEvent.getMessageAuthor(),
                            "User Creator",
                            e.getMessage(),
                            null,
                            null,
                            messageCreateEvent.getChannel());
                    return;
                }

                //If successful send a message about the great success
                messagingService.sendMessage(messageCreateEvent.getMessageAuthor(),
                        "User Creator",
                        "User has successfully been created for " + user.getName(),
                        null,
                        "https://i.imgur.com/XJyemeI.jpg",
                        messageCreateEvent.getChannel());
            } else {
                messagingService.sendMessage(messageCreateEvent.getMessageAuthor(),
                        "User Creator",
                        "Are you trying to use the `!user create` command? Please use the syntax `!user create`. Thanks!",
                        null,
                        null,
                        messageCreateEvent.getChannel());
            }
        }
    }
}
