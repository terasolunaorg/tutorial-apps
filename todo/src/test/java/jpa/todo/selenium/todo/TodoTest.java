package todo.selenium.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/seleniumContext.xml" })
public class TodoTest {

	@Inject
	WebDriver webDriver;

	@Value("${selenium.applicationContextUrl}/todo/list")
	String applicationContextUrl;

	/**
	 * Asserts that the content of the application entry page is "Todo List".
	 */
	@Test
	public void testList() throws IOException {

		webDriver.get(applicationContextUrl);

		assertThat(webDriver.findElement(By.xpath("/html/body/h1")).getText(), is("Todo List"));
	}

	/**
	 * Asserts that one todo is created.
	 */
	@Test
	public void testCreate() throws IOException {

		webDriver.get(applicationContextUrl);
		webDriver.findElement(By.id("todoTitle")).sendKeys("todoThings1");
		webDriver
				.findElement(
						By.xpath("//form[@action='/todo-jpa/todo/create']/button"))
				.click();

		assertThat(
				webDriver.findElement(
						By.xpath("//div[@class='alert alert-success']/ul/li"))
						.getText(), is("Created successfully!"));
		
		assertThat(webDriver.findElement(By.xpath("//*[@id='todoList']/ul/li"))
				.getText().substring(0, 11), is("todoThings1"));

		webDriver
				.findElement(
						By.xpath("//*[@id='todoList']/ul/li/form[@action='/todo-jpa/todo/delete']/button"))
				.click();
	}

	/**
	 * Asserts that one todo is finished.
	 */
	@Test
	public void testFinish() throws IOException {

		webDriver.get(applicationContextUrl);
		webDriver.findElement(By.id("todoTitle")).sendKeys("todoThings1");
		webDriver
				.findElement(
						By.xpath("//form[@action='/todo-jpa/todo/create']/button"))
				.click();

		webDriver
				.findElement(
						By.xpath("//*[@id='todoList']/ul/li/form[@action='/todo-jpa/todo/finish']/button"))
				.click();

		assertThat(
				webDriver.findElement(
						By.xpath("//div[@class='alert alert-success']/ul/li"))
						.getText(), is("Finished successfully!"));
		assertThat(
				webDriver
						.findElement(
								By.xpath("//*[@id='todoList']/ul/li/span[@class='strike']"))
						.getText(), is("todoThings1"));

		webDriver
				.findElement(
						By.xpath("//*[@id='todoList']/ul/li/form[@action='/todo-jpa/todo/delete']/button"))
				.click();
	}

	/**
	 * Asserts that one todo is deleted.
	 */
	@Test
	public void testDelete() throws IOException {

		webDriver.get(applicationContextUrl);
		webDriver.findElement(By.id("todoTitle")).sendKeys("todoThings1");
		webDriver
				.findElement(
						By.xpath("//form[@action='/todo-jpa/todo/create']/button"))
				.click();

		webDriver
				.findElement(
						By.xpath("//*[@id='todoList']/ul/li/form[@action='/todo-jpa/todo/delete']/button"))
				.click();
		
		assertThat(
				webDriver.findElement(
						By.xpath("//div[@class='alert alert-success']/ul/li"))
						.getText(), is("Deleted successfully!"));

		assertThat(webDriver.findElement(By.xpath("//*[@id='todoList']/ul"))
				.getText(), is(""));
	}

	/**
	 * Asserts that error message is showed.
	 * size must be between 1 and 30
	 */
	@Test
	public void testCreateEmpty() throws IOException {

		webDriver.get(applicationContextUrl);
		webDriver
				.findElement(
						By.xpath("//form[@action='/todo-jpa/todo/create']/button"))
				.click();

		assertThat(
				webDriver.findElement(By.xpath("//*[@id='todoTitle.errors']"))
						.getText(), is("size must be between 1 and 30"));
	}

	/**
	 * Asserts that error message is showed.
	 * size must be between 1 and 30
	 */
	@Test
	public void testCreateOverSize() throws IOException {

		webDriver.get(applicationContextUrl);
		webDriver.findElement(By.id("todoTitle")).sendKeys(
				"0123456789012345678901234567890");
		webDriver
				.findElement(
						By.xpath("//form[@action='/todo-jpa/todo/create']/button"))
				.click();

		assertThat(
				webDriver.findElement(By.xpath("//*[@id='todoTitle.errors']"))
						.getText(), is("size must be between 1 and 30"));
	}

	/**
	 * Asserts that error message is showed.
	 * The count of un-finished Todo must not be over 5.
	 */
	@Test
	public void testUnfinishedSize() throws IOException {

		webDriver.get(applicationContextUrl);
		for (int i = 1; i < 7; i++) {
			webDriver.findElement(By.id("todoTitle")).sendKeys(
					Integer.toString(i));
			webDriver
					.findElement(
							By.xpath("//form[@action='/todo-jpa/todo/create']/button"))
					.click();
		}

		assertThat(
				webDriver.findElement(
						By.xpath("//div[@id='todoForm']/div/ul/li")).getText(),
				is("[E001] The count of un-finished Todo must not be over 5."));

		for (int i = 0; i < 5; i++) {
			webDriver
					.findElement(
							By.xpath("//*[@id='todoList']/ul/li/form[@action='/todo-jpa/todo/delete']/button"))
					.click();
		}

	}

	/**
	 * Quits the driver, closing every associated window.
	 */
	@After
	public void tearDown() {
		webDriver.quit();
	}
}
