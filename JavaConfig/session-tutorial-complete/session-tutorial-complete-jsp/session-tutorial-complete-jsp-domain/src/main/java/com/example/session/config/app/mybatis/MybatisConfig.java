package com.example.session.config.app.mybatis;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;

/**
 * Mybatis config.
 */
public class MybatisConfig {

    /**
     * Settings Mybatis config.
     * @return Configured {@link Configuration}
     */
    public static Configuration configuration() {
        Configuration configuration = new Configuration();
        setSettings(configuration);
        setTypeAliases(configuration.getTypeAliasRegistry());
        setTypeHandlers(configuration.getTypeHandlerRegistry());
        return configuration;
    }

    /**
     * Settings MyBatis behaves.
     * @param configuration Accepted at configuration
     */
    private static void setSettings(Configuration configuration) {
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLazyLoadingEnabled(true);
        configuration.setDefaultFetchSize(100);
        // configuration.setDefaultExecutorType(ExecutorType.REUSE);
        // configuration.setJdbcTypeForNull(JdbcType.NULL);
        // configuration.setLocalCacheScope(LocalCacheScope.STATEMENT);
    }

    /**
     * Settings type aliases.
     * @param typeAliasRegistry Accepted at configuration
     */
    private static void setTypeAliases(TypeAliasRegistry typeAliasRegistry) {
        typeAliasRegistry.registerAliases("com.example.session.domain.model");
        typeAliasRegistry.registerAliases(
                "com.example.session.domain.repository");
        // typeAliasRegistry.registerAliases("com.example.session.infra.mybatis.typehandler");
    }

    /**
     * Settings type handlers.
     * @param typeHandlerRegistry Accepted at configuration
     */
    private static void setTypeHandlers(
            TypeHandlerRegistry typeHandlerRegistry) {
        // typeHandlerRegistry.register("com.example.session.infra.mybatis.typehandler");
    }
}
