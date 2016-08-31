package org.terasoluna.securelogin.domain.service.mail;

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
import org.terasoluna.securelogin.domain.common.message.MessageKeys;
import org.terasoluna.securelogin.domain.model.ReceivedMail;

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
