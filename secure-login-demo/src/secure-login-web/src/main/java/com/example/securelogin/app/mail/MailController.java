/*
 * Copyright(c) 2013 NTT DATA Corporation. Copyright(c) 2013 NTT Corporation.
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
package com.example.securelogin.app.mail;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.github.dozermapper.core.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.securelogin.domain.model.ReceivedMail;
import com.example.securelogin.domain.service.mail.PasswordReissueMailSharedService;

@RestController
@RequestMapping("api/receivedmail")
public class MailController {
    
    @Inject
    PasswordReissueMailSharedService mailSharedService;
    
    @Inject
    Mapper beanMapper;
    
    @GetMapping
    public List<ReceivedMailResource> receivedMessages(){
        List<ReceivedMailResource> resources = new ArrayList<>();
        List<ReceivedMail> mails = mailSharedService.getReceivedMessages(); 
        for(ReceivedMail mail : mails){
            resources.add(beanMapper.map(mail, ReceivedMailResource.class));
        }
        return resources;
    }
    
}
