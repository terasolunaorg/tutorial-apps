package org.terasoluna.securelogin.selenium.loginform;

import java.sql.SQLException;
import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.terasoluna.securelogin.selenium.FunctionTestSupport;
import org.terasoluna.securelogin.selenium.loginform.page.AbstractPageObject;
import org.terasoluna.securelogin.selenium.loginform.page.login.LoginPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SecureLoggingTest extends FunctionTestSupport {

	@Inject
	@Named("dataSource")
	DataSource dataSource;

	@Inject
	ResourceDatabasePopulator populator;

	@Before
	public void setup() throws ScriptException, SQLException {
		populator.populate(dataSource.getConnection());
	}
	
	/**
	 * <ul>
	 * <li>test : log username, operation and result for every request</li>
	 * </ul>
	 */
	@Test
	public void testSecureLogging001(){
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("log username, operation and result for every request");
		long start = Calendar.getInstance().getTimeInMillis();
		page = ((LoginPage) page).loginFailure("hoge", "hoge")
				.loginSuccessIntercepted("demo", "demo")
				.changePasswordSuccess("demo", "Foo1", "Foo1").gotoTop()
				.logout();
		long end = Calendar.getInstance().getTimeInMillis();
		isLogged(start, end, null, "^\\[START SERVICE\\]UserDetailsService\\.loadUserByUsername$");
		isLogged(start, end, null, "^\\[SERVICE THROWS EXCEPTION\\]UserDetailsService\\.loadUserByUsername$");
		isLogged(start, end, "demo", "^\\[START SERVICE\\]AccountSharedService\\.updatePassword$");
		isLogged(start, end, "demo", "^\\[COMPLETE SERVICE\\]AccountSharedService\\.updatePassword$");
	}
	
	private void isLogged(long start, long end, String username, String message){
		NamedParameterJdbcOperations jdbcOperations = dbLogAssertOperations.getJdbcOperations();
		
		StringBuilder sql = new StringBuilder();
		StringBuilder where = new StringBuilder();
		sql.append("SELECT COUNT(e.*) FROM logging_event e");
		where.append(" WHERE e.formatted_message REGEXP :message");
		where.append(" AND e.timestmp BETWEEN :start AND :end");
		sql.append(" JOIN logging_event_property ep ON ep.event_id = e.event_id");
		if(username != null){
			where.append(" AND ep.mapped_key = 'USER' AND ep.mapped_value = :username");
		}
		sql.append(where);
		
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("start", start);
        params.addValue("end", end);
        params.addValue("username", username);
        params.addValue("message", message);
		Long count = jdbcOperations.queryForObject(sql.toString(), params,
                Long.class);
        assertThat(count, is(not(0L)));
	}
}
