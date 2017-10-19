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

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

public class RestLog {

    private static final Logger logger = LoggerFactory.getLogger(RestLog.class);

    @Value("${selenium.enableCapture}")
    protected boolean enableCapture;

    private File evidenceSavingDirectory;

    private final AtomicInteger sequence = new AtomicInteger(0);

    public void setUp(File evidenceSavingDirectory) {
        sequence.set(0);
        this.evidenceSavingDirectory = evidenceSavingDirectory;
    }

    public void save(StringWriter writer) {
        save(writer, (String) null);
    }

    public void save(StringWriter writer, String subTitle) {
        if (!enableCapture) {
            return;
        }
        saveForced(writer, subTitle);
    }

    public void saveForced(StringWriter writer, String subTitle) {

        if (StringUtils.isEmpty(subTitle)) {
            subTitle = "";
        } else {
            subTitle = "-" + subTitle;
        }

        int sequenceNo = sequence.incrementAndGet();
        String evidenceFile = String.format("rest_communi_%03d%s.txt",
                sequenceNo, subTitle);
        File pageSourceFile = new File(evidenceSavingDirectory, evidenceFile);

        try {
            FileUtils.writeStringToFile(pageSourceFile, writer.toString());

        } catch (IOException e) {
            logger.error(e.toString());
        }

    }

}
