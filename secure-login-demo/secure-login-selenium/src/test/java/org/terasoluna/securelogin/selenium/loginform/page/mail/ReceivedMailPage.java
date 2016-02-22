package org.terasoluna.securelogin.selenium.loginform.page.mail;

import java.io.IOException;

import org.terasoluna.securelogin.selenium.WebDriverOperations;
import org.terasoluna.securelogin.selenium.loginform.page.AbstractPageObject;

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
		ArrayNode mails = mapper.readValue(
				webDriverOperations.getText(tagName("pre")), ArrayNode.class);
		return mails.get(mails.size() - 1).get("text").asText();
	}
}
