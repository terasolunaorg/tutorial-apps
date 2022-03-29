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

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

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
import com.example.securelogin.selenium.loginform.page.unlock.UnlockSuccessPage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;

public class AccountLockTest extends DBLogFunctionTestSupport {

    @Value("${security.lockingThreshold}")
    int lockingThreshold;

    @Value("${security.lockingDurationSeconds}")
    int lockingDurationSeconds;

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
     * <li>test : an account is locked by series of incorrect password attempts
     * <li>test : account is unlocked when the certain time passes</li>
     * </ul>
     */
    @Test
    public void testAccountLock001() throws IOException, InterruptedException {
        AbstractPageObject page = new LoginPage(webDriverOperations, applicationContextUrl)
                .openWithDescription(
                        "an account is locked by series of incorrect password attempts"
                                + " and unlocked when the certain time passes");
        for (int i = 0; i < lockingThreshold + 1; i++) {
            page = ((LoginPage) page).loginFailure("demo", "hoge");
        }
        assertThat(((LoginPage) page).getLoginError(), is(
                "User account is locked"));

        webDriverOperations.suspend(lockingDurationSeconds, TimeUnit.SECONDS);
        page = ((LoginPage) page).loginSuccessIntercepted("demo", "demo");
        assertTrue(webDriverOperations.getCurrentUrl().endsWith(
                "/password?form"));

        page = ((PasswordChangePage) page).changePasswordSuccess("demo", "Foo1",
                "Foo1").gotoTop().logout();
    }

    /**
     * <ul>
     * <li>test : administrator can unlock accounts</li>
     * </ul>
     */
    @Test
    public void testAccountLock002() throws IOException, InterruptedException {
        AbstractPageObject page = new LoginPage(webDriverOperations, applicationContextUrl)
                .openWithDescription("administrator can unlock accounts");

        for (int i = 0; i < lockingThreshold + 1; i++) {
            page = ((LoginPage) page).loginFailure("demo", "hoge");
        }

        // unlock
        page = ((LoginPage) page).loginSuccessIntercepted("admin", "demo")
                .changePasswordSuccess("demo", "Bar1", "Bar1").gotoTop()
                .goToUnlockPage().unlockSuccess("demo");
        assertTrue(webDriverOperations.getCurrentUrl().endsWith(
                "unlock?complete"));

        // confirm the account is successfully unlocked
        page = ((UnlockSuccessPage) page).gotoTop().logout()
                .loginSuccessIntercepted("demo", "demo");
        assertTrue(webDriverOperations.getCurrentUrl().endsWith(
                "/password?form"));
        page = ((PasswordChangePage) page).changePasswordSuccess("demo", "Foo1",
                "Foo1").gotoTop().logout();
    }

}
