/*
 * Copyright (C) 2013-2017 NTT DATA Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.example.securelogin.domain.service.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.terasoluna.gfw.common.exception.SystemException;
import com.example.securelogin.domain.common.message.MessageKeys;
import com.example.securelogin.domain.model.ReceivedMail;

import com.icegreen.greenmail.spring.GreenMailBean;

@Service
public class PasswordReissueMailSharedServiceImpl implements
		PasswordReissueMailSharedService {

	@Inject
	JavaMailSender mailSender;

	@Inject
	@Named("passwordReissueMessage")
	SimpleMailMessage templateMessage;

	@Inject
	GreenMailBean greenMailBean;

	@Override
	public void send(String to, String text) {
		SimpleMailMessage message = new SimpleMailMessage(templateMessage);
		message.setTo(to);
		message.setText(text);
		mailSender.send(message);
	}

	@Override
	public List<ReceivedMail> getReceivedMessages() {
		List<ReceivedMail> mails = new ArrayList<>();
		MimeMessage[] messages = greenMailBean.getReceivedMessages();
		for (MimeMessage message : messages) {
			ReceivedMail mail = new ReceivedMail();
			try {
				mail.setTo(message.getFrom()[0].toString());
				mail.setFrom(message.getRecipients(Message.RecipientType.TO)[0]
						.toString());
				mail.setSubject(message.getSubject());
				mail.setText(message.getContent().toString()
						.replace("\r\n", ""));
				mails.add(mail);
			} catch (MessagingException e) {
				throw new SystemException(MessageKeys.E_SL_FW_9001, e);
			} catch (IOException e) {
				throw new SystemException(MessageKeys.E_SL_FW_9001, e);
			}

		}
		return mails;
	}

}
