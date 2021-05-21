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
package org.apache.fineract.infrastructure.core.domain;

public class EmailDetail {

    private final String subject;
    private final String body;
    private final String address;
    private final String contactName;
    private String host;
    private String username;
    private String password;
    private Integer port;

    private Boolean tlsEnable;
    private Boolean smtpAuth;
    private Boolean mailDebug;
    private String transportProtocol;

    public EmailDetail(final String subject, final String body, final String address, final String contactName) {
        this.subject = subject;
        this.body = body;
        this.address = address;
        this.contactName = contactName;
    }

    public EmailDetail(String subject, String body, String address, String contactName, String host, String username, String password,
            Integer port, Boolean tlsEnable, Boolean smtpAuth, Boolean mailDebug, String transportProtocol) {
        this.subject = subject;
        this.body = body;
        this.address = address;
        this.contactName = contactName;
        this.host = host;
        this.username = username;
        this.password = password;
        this.port = port;
        this.tlsEnable = tlsEnable;
        this.smtpAuth = smtpAuth;
        this.mailDebug = mailDebug;
        this.transportProtocol = transportProtocol;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setTlsEnable(Boolean tlsEnable) {
        this.tlsEnable = tlsEnable;
    }

    public void setSmtpAuth(Boolean smtpAuth) {
        this.smtpAuth = smtpAuth;
    }

    public void setMailDebug(Boolean mailDebug) {
        this.mailDebug = mailDebug;
    }

    public void setTransportProtocol(String transportProtocol) {
        this.transportProtocol = transportProtocol;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getContactName() {
        return this.contactName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getHost() {
        return host;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getPort() {
        return port;
    }

    public Boolean getTlsEnable() {
        return tlsEnable;
    }

    public Boolean getSmtpAuth() {
        return smtpAuth;
    }

    public Boolean getMailDebug() {
        return mailDebug;
    }

    public String getTransportProtocol() {
        return transportProtocol;
    }
}
