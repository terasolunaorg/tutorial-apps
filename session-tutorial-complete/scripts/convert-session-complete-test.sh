#!/bin/bash

#selenium.properties
find ./target-project/session-tutorial-complete/session-tutorial-complete-selenium/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=session-tutorial-complete-web|'

find ./target-project/session-tutorial-complete -name "*.java" -o -name "*.xml"| xargs sed -i -e 's|todo.selenium|com.example.session.selenium|'

