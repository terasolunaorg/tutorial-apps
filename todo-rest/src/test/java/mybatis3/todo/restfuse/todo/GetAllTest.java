package todo.restfuse.todo;

import static com.eclipsesource.restfuse.Assert.assertOk;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.runner.RunWith;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.MediaType;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;

@RunWith(HttpJUnitRunner.class)
public class GetAllTest {

	@Rule
	public Destination restfuse = new Destination(this,
			"http://localhost:8081/todo-api-mybatis3/api/v1/todos");

	@Context
	private Response response;

	@HttpTest(method = Method.GET, path = "/", type = MediaType.APPLICATION_JSON)
	public void testGetTodos() throws Exception {

		String body = response.getBody();

		assertOk(response);
		Assert.assertEquals(body, "[]");
	}

}