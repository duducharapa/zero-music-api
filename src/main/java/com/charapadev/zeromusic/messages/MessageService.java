package com.charapadev.zeromusic.messages;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageSource messageSource;

    public String resolve(Message message) throws NoSuchMessageException {
        return messageSource.getMessage(
            message.getProperty(),
            null,
            Locale.getDefault()
        );
    }
}
