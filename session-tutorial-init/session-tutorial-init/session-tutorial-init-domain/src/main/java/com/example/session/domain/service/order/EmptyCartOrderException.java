package com.example.session.domain.service.order;

import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

public class EmptyCartOrderException extends BusinessException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyCartOrderException(ResultMessages messages) {
        super(messages);
    }
}
