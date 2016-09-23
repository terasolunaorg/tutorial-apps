package todo.restfuse.todo;

import static com.eclipsesource.restfuse.Assert.assertConflict;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.runner.RunWith;

import todo.restfuse.todo.common.ErrInfo;
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
public class PutErr409Test {

	@Rule
	public Destination restfuse = new Destination(this,
			"http://localhost:8081/todo-api-mybatis3-multi-web/api/v1/todos/"
					+ Todo.todoId);

	@Context
	private Response response;

	@HttpTest(method = Method.PUT, path = "/", type = MediaType.APPLICATION_JSON, file = "/testData/CreateTest.txt")
	public void testGetTodos() throws Exception {

		String body = response.getBody();
		ObjectMapper beanMapper = new ObjectMapper();
		ErrInfo errInfo = beanMapper.readValue(body, ErrInfo.class);

		assertConflict(response);
		Assert.assertEquals(errInfo.getCode(), "E002");
		Assert.assertEquals(errInfo.getMessage(),
				"[E002] The requested Todo is already finished. (id="
						+ Todo.todoId + ")");
	}
}