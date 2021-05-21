package org.apache.fineract.notification.config;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailMailerConfig {

    // @Autowired
    // private ExternalServicesPropertiesReadPlatformService externalServicesReadPlatformService;

    @Bean
    public JavaMailSender emailSender() {
        // final SMTPCredentialsData smtpCredentialsData =
        // this.externalServicesReadPlatformService.getSMTPCredentials();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(Integer.parseInt("587"));

        mailSender.setUsername("cash22team@gmail.com");
        mailSender.setPassword("PassworD123");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
