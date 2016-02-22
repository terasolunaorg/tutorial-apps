package com.example.session.app.account;

import org.terasoluna.gfw.common.exception.ResultMessagesNotificationException;
import org.terasoluna.gfw.common.message.ResultMessages;

public class IllegalOperationException extends
		ResultMessagesNotificationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalOperationException(ResultMessages messages) {
		super(messages);
	}
}
