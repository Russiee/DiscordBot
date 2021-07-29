package com.example.demo.listeners.impl;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserExistsException;
import com.example.demo.listeners.UserListener;
import com.example.demo.services.MessagingService;
import com.example.demo.services.UserService;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;

@Component
public class UserListenerImpl implements UserListener {
    @Autowired
    private MessagingService messagingService;
    @Autowired
    private UserService userService;

    private static final String USER_CREATE_TITLE = "User Creator";

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        if(messageCreateEvent.getMessageContent().startsWith("!user")) {
            messageCreateEvent.getChannel().type();
            if(messageCreateEvent.getMessageContent().equalsIgnoreCase("!user create")) {
                //Create the user
                User user;
                try {
                    user = userService.createUser(messageCreateEvent.getMessageAuthor().getIdAsString(), messageCreateEvent.getMessageAuthor().getDisplayName());
                } catch (UserExistsException e) {
                    messagingService.sendMessage(messageCreateEvent.getMessageAuthor(),
                            USER_CREATE_TITLE,
                            e.getMessage(),
                            null,
                            null,
                            messageCreateEvent.getChannel());
                    return;
                }

                //If successful send a message about the great success
                messagingService.sendMessage(messageCreateEvent.getMessageAuthor(),
                        USER_CREATE_TITLE,
                        "User has successfully been created for " + user.getName(),
                        null,
                        "https://i.imgur.com/XJyemeI.jpg",
                        messageCreateEvent.getChannel());
            } else {
                // Send helpful syntax message

                messagingService.sendMessage(messageCreateEvent.getMessageAuthor(),
                        USER_CREATE_TITLE,
                        "Are you trying to use the `!user create` command? Please use the syntax `!user create`. Thanks!",
                        null,
                        null,
                        messageCreateEvent.getChannel(), true);
            }
        }
    }
}
