package org.terasoluna.securelogin.app.mail;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.terasoluna.securelogin.domain.model.ReceivedMail;
import org.terasoluna.securelogin.domain.service.mail.PasswordReissueMailSharedService;

@Controller
public class MailController {
	
	@Inject
	PasswordReissueMailSharedService mailSharedService;
	
	@Inject
	Mapper beanMapper;
	
	@RequestMapping("api/receivedmail")
	@ResponseBody
	public List<ReceivedMailResource> receivedMessages(){
		List<ReceivedMailResource> resources = new ArrayList<>();
		List<ReceivedMail> mails = mailSharedService.getReceivedMessages(); 
		for(ReceivedMail mail : mails){
			resources.add(beanMapper.map(mail, ReceivedMailResource.class));
		}
		return resources;
	}
	
}
