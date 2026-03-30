/*
 * Copyright(c) 2024 NTT DATA Group Corporation. Copyright(c) 2013 NTT Corporation.
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import com.example.todo.selenium.RestTestSupport;

@ContextConfiguration(locations = {"classpath:META-INF/spring/seleniumContextRest.xml"})
public class TodoApiTest extends RestTestSupport {

    @Value("${selenium.applicationContextUrl}")
    String applicationContextUrl;

    @BeforeEach
    public void setUp() throws Exception {

        // Get all existing todo resources and delete them
        AtomicReference<List<String>> todoIdsRef = new AtomicReference<>();

        // @formatter:off
        restTestClient.get()
            .uri("")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$[*].todoId").value(List.class, todoIdsRef::set);
        // @formatter:on

        List<String> todoIds = todoIdsRef.get();

        // Delete all existing todos (if any)
        if (todoIds != null) {
            for (String todoId : todoIds) {
                // @formatter:off
                restTestClient.delete()
                    .uri("/{todoId}", todoId)
                    .exchange()
                    .expectStatus().isNoContent();
                // @formatter:on
            }
        }
    }

    @Test
    public void testCreateErr400() throws Exception {

        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("todoTitle", null);

        // @formatter:off
        restTestClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonBody)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.code").isEqualTo("E400")
                .jsonPath("$.message").isEqualTo("[E400] The requested Todo contains invalid values.")
                .jsonPath("$.details[0].code").isEqualTo("NotNull")
                .jsonPath("$.details[0].message").isEqualTo("todoTitle may not be null.")
                .jsonPath("$.details[0].target").isEqualTo("todoTitle");
        // @formatter:on
    }

    @Test
    public void testCreate() throws Exception {

        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("todoTitle", "Hello World!");

        // @formatter:off
        restTestClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.todoTitle").isEqualTo("Hello World!")
                .jsonPath("$.finished").isEqualTo(false);
        // @formatter:on
    }

    @Test
    public void testDeleteOne() throws Exception {

        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("todoTitle", "Hello World!");

        // Create a todo first and extract todoId
        AtomicReference<String> todoIdRef = new AtomicReference<>();

        // @formatter:off
        restTestClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.todoId").value((String todoId) -> todoIdRef.set(todoId));
        // @formatter:on

        String todoId = todoIdRef.get();

        // @formatter:off
        restTestClient.delete()
                .uri("/{todoId}", todoId)
                .exchange()
                .expectStatus().isNoContent();
        // @formatter:on
    }

    @Test
    public void testGetAll() throws Exception {

        // @formatter:off
        restTestClient.get()
                .exchange()
                .expectStatus().isOk();
        // @formatter:on
    }

    @Test
    public void testGetOneErr404() throws Exception {

        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("todoTitle", "Hello World!");

        // Create a todo first and extract todoId
        AtomicReference<String> todoIdRef = new AtomicReference<>();

        // @formatter:off
        restTestClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.todoId").value((String todoId) -> todoIdRef.set(todoId));
        // @formatter:on

        String todoId = todoIdRef.get();
        String nonexistId = todoId + "Nonexist";

        // @formatter:off
        restTestClient.get()
                .uri("/{nonexistId}", nonexistId)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.code").isEqualTo("E404")
                .jsonPath("$.message").isEqualTo("[E404] The requested Todo is not found. (id=" + nonexistId + ")");
        // @formatter:on
    }

    @Test
    public void testGetOne() throws Exception {

        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("todoTitle", "Hello World!");

        // Create a todo first and extract todoId
        AtomicReference<String> todoIdRef = new AtomicReference<>();

        // @formatter:off
        restTestClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.todoId").value((String todoId) -> todoIdRef.set(todoId));
        // @formatter:on

        String todoId = todoIdRef.get();

        // @formatter:off
        restTestClient.get()
                .uri("/{todoId}", todoId)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.todoId").isEqualTo(todoId);
        // @formatter:on
    }

    @Test
    public void testPutErr405() throws Exception {

        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("todoTitle", "Hello World!");

        // @formatter:off
        restTestClient.put()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonBody)
                .exchange()
                .expectStatus().isEqualTo(405)
                .expectBody()
                .jsonPath("$.code").isEqualTo("E999")
                .jsonPath("$.message").isEqualTo("[E999] Error occurred. Caused by : Request method 'PUT' is not supported");
        // @formatter:on
    }

    @Test
    public void testPutErr409() throws Exception {

        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("todoTitle", "Hello World!");

        // Create a todo first and extract todoId
        AtomicReference<String> todoIdRef = new AtomicReference<>();

        // @formatter:off
        restTestClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.todoId").value((String todoId) -> todoIdRef.set(todoId));
        // @formatter:on

        String todoId = todoIdRef.get();

        // First PUT request
        // @formatter:off
        restTestClient.put()
                .uri("/{todoId}", todoId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonBody)
                .exchange()
                .expectStatus().isOk();
        // @formatter:on

        // Second PUT request (should return conflict)
        // @formatter:off
        restTestClient.put()
                .uri("/{todoId}", todoId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonBody)
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody()
                .jsonPath("$.code").isEqualTo("E002")
                .jsonPath("$.message").isEqualTo("[E002] The requested Todo is already finished. (id=" + todoId + ")");
        // @formatter:on
    }

    @Test
    public void testPut() throws Exception {

        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("todoTitle", "Hello World!");

        // Create a todo first and extract todoId
        AtomicReference<String> todoIdRef = new AtomicReference<>();

        // @formatter:off
        restTestClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.todoId").value((String todoId) -> todoIdRef.set(todoId));
        // @formatter:on

        String todoId = todoIdRef.get();

        // @formatter:off
        restTestClient.put()
                .uri("/{todoId}", todoId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonBody)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.finished").isEqualTo(true);
        // @formatter:on
    }
}
