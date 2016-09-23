package todo.restfuse.todo;

import static com.eclipsesource.restfuse.Assert.assertNoContent;

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

@RunWith(HttpJUnitRunner.class)
public class DeleteOneTest {

	@Rule
	public Destination restfuse = new Destination(this,
			"http://localhost:8081/todo-api-jpa-multi-web/api/v1/todos/"
					+ Todo.todoId);

	@Context
	private Response response;

	@HttpTest(method = Method.DELETE, path = "/", type = MediaType.APPLICATION_JSON)
	public void testGetTodos() throws Exception {

		assertNoContent(response);
	}

}