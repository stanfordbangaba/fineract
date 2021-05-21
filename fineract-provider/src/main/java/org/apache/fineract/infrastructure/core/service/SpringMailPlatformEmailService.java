package org.apache.fineract.infrastructure.core.service;

import org.apache.fineract.infrastructure.configuration.data.SMTPCredentialsData;
import org.apache.fineract.infrastructure.configuration.service.ExternalServicesPropertiesReadPlatformService;
import org.apache.fineract.infrastructure.core.domain.EmailDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SpringMailPlatformEmailService {

    // @Autowired
    // private JavaMailSender emailSender;
    @Autowired
    private ExternalServicesPropertiesReadPlatformService externalServicesReadPlatformService;

    // @Override
    public void sendToUserAccount(String organisationName, String contactName, String address, String username, String unencodedPassword) {
        final String subject = "Welcome " + contactName + " to " + organisationName;
        final String body = "You are receiving this email as your email account: " + address
                + " has being used to create a user account for an organisation named [" + organisationName + "] on LMS.\n"
                + "You can login using the following credentials:\nusername: " + username + "\n" + "password: " + unencodedPassword + "\n"
                + "You must change this password upon first log in using Uppercase, Lowercase, number and character.\n"
                + "Thank you and welcome to the organisation.";

        final EmailDetail emailDetail = new EmailDetail(subject, body, address, contactName);
        sendDefinedEmail(emailDetail);
    }

    // @Override
    public void sendDefinedEmail(EmailDetail emailDetails) {

        final SMTPCredentialsData smtpCredentialsData = this.externalServicesReadPlatformService.getSMTPCredentials();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(smtpCredentialsData.getFromEmail());
        message.setTo(emailDetails.getAddress());
        message.setSubject(emailDetails.getSubject());
        message.setText(emailDetails.getBody());
        // emailSender.send(message);

    }
}
