/*
 * Copyright(c) 2013 NTT DATA Corporation. Copyright(c) 2013 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.example.securelogin.selenium.loginform;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.terasoluna.gfw.tutorial.selenium.DBLogFunctionTestSupport;
import com.example.securelogin.selenium.loginform.page.AbstractPageObject;
import com.example.securelogin.selenium.loginform.page.login.LoginPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class SecureLoggingTest extends DBLogFunctionTestSupport {

    @Inject
    @Named("dataSource")
    DataSource dataSource;

    @Inject
    ResourceDatabasePopulator populator;

    @Before
    public void setup() throws ScriptException, SQLException {
        try (Connection connection = dataSource.getConnection()) {
            populator.populate(connection);
        }
    }

    /**
     * <ul>
     * <li>test : log username, operation and result for every request</li>
     * </ul>
     */
    @Test
    public void testSecureLogging001() {
        AbstractPageObject page = new LoginPage(webDriverOperations, applicationContextUrl)
                .openWithDescription(
                        "log username, operation and result for every request");
        long start = Calendar.getInstance().getTimeInMillis();
        page = ((LoginPage) page).loginFailure("hoge", "hoge")
                .loginSuccessIntercepted("demo", "demo").changePasswordSuccess(
                        "demo", "Foo1", "Foo1").gotoTop().logout();
        long end = Calendar.getInstance().getTimeInMillis();
        isLogged(start, end, null,
                "^\\[START SERVICE\\]UserDetailsService\\.loadUserByUsername$");
        isLogged(start, end, null,
                "^\\[SERVICE THROWS EXCEPTION\\]UserDetailsService\\.loadUserByUsername$");
        isLogged(start, end, "demo",
                "^\\[START SERVICE\\]AccountSharedService\\.updatePassword$");
        isLogged(start, end, "demo",
                "^\\[COMPLETE SERVICE\\]AccountSharedService\\.updatePassword$");
    }

    private void isLogged(long start, long end, String username,
            String message) {
        NamedParameterJdbcOperations jdbcOperations = dbLogAssertOperations
                .getJdbcOperations();

        StringBuilder sql = new StringBuilder();
        StringBuilder where = new StringBuilder();
        sql.append("SELECT COUNT(e.*) FROM logging_event e");
        where.append(" WHERE e.formatted_message REGEXP :message");
        where.append(" AND e.timestmp BETWEEN :start AND :end");
        sql.append(
                " JOIN logging_event_property ep ON ep.event_id = e.event_id");
        if (username != null) {
            where.append(
                    " AND ep.mapped_key = 'USER' AND ep.mapped_value = :username");
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
