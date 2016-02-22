package org.terasoluna.securelogin.app.mail;

import lombok.Data;

@Data
public class ReceivedMailResource {
	private String from;
	private String to;
	private String subject;
	private String text;
}
