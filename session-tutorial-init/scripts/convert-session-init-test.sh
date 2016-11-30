#!/bin/bash

#selenium.properties
find ./target-project/session-tutorial-init/session-tutorial-init-selenium/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=session-tutorial-init-web|'

find ./target-project/session-tutorial-init -name "*.java" -o -name "*.xml"| xargs sed -i -e 's|todo.selenium|com.example.session.selenium|'

