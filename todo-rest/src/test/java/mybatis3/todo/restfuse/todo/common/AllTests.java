package todo.restfuse.todo.common;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import todo.restfuse.todo.CreateErr400Test;
import todo.restfuse.todo.CreateTest;
import todo.restfuse.todo.DeleteOneTest;
import todo.restfuse.todo.GetAllTest;
import todo.restfuse.todo.GetOneErr404Test;
import todo.restfuse.todo.GetOneTest;
import todo.restfuse.todo.PutErr405Test;
import todo.restfuse.todo.PutErr409Test;
import todo.restfuse.todo.PutTest;

@RunWith(Suite.class)
@SuiteClasses({ CreateTest.class, CreateErr400Test.class, GetOneTest.class,
		GetOneErr404Test.class, PutTest.class, PutErr409Test.class,
		DeleteOneTest.class, GetAllTest.class, PutErr405Test.class })
public class AllTests {

}
