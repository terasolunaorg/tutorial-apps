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
package todo.selenium;

import static com.jayway.restassured.config.LogConfig.logConfig;
import static com.jayway.restassured.config.RestAssuredConfig.config;

import java.io.File;
import java.io.PrintStream;
import java.io.StringWriter;

import javax.inject.Inject;

import org.apache.commons.io.output.WriterOutputStream;
import org.junit.AfterClass;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasoluna.gfw.tutorial.selenium.FunctionTestSupport;

import com.jayway.restassured.RestAssured;

public abstract class RestTestSupport extends FunctionTestSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            RestTestSupport.class);

    @Inject
    private RestLog restLog;

    protected StringWriter writer;

    protected PrintStream captor;

    protected RestTestSupport() {
        super();
        disableSetupDefaultWebDriver();
    }

    @Before
    public final void setUpRestEvidence() {

        String testCaseName = testName.getMethodName().replaceAll("^test", "");

        String simplePackageName = this.getClass().getPackage().getName()
                .replaceAll(".*\\.", "");
        File evidenceSavingDirectory = new File(String.format("%s/%s/%s",
                evidenceBaseDirectory, simplePackageName, testCaseName));
        restLog.setUp(evidenceSavingDirectory);
    }

    @Before
    public final void setUpConfig() {

        // Initialization of applicationContextUrl
        RestAssured.baseURI = applicationContextUrl + "/api/v1/todos";
        RestAssured.config = config().logConfig(logConfig()
                .enablePrettyPrinting(false));
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
    }

    @Override
    protected void onSucceeded() {
        String subTitle = "succeeded";
        try {
            restLog.save(writer, subTitle);
        } catch (Throwable t) {
            LOGGER.error("failed restLog capture.", t);
        }
    }

    @Override
    protected void onFailed(Throwable e) {
        String subTitle = "failed";
        try {
            restLog.saveForced(writer, subTitle);
        } catch (Throwable t) {
            LOGGER.error("failed restLog capture.", t);
        }
    }

    @AfterClass
    public static void tearDownConfig() {
        RestAssured.reset();
    }

}
