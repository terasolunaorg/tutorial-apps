# add multipart config
__END_SERVLET_DEFINITION_LINE__i\
        <load-on-startup>1</load-on-startup>\
        <multipart-config>\
             <max-file-size>5242880</max-file-size>\
             <max-request-size>27262976</max-request-size>\
             <file-size-threshold>0</file-size-threshold>\
         </multipart-config>
__END_SERVLET_DEFINITION_LINE__d

# add definitions
__END_WEB_APP_DEFINITION_LINE__i\
\
    <servlet>\
        <servlet-name>H2Console</servlet-name>\
        <servlet-class>org.h2.server.web.WebServlet</servlet-class>\
        <init-param>\
            <param-name>-webAllowOthers</param-name>\
            <param-value>true</param-value>\
        </init-param>\
        <load-on-startup>2</load-on-startup>\
    </servlet>\
\
    <servlet-mapping>\
        <servlet-name>H2Console</servlet-name>\
        <url-pattern>/admin/h2/*</url-pattern>\
    </servlet-mapping>\
\
    <listener>\
        <listener-class>org.h2.server.web.DbStarter</listener-class>\
    </listener>\
\
    <context-param>\
        <param-name>db.url</param-name>\
        <param-value>jdbc:h2:mem:secure-login-test;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'classpath:logback-ddl.sql'</param-value>\
    </context-param>\
\
    <context-param>\
        <param-name>db.user</param-name>\
        <param-value>sa</param-value>\
    </context-param>\
\
    <context-param>\
        <param-name>db.password</param-name>\
        <param-value></param-value>\
    </context-param>\
\
    <context-param>\
        <param-name>db.tcpServer</param-name>\
        <param-value>-tcpAllowOthers -tcpPort 9202</param-value>\
    </context-param>

# add filter
__FILTERCHAIN_LINE__i\
        <filter-name>MultipartFilter</filter-name>\
        <filter-class>org.springframework.web.multipart.support.MultipartFilter</filter-class>\
    </filter>\
    <filter-mapping>\
            <filter-name>MultipartFilter</filter-name>\
            <url-pattern>/*</url-pattern>\
    </filter-mapping>\
\
    <filter>\
        <filter-name>inputValidationFilter</filter-name>\
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>\
    </filter>\
    <filter-mapping>\
        <filter-name>inputValidationFilter</filter-name>\
        <url-pattern>/*</url-pattern>\
    </filter-mapping>\
\
    <filter>\

# add error page
__END_ERRORPAGE_LINE__i\
    </error-page>\
    <error-page>\
        <exception-type>org.terasoluna.securelogin.app.common.filter.exception.InvalidCharacterException</exception-type>\
        <location>/WEB-INF/views/common/error/invalidCharacterError.jsp</location>
