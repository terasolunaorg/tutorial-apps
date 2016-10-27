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

		assertThat(webDriver.findElement(By.xpath("/html/body")).getText(),
				is(containsString("Todo List")));
	}

	/**
	 * Asserts that one todo is created.
	 */
	@Test
	public void testCreate() throws IOException {

		webDriver.findElement(By.id("todoTitle")).sendKeys("todoThings1");
		webDriver.findElement(
				By.xpath("//form[@action='/" + contextName
						+ "/todo/create']/button")).click();

		WebElement todoElement = webDriver.findElement(By
				.xpath("//div[@id='todoList']/ul/li"));
		assertThat(
				webDriver.findElement(
						By.xpath("//div[@class='alert alert-success']/ul/li"))
						.getText(), is("Created successfully!"));

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

		webDriver.findElement(By.id("todoTitle")).sendKeys("todoThings1");
		webDriver.findElement(
				By.xpath("//form[@action='/" + contextName
						+ "/todo/create']/button")).click();
		webDriver.findElement(
				By.xpath("//div[@id='todoList']/ul/li/form[@action='/"
						+ contextName + "/todo/finish']/button")).click();

		WebElement todoElement = webDriver.findElement(By
				.xpath("//div[@id='todoList']/ul/li"));
		assertThat(
				webDriver.findElement(
						By.xpath("//div[@class='alert alert-success']/ul/li"))
						.getText(), is("Finished successfully!"));
		assertThat(todoElement
				.findElement(By.xpath("//span[@class='strike']")).getText(),
				is("todoThings1"));

		todoElement.findElement(
				By.xpath("//form[@action='/" + contextName
						+ "/todo/delete']/button")).click();
	}

	/**
	 * Asserts that one todo is deleted.
	 */
	@Test
	public void testDelete() throws IOException {

		webDriver.findElement(By.id("todoTitle")).sendKeys("todoThings1");
		webDriver.findElement(
				By.xpath("//form[@action='/" + contextName
						+ "/todo/create']/button")).click();
		webDriver.findElement(
				By.xpath("//div[@id='todoList']/ul/li/form[@action='/"
						+ contextName + "/todo/delete']/button")).click();

		assertThat(
				webDriver.findElement(
						By.xpath("//div[@class='alert alert-success']/ul/li"))
						.getText(), is("Deleted successfully!"));

		assertThat(webDriver.findElement(By.xpath("//div[@id='todoList']/ul"))
				.getText(), is(""));
	}

	/**
	 * Asserts that error message is showed. size must be between 1 and 30
	 */
	@Test
	public void testCreateEmpty() throws IOException {

		webDriver.findElement(
				By.xpath("//form[@action='/" + contextName
						+ "/todo/create']/button")).click();

		assertThat(
				webDriver.findElement(
						By.xpath("//span[@id='todoTitle.errors']")).getText(),
				is("size must be between 1 and 30"));
	}

	/**
	 * Asserts that error message is showed. size must be between 1 and 30
	 */
	@Test
	public void testCreateOverSize() throws IOException {

		webDriver.findElement(By.id("todoTitle")).sendKeys(
				"0123456789012345678901234567890");
		webDriver.findElement(
				By.xpath("//form[@action='/" + contextName
						+ "/todo/create']/button")).click();

		assertThat(
				webDriver.findElement(
						By.xpath("//span[@id='todoTitle.errors']")).getText(),
				is("size must be between 1 and 30"));
	}

	/**
	 * Asserts that error message is showed. The count of un-finished Todo must
	 * not be over 5.
	 */
	@Test
	public void testUnfinishedSize() throws IOException {

		for (int i = 0; i < 6; i++) {
			webDriver.findElement(By.id("todoTitle")).sendKeys(
					Integer.toString(i));
			webDriver.findElement(
					By.xpath("//form[@action='/" + contextName
							+ "/todo/create']/button")).click();
		}

		assertThat(
				webDriver.findElement(
						By.xpath("//div[@id='todoForm']/div/ul/li")).getText(),
				is("[E001] The count of un-finished Todo must not be over 5."));

		for (int i = 0; i < 5; i++) {
			webDriver.findElement(
					By.xpath("//div[@id='todoList']/ul/li/form[@action='/"
							+ contextName + "/todo/delete']/button")).click();
		}

	}

}
