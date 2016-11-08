package todo.selenium.todo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;

import todo.selenium.FunctionTestSupport;
public class TodoTest extends FunctionTestSupport {

	private static WebDriver webDriver;

	@Value("${selenium.contextName}")
	String contextName;
	
	public TodoTest(){
		super.disableDefaultWebDriver();
	}

	@Before
	public void testBefore() {
		if (webDriver == null) {
            webDriver = webDriverCreator
                    .createLocaleSpecifiedDriver("");
        }
        super.setCurrentWebDriver(webDriver);
	}

	/**
	 * Asserts that the content of the application entry page is "Todo List".
	 */
	@Test
	public void testList() throws IOException {

		assertThat(webDriverOperations.getText(By.xpath("/html/body")),
				is(containsString("Todo List")));
	}

	/**
	 * Asserts that one todo is created.
	 */
	@Test
	public void testCreate() throws IOException {

		webDriverOperations.overrideText(By.id("todoTitle"), "todoThings1");
		webDriverOperations.click(By.xpath("//form[@action='/" + contextName
				+ "/todo/create']/button"));

		WebElement todoElement = webDriver.findElement(By
				.xpath("//div[@id='todoList']/ul/li"));
		assertThat(webDriverOperations.getText(By
				.xpath("//div[@class='alert alert-success']/ul/li")),
				is("Created successfully!"));

		assertThat(todoElement.getText(), is(containsString("todoThings1")));
		todoElement.findElement(
				By.xpath("//form[@action='/" + contextName
						+ "/todo/delete']/button")).click();
	}

	/**
	 * Asserts that one todo is finished.
	 */
	@Test
	public void testFinish() throws IOException {

		webDriverOperations.overrideText(By.id("todoTitle"), "todoThings1");
		webDriverOperations.click(By.xpath("//form[@action='/" + contextName
				+ "/todo/create']/button"));
		webDriverOperations.click(By
				.xpath("//div[@id='todoList']/ul/li/form[@action='/"
						+ contextName + "/todo/finish']/button"));

		WebElement todoElement = webDriver.findElement(By
				.xpath("//div[@id='todoList']/ul/li"));
		assertThat(webDriverOperations.getText(By
				.xpath("//div[@class='alert alert-success']/ul/li")),
				is("Finished successfully!"));
		assertThat(todoElement.findElement(By.xpath("//span[@class='strike']"))
				.getText(), is("todoThings1"));

		todoElement.findElement(
				By.xpath("//form[@action='/" + contextName
						+ "/todo/delete']/button")).click();
	}

	/**
	 * Asserts that one todo is deleted.
	 */
	@Test
	public void testDelete() throws IOException {

		webDriverOperations.overrideText(By.id("todoTitle"), "todoThings1");
		webDriverOperations.click(By.xpath("//form[@action='/" + contextName
				+ "/todo/create']/button"));
		webDriverOperations.click(By
				.xpath("//div[@id='todoList']/ul/li/form[@action='/"
						+ contextName + "/todo/delete']/button"));

		assertThat(webDriverOperations.getText(By
				.xpath("//div[@class='alert alert-success']/ul/li")),
				is("Deleted successfully!"));

		assertThat(webDriverOperations.getText(By
				.xpath("//div[@id='todoList']/ul")), is(""));
	}

	/**
	 * Asserts that error message is showed. size must be between 1 and 30
	 */
	@Test
	public void testCreateEmpty() throws IOException {

		webDriverOperations.click(By.xpath("//form[@action='/" + contextName
				+ "/todo/create']/button"));

		assertThat(webDriverOperations.getText(By
				.xpath("//span[@id='todoTitle.errors']")),
				is("size must be between 1 and 30"));
	}

	/**
	 * Asserts that error message is showed. size must be between 1 and 30
	 */
	@Test
	public void testCreateOverSize() throws IOException {

		webDriverOperations.overrideText(By.id("todoTitle"),
				"0123456789012345678901234567890");
		webDriverOperations.click(By.xpath("//form[@action='/" + contextName
				+ "/todo/create']/button"));

		assertThat(webDriverOperations.getText(By
				.xpath("//span[@id='todoTitle.errors']")),
				is("size must be between 1 and 30"));
	}

	/**
	 * Asserts that error message is showed. The count of un-finished Todo must
	 * not be over 5.
	 */
	@Test
	public void testUnfinishedSize() throws IOException {

		for (int i = 0; i < 6; i++) {
			webDriverOperations.overrideText(By.id("todoTitle"),
					Integer.toString(i));
			webDriverOperations.click(By.xpath("//form[@action='/"
					+ contextName + "/todo/create']/button"));
		}

		assertThat(webDriverOperations.getText(By
				.xpath("//div[@id='todoForm']/div/ul/li")),
				is("[E001] The count of un-finished Todo must not be over 5."));

		for (int i = 0; i < 5; i++) {
			webDriverOperations.click(By
					.xpath("//div[@id='todoList']/ul/li/form[@action='/"
							+ contextName + "/todo/delete']/button"));
		}

	}

}
