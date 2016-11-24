#!/bin/bash

# settings target project
target[0]=./target-project/todo-api
target[1]=./target-project/todo-api-mybatis3
target[2]=./target-project/todo-api-mybatis3-multi
target[3]=./target-project/todo-api-jpa
target[4]=./target-project/todo-api-jpa-multi

# web.xml
for i in "${target[*]}";do
find $i -type f -name 'web.xml' | xargs sed -i -e 's|<servlet>|\
    <!-- (1) -->\
    <servlet>\
        <servlet-name>restApiServlet</servlet-name>\
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>\
        <init-param>\
            <param-name>contextConfigLocation</param-name>\
            <!-- ApplicationContext for Spring MVC (REST) -->\
            <param-value>classpath*:META-INF/spring/spring-mvc-rest.xml</param-value>\
        </init-param>\
        <load-on-startup>1</load-on-startup>\
    </servlet>\
\
    <!-- (2) -->\
    <servlet-mapping>\
        <servlet-name>restApiServlet</servlet-name>\
        <url-pattern>/api/v1/*</url-pattern>\
    </servlet-mapping>\
\
    <servlet>|'
done

# spring-mvc-rest.xml
for i in "${target[*]}";do
find $i -type f -name "spring-mvc.xml" -print0 | while read -r -d '' file; do cp -i "$file" "${file%%spring-mvc.xml}spring-mvc-rest.xml"; done
done

# spring-mvc-rest.xml
for i in "${target[*]}";do
find $i -type f -name 'spring-mvc-rest.xml' | xargs sed -i -e 's|</mvc:argument-resolvers>|\
        </mvc:argument-resolvers>\
        <mvc:message-converters register-defaults="false">\
            <!-- (1) -->\
            <bean\n\
                class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">\
                <!-- (2) -->\
                <property name="objectMapper">\n\
                    <bean class="com.fasterxml.jackson.databind.ObjectMapper">\
                        <property name="dateFormat">\
                            <!-- (3) -->\
                            <bean class="com.fasterxml.jackson.databind.util.StdDateFormat" />\
                        </property>\
                    </bean>\
                </property>\
            </bean>\
        </mvc:message-converters>|'
done

# spring-mvc-rest.xml
for i in "${target[*]}";do
find $i -type f -name 'spring-mvc-rest.xml' | xargs sed -i -e 's|<context:component-scan base-package="todo.app" />|\
    <context:component-scan base-package="todo.api" />|'
done

# spring-security,xml
for i in "${target[*]}";do
find $i -type f -name 'spring-security.xml' | xargs sed -i -e 's|<sec:http pattern="/resources/\*\*" security="none"/>|\
    <sec:http pattern="/resources/**" security="none" />\
\
    <!-- (1) -->\
    <sec:http pattern="/api/v1/**" security="none" />\
|'
done
