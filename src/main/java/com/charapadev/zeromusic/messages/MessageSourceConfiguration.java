package com.charapadev.zeromusic.messages;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class MessageSourceConfiguration {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource newSource = new ReloadableResourceBundleMessageSource();

        newSource.setBasename("classpath:messages");
        newSource.setDefaultEncoding("UTF-8");

        return newSource;
    }
}
