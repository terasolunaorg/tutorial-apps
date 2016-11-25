package com.example.security.selenium.security;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.security.selenium.FunctionTestSupport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/seleniumContext.xml" })
public class FirstSpringSecurityTest extends FunctionTestSupport {

    /**
     * Test of login screen<br>
     * <li>Access the root of the project and transition to the login screen. <li>The initial value of username on the login screen is "demo".<li>
     * The initial value of password on the login screen is "demo".
     */
    @Test
    public void testLoginForm() {

        // check initial value
        {
        	assertThat(driver.findElement(By.id("username")).getAttribute("value"),
            		is("demo"));
            assertThat(driver.findElement(By.id("password")).getAttribute("value"),
            		is("demo"));

        }

    }

    /**
     * Test of home screen<br>
     * After login screen is displayed, login as the default user.<br>
     * <li>"Hello World!" Is displayed on the login screen.<li>
     * "Welcome Taro Yamada !!" is displayed on the login screen.<li>
     * The "view account" link is displayed on the login screen.
     */
    @Test
    public void testLogin() {

        // login
        driver.findElement(By.name("submit")).click();

        // check home screen
        {
            assertThat(driver.findElement(By.tagName("h1")).getText(),
                    is("Hello world!"));

            List<WebElement> cheese = driver.findElements(By.tagName("p"));
            assertThat(cheese.get(1).getText(), is("Welcome Taro Yamada !!"));
            assertThat(
            		driver.findElement(By.xpath("//a[@href='/"
                            + contextName + "/account']")).getText(), is("view account"));
        }

    }

    /**
     * Testlogout process.After logging in as the default user, press the logout button on the Home screen. <li>
     * Return to the login screen after pressing the logout button. <li>The initial value of username on the login screen is "demo". <li>
     * The initial value of password on the login screen is "demo".
     */
    @Test
    public void testLogout() {

        // login
        driver.findElement(By.name("submit")).click();

        // click logout button
        {
        	driver.findElement(By.tagName("button")).click();
        }

        // check login screen
        {
        	assertThat(driver.findElement(By.id("username")).getAttribute("value"),
            		is("demo"));
            assertThat(driver.findElement(By.id("password")).getAttribute("value"),
            		is("demo"));
        }

    }

    /**
     * Test account information screen<br>
     * After logging in, click "view account" button.<br>
     * <li>Username is "demo".<li>First name is "Taro". <li>
     * Last name is "Yamada".
     * 
     */
    @Test
    public void testViewAccount() {

        // login
        driver.findElement(By.name("submit")).click();

        // click "view account" button.
        {
        	driver.findElement(By.xpath("//a[@href='/" + contextName
                    + "/account']")).click();;
        }

        // check account information
        {
            // get table
            List<WebElement> cheese = driver.findElements(By
                    .xpath("//table/tbody/tr/td"));

            assertThat(cheese.get(0).getText(), is("demo"));
            assertThat(cheese.get(1).getText(), is("Taro"));
            assertThat(cheese.get(2).getText(), is("Yamada"));
        }

    }

    /**
     * Authentication test with non-registered users
     * Display the login screen, enter the user name that is not registered, and click the login button.
     * <li>The error message "Bad credentials" is output on the login screen.
     */
    @Test
    public void testInvalidUserLogin() {

        // login as an unregistered user
        {
            driver.findElement(By.id("username")).clear(); 
            driver.findElement(By.id("username")).sendKeys("aaa"); 
            driver.findElement(By.name("submit")).click();

        }

        // check error message and check login screen
        {
            assertThat(driver.findElement(By.tagName("li")).getText(),
                    is("Bad credentials"));

            assertThat(driver.findElement(By.id("username")).getAttribute("value"),
            		is("demo"));
            assertThat(driver.findElement(By.id("password")).getAttribute("value"),
            		is("demo"));
        }
    }
}
