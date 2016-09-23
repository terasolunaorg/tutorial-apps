package todo.restfuse.todo;

import static com.eclipsesource.restfuse.Assert.assertBadRequest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.runner.RunWith;

import todo.restfuse.todo.common.ErrInfo;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.MediaType;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(HttpJUnitRunner.class)
public class CreateErr400Test {

	@Rule
	public Destination restfuse = new Destination(this,
			"http://localhost:8081/todo-api-mybatis3/api/v1/todos");

	@Context
	private Response response;

	@HttpTest(method = Method.POST, path = "/", type = MediaType.APPLICATION_JSON, file = "/testData/CreateErr400Test.txt")
	public void testPostTodos() throws Exception {

		String body = response.getBody();
		ObjectMapper beanMapper = new ObjectMapper();
		ErrInfo errInfo = beanMapper.readValue(body, ErrInfo.class);

		assertBadRequest(response);
		Assert.assertEquals(errInfo.getCode(), "E400");
		Assert.assertEquals(errInfo.getMessage(),
				"[E400] The requested Todo contains invalid values.");
		Assert.assertEquals(errInfo.getDetails().get(0).getCode(), "NotNull");
		Assert.assertEquals(errInfo.getDetails().get(0).getMessage(),
				"todoTitle may not be null.");
		Assert.assertEquals(errInfo.getDetails().get(0).getTarget(),
				"todoTitle");
	}
}