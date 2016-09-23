package todo.restfuse.todo;

import static com.eclipsesource.restfuse.Assert.assertOk;

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
public class GetOneTest {

	@Rule
	public Destination restfuse = new Destination(this,
			"http://localhost:8081/todo-api-mybatis3/api/v1/todos/"
					+ Todo.todoId);

	@Context
	private Response response;

	@HttpTest(method = Method.GET, path = "/", type = MediaType.APPLICATION_JSON)
	public void testGetTodos() throws Exception {

		String body = response.getBody();
		String orgId = Todo.todoId;
		ObjectMapper beanMapper = new ObjectMapper();
		Todo todo = beanMapper.readValue(body, Todo.class);

		assertOk(response);
		Assert.assertEquals(orgId, todo.getTodoId());
	}

}