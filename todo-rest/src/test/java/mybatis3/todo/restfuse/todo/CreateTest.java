package todo.restfuse.todo;

import static com.eclipsesource.restfuse.Assert.assertCreated;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.runner.RunWith;

import todo.restfuse.todo.common.Todo;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.MediaType;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(HttpJUnitRunner.class)
public class CreateTest {

	@Rule
	public Destination restfuse = new Destination(this,
			"http://localhost:8081/todo-api-mybatis3/api/v1/todos");

	@Context
	private Response response;

	@HttpTest(method = Method.POST, path = "/", type = MediaType.APPLICATION_JSON, file = "/testData/CreateTest.txt")
	public void testPostTodos() throws Exception {

		String body = response.getBody();
		ObjectMapper beanMapper = new ObjectMapper();
		Todo todo = beanMapper.readValue(body, Todo.class);

		assertCreated(response);
		Assert.assertEquals(todo.getTodoTitle(), "Hello World!");
	}
}