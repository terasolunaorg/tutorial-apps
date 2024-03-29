package com.example.securelogin.config.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.passay.CharacterCharacteristicsRule;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordGenerator;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.UsernameRule;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.terasoluna.gfw.common.exception.ExceptionCodeResolver;
import org.terasoluna.gfw.common.exception.ExceptionLogger;
import org.terasoluna.gfw.common.exception.SimpleMappingExceptionCodeResolver;
import org.terasoluna.gfw.web.exception.ExceptionLoggingFilter;

import com.example.securelogin.app.common.filter.InputValidationFilter;
import com.example.securelogin.app.common.validation.rule.EncodedPasswordHistoryRule;
import com.example.securelogin.domain.common.scheduled.TempFileCleaner;
import com.example.securelogin.domain.common.scheduled.UnnecessaryReissueInfoCleaner;

/**
 * Application context.
 */
@Configuration
@EnableAspectJAutoProxy
@Import({ SecureLoginThymeleafDomainConfig.class,
        SecureLoginReissueInfoCleanerConfig.class,
        SecureLoginTempFileCleanerConfig.class })
@EnableScheduling
public class ApplicationContextConfig {

    /**
     * security.prohibitedChars property.
     */
    @Value("${app.security.prohibitedChars}")
    private char[] prohibitedChars;

    /**
     * security.prohibitedCharsForFileName property.
     */
    @Value("${app.security.prohibitedCharsForFileName}")
    private char[] prohibitedCharsForFileName;

    /**
     * security.passwordMinimumLength property.
     */
    @Value("${security.passwordMinimumLength}")
    private Integer passwordMinimumLength;

    /**
     * security.reissueInfoCleanupSeconds property.
     */
    @Value("${security.reissueInfoCleanupSeconds}")
    private long reissueInfoCleanupSeconds;

    /**
     * security.tempFileCleanupSeconds property.
     */
    @Value("${security.tempFileCleanupSeconds}")
    private long tempFileCleanupSeconds;


    /**
     * Configure {@link PasswordEncoder} bean.
     * @return Bean of configured {@link DelegatingPasswordEncoder}
     */
    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> idToPasswordEncoder = new HashMap<>();
        idToPasswordEncoder.put("pbkdf2", pbkdf2PasswordEncoder());
        idToPasswordEncoder.put("bcrypt", bCryptPasswordEncoder());
        /* When using commented out PasswordEncoders, you need to add bcprov-jdk18on.jar to the dependency.
        idToPasswordEncoder.put("argon2", argon2PasswordEncoder());
        idToPasswordEncoder.put("scrypt", sCryptPasswordEncoder());
        */
        return new DelegatingPasswordEncoder("pbkdf2", idToPasswordEncoder);
    }

    /**
     * Configure {@link Pbkdf2PasswordEncoder} bean.
     * @return Bean of configured {@link Pbkdf2PasswordEncoder}
     */
    @Bean
    public Pbkdf2PasswordEncoder pbkdf2PasswordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    /**
     * Configure {@link BCryptPasswordEncoder} bean.
     * @return Bean of configured {@link BCryptPasswordEncoder}
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* When using commented out PasswordEncoders, you need to add bcprov-jdk18on.jar to the dependency.
    @Bean
    public Argon2PasswordEncoder argon2PasswordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
    @Bean
    public SCryptPasswordEncoder sCryptPasswordEncoder() {
        return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
    */

    /**
     * Configure {@link PropertySourcesPlaceholderConfigurer} bean.
     * @param properties Property files to be read
     * @return Bean of configured {@link PropertySourcesPlaceholderConfigurer}
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(
            @Value("classpath*:/META-INF/spring/*.properties") Resource... properties) {
        PropertySourcesPlaceholderConfigurer bean = new PropertySourcesPlaceholderConfigurer();
        bean.setLocations(properties);
        return bean;
    }

    /**
     * Configure {@link MessageSource} bean.
     * @return Bean of configured {@link ResourceBundleMessageSource}
     */
    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
        bean.setBasenames("i18n/application-messages");
        return bean;
    }

    /**
     * Configure {@link ExceptionCodeResolver} bean.
     * @return Bean of configured {@link SimpleMappingExceptionCodeResolver}
     */
    @Bean("exceptionCodeResolver")
    public ExceptionCodeResolver exceptionCodeResolver() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("ResourceNotFoundException", "e.sl.fw.5001");
        map.put("MultipartException", "e.sl.fw.6001");
        map.put("InvalidTransactionTokenException", "e.sl.fw.7001");
        map.put("BusinessException", "e.sl.fw.8001");
        map.put(".DataAccessException", "e.sl.fw.9002");
        SimpleMappingExceptionCodeResolver bean = new SimpleMappingExceptionCodeResolver();
        bean.setExceptionMappings(map);
        bean.setDefaultExceptionCode("e.sl.fw.9001");
        return bean;
    }

    /**
     * Configure {@link ExceptionLogger} bean.
     * @return Bean of configured {@link ExceptionLogger}
     */
    @Bean("exceptionLogger")
    public ExceptionLogger exceptionLogger() {
        ExceptionLogger bean = new ExceptionLogger();
        bean.setExceptionCodeResolver(exceptionCodeResolver());
        return bean;
    }

    /**
     * Configure {@link ExceptionLoggingFilter} bean.
     * @return Bean of configured {@link ExceptionLoggingFilter}
     */
    @Bean("exceptionLoggingFilter")
    public ExceptionLoggingFilter exceptionLoggingFilter() {
        ExceptionLoggingFilter bean = new ExceptionLoggingFilter();
        bean.setExceptionLogger(exceptionLogger());
        return bean;
    }

    /**
     * Configure {@link InputValidationFilter} bean.
     * @return Bean of configured {@link InputValidationFilter}
     */
    @Bean(name = "inputValidationFilter")
    public InputValidationFilter inputValidationFilter() {
        InputValidationFilter bean = new InputValidationFilter(prohibitedChars, prohibitedCharsForFileName);
        return bean;
    }

    /**
     * Configure {@link LengthRule} bean.
     * @return Bean of configured {@link LengthRule}
     */
    @Bean(name = "lengthRule")
    public LengthRule lengthRule() {
        LengthRule bean = new LengthRule();
        bean.setMinimumLength(passwordMinimumLength);
        return bean;
    }

    /**
     * Configure {@link LengthRule} bean.
     * @return Bean of configured {@link LengthRule}
     */
    @Bean(name = "upperCaseRule")
    public CharacterRule upperCaseRule() {
        return new CharacterRule(EnglishCharacterData.UpperCase, 1);
    }

    /**
     * Configure {@link CharacterRule} bean.
     * @return Bean of configured {@link CharacterRule}
     */
    @Bean(name = "lowerCaseRule")
    public CharacterRule lowerCaseRule() {
        return new CharacterRule(EnglishCharacterData.LowerCase, 1);
    }

    /**
     * Configure {@link CharacterRule} bean.
     * @return Bean of configured {@link CharacterRule}
     */
    @Bean(name = "digitRule")
    public CharacterRule digitRule() {
        return new CharacterRule(EnglishCharacterData.Digit, 1);
    }

    /**
     * Configure {@link CharacterRule} bean.
     * @return Bean of configured {@link CharacterRule}
     */
    @Bean(name = "specialCharacterRule")
    public CharacterRule specialCharacterRule() {
        return new CharacterRule(EnglishCharacterData.Special, 1);
    }

    /**
     * Configure {@link CharacterCharacteristicsRule} bean.
     * @return Bean of configured {@link CharacterCharacteristicsRule}
     */
    @Bean(name = "characterCharacteristicsRule")
    public CharacterCharacteristicsRule characterCharacteristicsRule() {
        CharacterCharacteristicsRule bean = new CharacterCharacteristicsRule();
        List<CharacterRule> rules = new ArrayList<>();
        rules.add(upperCaseRule());
        rules.add(lowerCaseRule());
        rules.add(digitRule());
        rules.add(specialCharacterRule());
        bean.setRules(rules);
        bean.setNumberOfCharacteristics(3);
        return bean;
    }

    /**
     * Configure {@link UsernameRule} bean.
     * @return Bean of configured {@link UsernameRule}
     */
    @Bean(name = "usernameRule")
    public UsernameRule usernameRule() {
        return new UsernameRule();
    }

    /**
     * Configure {@link EncodedPasswordHistoryRule} bean.
     * @return Bean of configured {@link EncodedPasswordHistoryRule}
     */
    @Bean(name = "encodedPasswordHistoryRule")
    public EncodedPasswordHistoryRule encodedPasswordHistoryRule() {
        return new EncodedPasswordHistoryRule(passwordEncoder());
    }

    /**
     * Configure {@link PasswordValidator} bean.
     * @return Bean of configured {@link PasswordValidator}
     */
    @Bean(name = "characteristicPasswordValidator")
    public PasswordValidator characteristicPasswordValidator() {
        List<Rule> rules = new ArrayList<>();
        rules.add(lengthRule());
        rules.add(characterCharacteristicsRule());
        rules.add(usernameRule());
        return new PasswordValidator(rules);
    }

    /**
     * Configure {@link PasswordValidator} bean.
     * @return Bean of configured {@link PasswordValidator}
     */
    @Bean(name = "encodedPasswordHistoryValidator")
    public PasswordValidator encodedPasswordHistoryValidator() {
        List<Rule> rules = new ArrayList<>();
        rules.add(encodedPasswordHistoryRule());
        return new PasswordValidator(rules);
    }

    /**
     * Configure {@link PasswordValidator} bean.
     * @return Bean of configured {@link PasswordValidator}
     */
    @Bean(name = "passwordGenerator")
    public PasswordGenerator passwordGenerator() {
        return new PasswordGenerator();
    }

    /**
     * Configure {@link CharacterRule} list.
     * @return List of configured {@link CharacterRule}
     */
    @Bean(name = "passwordGenerationRules")
    public List<CharacterRule> passwordGenerationRules() {
        List<CharacterRule> rules = new ArrayList<>();
        rules.add(upperCaseRule());
        rules.add(lowerCaseRule());
        rules.add(digitRule());
        return rules;
    }

    /**
     * Configure expiredReissueInfoCleaner.
     * @return Bean of configured {@link UnnecessaryReissueInfoCleaner}
     */
    @Bean(name = "expiredReissueInfoCleaner")
    public UnnecessaryReissueInfoCleaner expiredReissueInfoCleaner() {
        return new UnnecessaryReissueInfoCleaner();
    }

    /**
     * Configure expiredReissueInfoCleanTrigger.
     * @return Bean of configured {@link PeriodicTrigger}
     */
    @Bean(name = "expiredReissueInfoCleanTrigger")
    public PeriodicTrigger expiredReissueInfoCleanTrigger() {
        return new PeriodicTrigger(Duration.of(reissueInfoCleanupSeconds,
                TimeUnit.SECONDS.toChronoUnit()));
    }

    /**
     * Configure {@link Executor} bean.
     * @return Bean of configured {@link Executor}
     */
    @Bean("reissueInfoCleanupTaskScheduler")
    public Executor reissueInfoCleanupTaskScheduler() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * Configure tempFileCleaner.
     * @return Bean of configured {@link TempFileCleaner}
     */
    @Bean(name = "tempFileCleaner")
    public TempFileCleaner tempFileCleaner() {
        return new TempFileCleaner();
    }

    /**
     * Configure tempFileCleanTrigger.
     * @return Bean of configured {@link PeriodicTrigger}
     */
    @Bean(name = "tempFileCleanTrigger")
    public PeriodicTrigger tempFileCleanTrigger() {
        return new PeriodicTrigger(Duration.of(tempFileCleanupSeconds,
                TimeUnit.SECONDS.toChronoUnit()));
    }

    /**
     * Configure {@link Executor} bean.
     * @return Bean of configured {@link Executor}
     */
    @Bean("tempFileTaskScheduler")
    public Executor tempFileTaskScheduler() {
        return Executors.newSingleThreadScheduledExecutor();
    }

}