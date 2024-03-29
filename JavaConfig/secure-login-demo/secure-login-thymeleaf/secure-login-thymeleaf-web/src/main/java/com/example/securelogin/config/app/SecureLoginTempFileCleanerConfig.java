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
package com.example.securelogin.config.app;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.PeriodicTrigger;

import com.example.securelogin.domain.common.scheduled.UnnecessaryReissueInfoCleaner;

import jakarta.inject.Inject;;

/**
 * Scheduler definition.
 */
@Configuration
public class SecureLoginTempFileCleanerConfig implements
                                                 SchedulingConfigurer {

    /**
     * Bean of Executor
     */
    @Inject
    private Executor tempFileTaskScheduler;

    /**
     * Bean of UnnecessaryReissueInfoCleaner
     */
    @Inject
    private UnnecessaryReissueInfoCleaner tempFileCleaner;

    /**
     * Bean of PeriodicTrigger
     */
    @Inject
    private PeriodicTrigger tempFileCleanTrigger;

    /**
     * {@inheritDoc}
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(tempFileTaskScheduler);
        taskRegistrar.addTriggerTask(() -> tempFileCleaner.cleanup(),
                tempFileCleanTrigger);
    }
}