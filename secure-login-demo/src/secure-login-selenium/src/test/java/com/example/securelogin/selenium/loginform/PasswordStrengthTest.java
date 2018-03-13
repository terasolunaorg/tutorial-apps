/*
 * Copyright (C) 2013-2017 NTT DATA Corporation
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

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.terasoluna.gfw.tutorial.selenium.DBLogFunctionTestSupport;
import com.example.securelogin.selenium.loginform.page.AbstractPageObject;
import com.example.securelogin.selenium.loginform.page.login.LoginPage;
import com.example.securelogin.selenium.loginform.page.passwordchange.PasswordChangePage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class PasswordStrengthTest extends DBLogFunctionTestSupport {

    @Value("${security.passwordMinimumLength}")
    int passwordMinimumLength;

    @Inject
    @Named("dataSource")
    private DataSource dataSource;

    @Inject
    ResourceDatabasePopulator populator;

    @Before
    public void setup() throws ScriptException, SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            populator.populate(connection);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * <ul>
     * <li>test : password strength check(insufficient length)</li>
     * </ul>
     */
    @Test
    public void testPasswordStrength001() throws IOException, InterruptedException {
        AbstractPageObject page = new LoginPage(webDriverOperations, applicationContextUrl)
                .openWithDescription(
                        "password strength check(insufficient length)");

        // password strength check : insufficient length
        page = ((LoginPage) page).loginSuccessIntercepted("demo", "demo")
                .changePasswordFailure("demo", "Fo1", "Fo1");
        assertThat(((PasswordChangePage) page).getNewPasswordError(),
                containsString("Password must be at least "
                        + passwordMinimumLength + " characters in length."));

        page = ((PasswordChangePage) page).changePasswordSuccess("demo", "Foo1",
                "Foo1").gotoTop().logout();
    }

    /**
     * <ul>
     * <li>test : password strength check(characteristic condition is
     * unsatisfied)</li>
     * </ul>
     */
    @Test
    public void testPasswordStrength002() throws IOException, InterruptedException {
        AbstractPageObject page = new LoginPage(webDriverOperations, applicationContextUrl)
                .openWithDescription(
                        "password strength check(characteristic condition is"
                                + "unsatisfied)");

        // password strength check : characteristic condition is unsatisfied
        page = ((LoginPage) page).loginSuccessIntercepted("demo", "demo")
                .changePasswordFailure("demo", "FooBar", "FooBar");
        assertThat(((PasswordChangePage) page).getNewPasswordError(),
                containsString(
                        "Password matches 2 of 4 character rules, but 3 are required."));

        page = ((PasswordChangePage) page).changePasswordSuccess("demo", "Foo1",
                "Foo1").gotoTop().logout();
    }

    /**
     * <ul>
     * <li>test : password strength check(contains username)</li>
     * </ul>
     */
    @Test
    public void testPasswordStrength003() throws IOException, InterruptedException {
        AbstractPageObject page = new LoginPage(webDriverOperations, applicationContextUrl)
                .openWithDescription(
                        "password strength check(contains username)");

        // password strength check : contains username
        page = ((LoginPage) page).loginSuccessIntercepted("demo", "demo")
                .changePasswordFailure("demo", "demoFoo1", "demoFoo1");
        assertThat(((PasswordChangePage) page).getNewPasswordError(),
                containsString("Password contains the user id demo."));

        page = ((PasswordChangePage) page).changePasswordSuccess("demo", "Foo1",
                "Foo1").gotoTop().logout();
    }
}
