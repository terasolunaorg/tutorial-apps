/*
 * Copyright (C) 2018 NTT DATA Corporation. Copyright (C) 2018 NTT Corporation.
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
package com.example.securelogin.selenium.loginform.page.mail;

import java.io.IOException;

import org.terasoluna.gfw.tutorial.selenium.WebDriverOperations;
import com.example.securelogin.selenium.loginform.page.AbstractPageObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import static org.openqa.selenium.By.tagName;

public class ReceivedMailPage extends AbstractPageObject {

    private static final ObjectMapper mapper = new ObjectMapper();

    {
        url = "/api/receivedmail";
    }

    public ReceivedMailPage(WebDriverOperations webDriverOperations,
            String applicationContextURL) {
        super(webDriverOperations, applicationContextURL);
    }

    public String getLatestMailText() throws JsonParseException, JsonMappingException, IOException {
        ArrayNode mails = mapper.readValue(webDriverOperations.getText(tagName(
                "pre")), ArrayNode.class);
        return mails.get(mails.size() - 1).get("text").asText();
    }
}
