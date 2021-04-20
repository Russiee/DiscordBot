package com.example.demo.listeners.impl;

import com.example.demo.listeners.DeleteReactionListener;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.springframework.stereotype.Component;

@Component
public class DeleteReactionListenerImpl implements DeleteReactionListener {

    @Override
    public void onReactionAdd(ReactionAddEvent reactionAddEvent) {
        if(reactionAddEvent.getEmoji().equalsEmoji("\uD83D\uDC4E")) {
            reactionAddEvent.deleteMessage();
        }
    }
}
