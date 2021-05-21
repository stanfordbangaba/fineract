/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.infrastructure.core.service;

import org.apache.fineract.infrastructure.configuration.data.SMTPCredentialsData;
import org.apache.fineract.infrastructure.configuration.service.ExternalServicesPropertiesReadPlatformService;
import org.apache.fineract.infrastructure.core.domain.EmailDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GmailBackedPlatformEmailService implements PlatformEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(GmailBackedPlatformEmailService.class);

    private final ExternalServicesPropertiesReadPlatformService externalServicesReadPlatformService;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public GmailBackedPlatformEmailService(final ExternalServicesPropertiesReadPlatformService externalServicesReadPlatformService) {
        this.externalServicesReadPlatformService = externalServicesReadPlatformService;
    }

    @Override
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

    @Override
    public void sendDefinedEmail(EmailDetail emailDetails) {
        final SMTPCredentialsData smtpCredentialsData = this.externalServicesReadPlatformService.getSMTPCredentials();

        LOG.info("SMTP Credentials, username: {}, password: {}, host: {}, port: {}, fromEmail: {}, fromName: {}, useTLS: {}",
                smtpCredentialsData.getUsername(), smtpCredentialsData.getPassword(), smtpCredentialsData.getHost(),
                smtpCredentialsData.getPort(), smtpCredentialsData.getFromEmail(), smtpCredentialsData.getFromName(),
                smtpCredentialsData.isUseTLS());

        final String authuser = smtpCredentialsData.getUsername();
        final String authpwd = smtpCredentialsData.getPassword();

        try {

            emailDetails.setHost(smtpCredentialsData.getHost());
            emailDetails.setMailDebug(false);
            emailDetails.setUsername(authuser);
            emailDetails.setPassword(authpwd);
            emailDetails.setTlsEnable(smtpCredentialsData.isUseTLS());
            emailDetails.setPort(Integer.parseInt(smtpCredentialsData.getPort()));

            final Object result = restTemplate.postForObject("http://localhost:8083/lms/api/v2/notifications/basic-email", emailDetails,
                    Object.class);
            LOG.info("Result");

        } catch (Exception e) {
            throw new PlatformEmailSendException(e);
        }
    }
}
