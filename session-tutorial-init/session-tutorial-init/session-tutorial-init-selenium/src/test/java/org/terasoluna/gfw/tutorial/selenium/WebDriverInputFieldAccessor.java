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
package org.terasoluna.gfw.tutorial.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class is for accessing input fields using WebDriver.
 * <p>
 * It provides two methods of accessing in a standard way and accessing using JavaScript.
 * </p>
 */
public enum WebDriverInputFieldAccessor {

    /**
     * An object that accesses input fields in Selenium's standard way.
     */
    STANDARD {
        /**
         * {@inheritDoc}
         */
        @Override
        public void appendValue(By by, String value, WebDriver webDriver) {
            webDriver.findElement(by).sendKeys(value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void overrideValue(By by, String value, WebDriver webDriver) {
            WebElement element = webDriver.findElement(by);
            element.clear();
            element.sendKeys(value);
        }
    },

    /**
     * An object that accesses input fields using JavaScript.
     * <p>
     * It is an object aimed at speeding up the access to the input field.
     * </p>
     */
    JAVASCRIPT {
        /**
         * {@inheritDoc}
         */
        @Override
        public void appendValue(By by, String value, WebDriver webDriver) {
            String inputValue = getValue(by, webDriver);
            if (inputValue != null) {
                inputValue = inputValue + value;
            } else {
                inputValue = value;
            }
            setValue(by, inputValue, webDriver);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void overrideValue(By by, String value, WebDriver webDriver) {
            setValue(by, value, webDriver);
        }

        private void setValue(By by, String value, WebDriver webDriver) {
            String scriptCode = "arguments[0].value = '" + js(value) + "';";
            ((JavascriptExecutor) webDriver).executeScript(scriptCode, webDriver
                    .findElement(by));
        }

    };

    /**
     * Add a value to the specified element (text item).
     * @param by : An identifier for searching for an element (text item)
     */
    public abstract void appendValue(By by, String value, WebDriver webDriver);

    /**
     * Overwrite the value of the specified element (text item).
     * @param by : An identifier for searching for an element (text item)
     */
    public abstract void overrideValue(By by, String value,
            WebDriver webDriver);

    /**
     * Get the value set in the specified input field.
     * @param by : An identifier for searching for an element (text item)
     */
    public String getValue(By by, WebDriver webDriver) {
        return webDriver.findElement(by).getAttribute("value");
    }

    private static String js(String value) {
        if (value == null || value.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            switch (ch) {
            case '\'':
                result.append("\\'");
                break;
            case '"':
                result.append("\\\"");
                break;
            case '\\':
                result.append("\\\\");
                break;
            case '/':
                result.append("\\/");
                break;
            case '<':
                result.append("\\x3c");
                break;
            case '>':
                result.append("\\x3e");
                break;
            case '\r':
                result.append("\\r");
                break;
            case '\n':
                result.append("\\n");
                break;
            default:
                result.append(ch);
                break;
            }
        }
        return result.toString();
    }

}
