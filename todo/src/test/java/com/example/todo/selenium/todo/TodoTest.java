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
package com.example.todo.selenium.todo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;

import org.terasoluna.gfw.tutorial.selenium.FunctionTestSupport;

public class TodoTest extends FunctionTestSupport {

    @Value("${selenium.contextName}")
    String contextName;

    public TodoTest() {

    }

    @Override
    protected String getPackageRootUrl() {
        return super.getPackageRootUrl() + "list";
    }

    /**
     * Asserts that the content of the application entry page is "Todo List".
     */
    @Test
    public void testList() throws IOException {

        assertThat(driver.findElement(By.xpath("/html/body")).getText(), is(
                containsString("Todo List")));
    }

    /**
     * Asserts that one todo is created.
     */
    @Test
    public void testCreate() throws IOException {

        inputFieldAccessor.overrideValue(By.id("todoTitle"), "todoThings1",
                driver);
        driver.findElement(By.xpath("//form[@action='/" + contextName
                + "/todo/create']/button")).click();
        ;

        WebElement todoElement = driver.findElement(By.xpath(
                "//div[@id='todoList']/ul/li"));
        assertThat(driver.findElement(By.xpath(
                "//div[@class='alert alert-success']/ul/li")).getText(), is(
                        "Created successfully!"));

        assertThat(todoElement.getText(), is(containsString("todoThings1")));
        todoElement.findElement(By.xpath("//form[@action='/" + contextName
                + "/todo/delete']/button")).click();
    }

    /**
     * Asserts that one todo is finished.
     */
    @Test
    public void testFinish() throws IOException {

        inputFieldAccessor.overrideValue(By.id("todoTitle"), "todoThings1",
                driver);
        driver.findElement(By.xpath("//form[@action='/" + contextName
                + "/todo/create']/button")).click();
        driver.findElement(By.xpath(
                "//div[@id='todoList']/ul/li/form[@action='/" + contextName
                        + "/todo/finish']/button")).click();

        WebElement todoElement = driver.findElement(By.xpath(
                "//div[@id='todoList']/ul/li"));
        assertThat(driver.findElement(By.xpath(
                "//div[@class='alert alert-success']/ul/li")).getText(), is(
                        "Finished successfully!"));
        assertThat(todoElement.findElement(By.xpath("//span[@class='strike']"))
                .getText(), is("todoThings1"));

        todoElement.findElement(By.xpath("//form[@action='/" + contextName
                + "/todo/delete']/button")).click();
    }

    /**
     * Asserts that one todo is deleted.
     */
    @Test
    public void testDelete() throws IOException {

        inputFieldAccessor.overrideValue(By.id("todoTitle"), "todoThings1",
                driver);
        driver.findElement(By.xpath("//form[@action='/" + contextName
                + "/todo/create']/button")).click();
        ;
        driver.findElement(By.xpath(
                "//div[@id='todoList']/ul/li/form[@action='/" + contextName
                        + "/todo/delete']/button")).click();

        assertThat(driver.findElement(By.xpath(
                "//div[@class='alert alert-success']/ul/li")).getText(), is(
                        "Deleted successfully!"));

        assertThat(driver.findElement(By.xpath("//div[@id='todoList']/ul"))
                .getText(), is(""));
    }

    /**
     * Asserts that error message is showed. size must be between 1 and 30
     */
    @Test
    public void testCreateEmpty() throws IOException {

        driver.findElement(By.xpath("//form[@action='/" + contextName
                + "/todo/create']/button")).click();
        ;

        assertThat(driver.findElement(By.xpath(
                "//span[@id='todoTitle.errors']")).getText(), is(
                        "size must be between 1 and 30"));
    }

    /**
     * Asserts that error message is showed. size must be between 1 and 30
     */
    @Test
    public void testCreateOverSize() throws IOException {

        inputFieldAccessor.overrideValue(By.id("todoTitle"),
                "0123456789012345678901234567890", driver);
        driver.findElement(By.xpath("//form[@action='/" + contextName
                + "/todo/create']/button")).click();
        ;

        assertThat(driver.findElement(By.xpath(
                "//span[@id='todoTitle.errors']")).getText(), is(
                        "size must be between 1 and 30"));
    }

    /**
     * Asserts that error message is showed. The count of un-finished Todo must
     * not be over 5.
     */
    @Test
    public void testUnfinishedSize() throws IOException {

        for (int i = 0; i < 6; i++) {
            inputFieldAccessor.overrideValue(By.id("todoTitle"), Integer
                    .toString(i), driver);
            driver.findElement(By.xpath("//form[@action='/" + contextName
                    + "/todo/create']/button")).click();
        }

        assertThat(driver.findElement(By.xpath(
                "//div[@id='todoForm']/div/ul/li")).getText(), is(
                        "[E001] The count of un-finished Todo must not be over 5."));

        for (int i = 0; i < 5; i++) {
            driver.findElement(By.xpath(
                    "//div[@id='todoList']/ul/li/form[@action='/" + contextName
                            + "/todo/delete']/button")).click();
            ;
        }

    }

}
