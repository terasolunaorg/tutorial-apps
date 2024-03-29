package com.example.session.config.app;

import jakarta.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.core.JdbcTemplate;
import org.terasoluna.gfw.common.codelist.CodeList;
import org.terasoluna.gfw.common.codelist.JdbcCodeList;

/**
 * Bean definition regarding CodeLists.
 */
@Configuration
public class SessionTutorialInitThymeleafCodeListConfig {

    /**
     * JDBC fetchSize property.
     */
    @Value("${codelist.jdbc.fetchSize:1000}")
    private Integer fetchSize;

    /**
     * Bean of DataSource.
     */
    @Inject
    private DataSource dataSource;

    /**
     * Configure {@link JdbcTemplate} bean.
     * @return Bean of configured {@link JdbcTemplate}
     */
    @Bean("jdbcTemplateForCodeList")
    public JdbcTemplate jdbcTemplateForCodeList() {
        JdbcTemplate bean = new JdbcTemplate();
        bean.setDataSource(dataSource);
        bean.setFetchSize(fetchSize);
        return bean;
    }

    // Example for usage of AbstractJdbcCodeList

//  /**
//   * Common processing of {@link JdbcCodeList}.
//   * @return Bean of configured {@link JdbcCodeList}
//   */
//  private JdbcCodeList abstractJdbcCodeList() {
//      JdbcCodeList bean = new JdbcCodeList();
//      bean.setJdbcTemplate(jdbcTemplateForCodeList());
//      return bean;
//  }

//  /**
//  * Example for usage of {@link AbstractJdbcCodeList}.
//  * @return Bean of configured {@link JdbcCodeList}
//  */
//  @Bean("CL_SAMPLE")
//  public JdbcCodeList clSample() {
//      JdbcCodeList jdbcCodeList = abstractJdbcCodeList();
//      jdbcCodeList.setQuerySql(
//              "SELECT code, code_name FROM t_sample_codes ORDER BY code");
//      jdbcCodeList.setValueColumn("code");
//      jdbcCodeList.setLabelColumn("code_name");
//      return jdbcCodeList;
//  }

    /**
     * Configure {@link CodeList} bean.
     * @return Bean of configured {@link CodeList}
     */
    @Bean("CL_CATEGORIES")
    public CodeList CL_CATEGORIES() {
        JdbcCodeList bean = new JdbcCodeList();
        bean.setDataSource(dataSource);
        bean.setQuerySql(
                "SELECT category_id, category_name FROM category ORDER BY category_id");
        bean.setValueColumn("category_id");
        bean.setLabelColumn("category_name");
        return bean;
    }
}