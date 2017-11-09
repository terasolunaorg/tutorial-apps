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
package todo.selenium.todo;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;

import todo.selenium.RestTestSupport;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

@ContextConfiguration(locations = {
        "classpath:META-INF/spring/seleniumContextRest.xml" })
public class TodoApiTest extends RestTestSupport {

    @Value("${selenium.applicationContextUrl}")
    String applicationContextUrl;

    @Before
    public void setUp() throws Exception {

        // Get all existing todo resources
        List<String> todoIds = RestAssured.given().when().get("").then()
                .extract().jsonPath().getList("todoId");

        // Delete all existing todos
        for (String todoId : todoIds) {
            RestAssured.given().when().delete("/{todoId}", todoId);
        }
    }

    @Test
    public void testCreateErr400() throws Exception {

        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("todoTitle", null);

        // posting request
        RestAssured.given().body(jsonBody).contentType(ContentType.JSON).when()
                .post("").then().statusCode(400).body("code", equalTo("E400"))
                .body("message", equalTo(
                        "[E400] The requested Todo contains invalid values."))
                .body("details[0].code", equalTo("NotNull")).body(
                        "details[0].message", equalTo(
                                "todoTitle may not be null.")).body(
                                        "details[0].target", equalTo(
                                                "todoTitle"));
    }

    @Test
    public void testCreate() throws Exception {

        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("todoTitle", "Hello World!");

        // posting request
        RestAssured.given().body(jsonBody).contentType(ContentType.JSON).when()
                .post("").then().statusCode(201).body("todoTitle", equalTo(
                        "Hello World!")).body("finished", equalTo(false));
    }

    @Test
    public void testDeleteOne() throws Exception {

        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("todoTitle", "Hello World!");

        // posting request
        String todoId = RestAssured.given().body(jsonBody).contentType(
                ContentType.JSON).when().post("").then().extract().jsonPath()
                .get("todoId");

        // deleting request
        RestAssured.given().when().delete("/{todoId}", todoId).then()
                .statusCode(204);
    }

    @Test
    public void testGetAll() throws Exception {

        // getting request
        RestAssured.given().when().get("").then().statusCode(200);
    }

    @Test
    public void testGetOneErr404() throws Exception {

        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("todoTitle", "Hello World!");

        // posting request
        String todoId = RestAssured.given().body(jsonBody).contentType(
                ContentType.JSON).when().post("").then().extract().jsonPath()
                .get("todoId");

        // request path
        String nonexistId = todoId + "Nonexist";

        // getting request
        RestAssured.given().when().get("/{nonexistId}", nonexistId).then()
                .statusCode(404).body("code", equalTo("E404")).body("message",
                        equalTo("[E404] The requested Todo is not found. (id="
                                + nonexistId + ")"));
    }

    @Test
    public void testGetOne() throws Exception {

        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("todoTitle", "Hello World!");

        // posting request
        String todoId = RestAssured.given().body(jsonBody).contentType(
                ContentType.JSON).when().post("").then().extract().jsonPath()
                .get("todoId");

        // getting request
        RestAssured.given().when().get("/{todoId}", todoId).then().statusCode(
                200).body("todoId", equalTo(todoId));
    }

    @Test
    public void testPutErr405() throws Exception {

        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("todoTitle", "Hello World!");

        // putting request
        RestAssured.given().body(jsonBody).contentType(ContentType.JSON).when()
                .put("").then().statusCode(405).body("code", equalTo("E999"))
                .body("message", equalTo(
                        "[E999] Error occurred. Caused by : Request method 'PUT' not supported"));
    }

    @Test
    public void testPutErr409() throws Exception {

        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("todoTitle", "Hello World!");

        // posting request
        String todoId = RestAssured.given().body(jsonBody).contentType(
                ContentType.JSON).when().post("").then().extract().jsonPath()
                .get("todoId");

        // putting request
        RestAssured.given().body(jsonBody).contentType(ContentType.JSON).when()
                .put("/{todoId}", todoId);

        // putting request again
        RestAssured.given().body(jsonBody).contentType(ContentType.JSON).when()
                .put("/{todoId}", todoId).then().statusCode(409).body("code",
                        equalTo("E002")).body("message", equalTo(
                                "[E002] The requested Todo is already finished. (id="
                                        + todoId + ")"));
    }

    @Test
    public void testPut() throws Exception {

        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("todoTitle", "Hello World!");

        // posting request
        String todoId = RestAssured.given().body(jsonBody).contentType(
                ContentType.JSON).when().post("").then().extract().jsonPath()
                .get("todoId");

        // putting request
        RestAssured.given().body(jsonBody).contentType(ContentType.JSON).when()
                .put("/{todoId}", todoId).then().statusCode(200).body(
                        "finished", equalTo(true));
    }
}
