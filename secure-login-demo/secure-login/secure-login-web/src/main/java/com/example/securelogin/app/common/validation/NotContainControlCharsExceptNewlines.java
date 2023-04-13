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
package com.example.securelogin.app.common.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.example.securelogin.app.common.validation.NotContainControlCharsExceptNewlines.List;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Pattern;

@Documented
@Constraint(validatedBy = {})
@Target(FIELD)
@Retention(RUNTIME)
@Repeatable(List.class)
@ReportAsSingleViolation
@Pattern(regexp = "^[\\r\\n\\P{Cntrl}]*$")
public @interface NotContainControlCharsExceptNewlines {
    String message() default "{com.example.securelogin.app.common.validation.NotContainControlCharsExceptNewlines.message}";

    Class<?>[] groups() default {};

    @Target(FIELD)
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        NotContainControlCharsExceptNewlines[] value();
    }

    Class<? extends Payload>[] payload() default {};
}
