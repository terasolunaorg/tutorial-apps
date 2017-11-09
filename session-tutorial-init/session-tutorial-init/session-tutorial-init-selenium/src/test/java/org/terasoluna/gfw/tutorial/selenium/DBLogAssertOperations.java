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
package org.terasoluna.gfw.tutorial.selenium;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.StringUtils;

public class DBLogAssertOperations {
    @Value("${selenium.waitForLogAssertion.offsetSeconds:0}")
    int offsetSecondsOfWaitForLogAssertion;

    private final NamedParameterJdbcOperations jdbcOperations;

    public DBLogAssertOperations(JdbcOperations jdbcOperations) {
        this.jdbcOperations = new NamedParameterJdbcTemplate(jdbcOperations);
    }

    /**
     * NamedParameterJdbcOperationsを返却する。
     * <p>
     * 固有の検証をしたい場合は、このメソッドで取得したNamedParameterJdbcOperationsを使用してSQLを発行してください。
     * </p>
     * @return NamedParameterJdbcOperations
     */
    public NamedParameterJdbcOperations getJdbcOperations() {
        return jdbcOperations;
    }

    /**
     * テストケース実行時に、ERRORレベルのログが出力されていない事を検証する。
     */
    public void assertNotContainsError() {
        assertNotContainsError(null);
    }

    /**
     * 指定したX-Trackのリクエスト内で、ERRORレベルのログが出力されていない事を検証する。
     * <p>
     * X-Trackで対象を絞り込むことが可能である。
     * </p>
     * @param xTrack リクエストを一意に特定するための値（絞り込みを行わない場合はnullを指定）
     */
    public void assertNotContainsError(String xTrack) {
        assertNotContainsLevels(xTrack, "ERROR");
    }

    /**
     * テストケース実行時に、WARNレベルのログが出力されていない事を検証する。
     */
    public void assertNotContainsWarn() {
        assertNotContainsWarn(null);
    }

    /**
     * 指定したX-Trackのリクエスト内で、WARNレベルのログが出力されていない事を検証する。
     * <p>
     * X-Trackで対象を絞り込むことが可能である。
     * </p>
     * @param xTrack リクエストを一意に特定するための値（絞り込みを行わない場合はnullを指定）
     */
    public void assertNotContainsWarn(String xTrack) {
        assertNotContainsLevels(xTrack, "WARN");
    }

    /**
     * テストケース実行時に、WARN又はERRORレベルのログが出力されていない事を検証する。
     */
    public void assertNotContainsWarnAndError() {
        assertNotContainsWarnAndError(null);
    }

    /**
     * 指定したX-Trackのリクエスト内で、WARN又はERRORレベルのログが出力されていない事を検証する。
     * <p>
     * X-Trackで対象を絞り込むことが可能である。
     * </p>
     * @param xTrack リクエストを一意に特定するための値（絞り込みを行わない場合はnullを指定）
     */
    public void assertNotContainsWarnAndError(String xTrack) {
        assertNotContainsLevels(xTrack, "WARN", "ERROR");
    }

    /**
     * ログの検証を行うまで一定時間待機する。
     * <p>
     * テストケース終了直後に出力されたログがDBに格納されるまでのタイムラグ分待機する。暫定で50msecにしている。
     * </p>
     */
    public void waitForAssertion() {
        try {
            long adjustedWaitTime = (50 + TimeUnit.SECONDS.toMillis(
                    offsetSecondsOfWaitForLogAssertion));
            TimeUnit.MILLISECONDS.sleep(adjustedWaitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * ログの検証を行うまで一定時間待機する。
     * <p>
     * テストケース終了直後に出力されたログがDBに格納されるまでのタイムラグ分待機する。
     * </p>
     * @param waitTime 待機時間(msec)
     */
    public void waitForAssertion(long waitTime) {
        try {
            long adjustedWaitTime = waitTime + TimeUnit.SECONDS.toMillis(
                    offsetSecondsOfWaitForLogAssertion);
            TimeUnit.MILLISECONDS.sleep(adjustedWaitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定したメッセージに完全一致するログが出力されていることを検証する。
     * <p>
     * ロガー名を指定することで対象を絞り込むことが可能である。
     * </p>
     * @param loggerName 出力元のロガー名（絞り込みを行わない場合はnullを指定）
     * @param message メッセージ（必須）
     */
    public void assertContainsByMessage(String loggerName, String message) {
        assertContainsByMessage(null, loggerName, message);
    }

    /**
     * 指定したメッセージに完全一致するログが出力されていることを検証する。
     * <p>
     * X-Track,出力元のロガー名を指定することで対象を絞り込むことが可能である。
     * </p>
     * @param xTrack リクエストを一意に特定するための値（絞り込みを行わない場合はnullを指定）
     * @param loggerName 出力元のロガー名（絞り込みを行わない場合はnullを指定）
     * @param message メッセージ（必須）
     */
    public void assertContainsByMessage(String xTrack, String loggerName,
            String message) {
        long count = getCountInLogContainsByMessage(xTrack, loggerName,
                message);
        assertThat(count, is(1L));
    }

    /**
     * 指定したメッセージパターン(正規表現)に一致するログが出力されていることを検証する。
     * <p>
     * 出力元のロガー名パターンを指定することで対象を絞り込むことが可能である。
     * </p>
     * @param loggerNamePattern 出力元のロガー名のパターン（絞り込みを行わない場合はnullを指定）
     * @param messagePattern メッセージのパターン（必須）
     */
    public void assertContainsByRegexMessage(String loggerNamePattern,
            String messagePattern) {
        assertContainsByRegexMessage(null, loggerNamePattern, messagePattern);
    }

    /**
     * 指定したメッセージパターン(正規表現)に一致するログが出力されていることを検証する。
     * <p>
     * X-Track,出力元のロガー名パターンを指定することで対象を絞り込むことが可能である。
     * </p>
     * @param xTrack リクエストを一意に特定するための値（絞り込みを行わない場合はnullを指定）
     * @param loggerNamePattern 出力元のロガー名のパターン（絞り込みを行わない場合はnullを指定）
     * @param messagePattern メッセージのパターン（必須）
     */
    public void assertContainsByRegexMessage(String xTrack,
            String loggerNamePattern, String messagePattern) {
        long count = getCountInLogContainsByRegexMessage(xTrack,
                loggerNamePattern, messagePattern);
        assertThat(count, is(1L));
    }

    /**
     * 指定したメッセージパターン(正規表現)に紐付く例外ログが出力されていることを検証する。
     * <p>
     * X-Track,出力元のロガー名パターンを指定することで対象を絞り込むことが可能である。
     * </p>
     * @param xTrack リクエストを一意に特定するための値（絞り込みを行わない場合はnullを指定）
     * @param loggerNamePattern 出力元のロガー名のパターン（絞り込みを行わない場合はnullを指定）
     * @param messagePattern メッセージのパターン（必須）
     * @param exceptionMessagePattern 例外情報のパターン(必須)
     */
    public void assertContainsByRegexExceptionMessage(String xTrack,
            String loggerNamePattern, String messagePattern,
            String exceptionMessagePattern) {

        StringBuilder sql = new StringBuilder();
        StringBuilder where = new StringBuilder();
        sql.append("SELECT COUNT(e.*) FROM logging_event e");
        where.append(" WHERE e.formatted_message REGEXP :message");

        sql.append(
                " JOIN logging_event_exception ee ON ee.event_id = e.event_id");
        where.append(
                " AND ee.I = '0' AND ee.TRACE_LINE REGEXP :exceptionMessage");

        if (StringUtils.hasText(xTrack)) {
            sql.append(
                    " JOIN logging_event_property ep ON ep.event_id = e.event_id");
            where.append(
                    " AND ep.mapped_key = 'X-Track' AND ep.mapped_value = :xTrack");
        }
        if (StringUtils.hasText(loggerNamePattern)) {
            where.append(" AND e.logger_name REGEXP :loggerName");
        }
        sql.append(where);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("xTrack", xTrack);
        params.addValue("loggerName", loggerNamePattern);
        params.addValue("message", messagePattern);
        params.addValue("exceptionMessage", exceptionMessagePattern);
        Long count = jdbcOperations.queryForObject(sql.toString(), params,
                Long.class);
        assertThat(count, is(1L));
    }

    /**
     * スタックトレースに指定した文字列パターン(正規表現)が出力されていることを検証する。
     * @param stackTracePattern スタックトレースの文字列パターン(必須)
     */
    public void assertContainsByRegexStackTrace(String stackTracePattern) {

        StringBuilder sql = new StringBuilder();
        StringBuilder where = new StringBuilder();
        sql.append("SELECT COUNT(e.*) FROM logging_event_exception e");
        where.append(" WHERE e.TRACE_LINE REGEXP :stackTrace");
        sql.append(where);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("stackTrace", stackTracePattern);
        Long count = jdbcOperations.queryForObject(sql.toString(), params,
                Long.class);
        assertThat(count, is(1L));
    }

    /**
     * 指定したメッセージに完全一致するログが出力されていないことを検証する。
     * <p>
     * ロガー名を指定することで対象を絞り込むことが可能である。
     * </p>
     * @param loggerName 出力元のロガー名（絞り込みを行わない場合はnullを指定）
     * @param message メッセージ（必須）
     */
    public void assertNotContainsByMessage(String loggerName, String message) {
        assertContainsByMessage(null, loggerName, message);
    }

    /**
     * 指定したメッセージに完全一致するログが出力されていないことを検証する。
     * <p>
     * X-Track,出力元のロガー名を指定することで対象を絞り込むことが可能である。
     * </p>
     * @param xTrack リクエストを一意に特定するための値（絞り込みを行わない場合はnullを指定）
     * @param loggerName 出力元のロガー名（絞り込みを行わない場合はnullを指定）
     * @param message メッセージ（必須）
     */
    public void assertNotContainsByMessage(String xTrack, String loggerName,
            String message) {
        long count = getCountInLogContainsByMessage(xTrack, loggerName,
                message);
        assertThat(count, is(0L));
    }

    /**
     * 指定したメッセージパターン(正規表現)に一致するログが出力されていないことを検証する。
     * <p>
     * 出力元のロガー名パターンを指定することで対象を絞り込むことが可能である。
     * </p>
     * @param loggerNamePattern 出力元のロガー名のパターン（絞り込みを行わない場合はnullを指定）
     * @param messagePattern メッセージのパターン（必須）
     */
    public void assertNotContainsByRegexMessage(String loggerNamePattern,
            String messagePattern) {
        assertContainsByRegexMessage(null, loggerNamePattern, messagePattern);
    }

    /**
     * 指定したメッセージパターン(正規表現)に一致するログが出力されていないことを検証する。
     * <p>
     * X-Track,出力元のロガー名パターンを指定することで対象を絞り込むことが可能である。
     * </p>
     * @param xTrack リクエストを一意に特定するための値（絞り込みを行わない場合はnullを指定）
     * @param loggerNamePattern 出力元のロガー名のパターン（絞り込みを行わない場合はnullを指定）
     * @param messagePattern メッセージのパターン（必須）
     */
    public void assertNotContainsByRegexMessage(String xTrack,
            String loggerNamePattern, String messagePattern) {
        long count = getCountInLogContainsByRegexMessage(xTrack,
                loggerNamePattern, messagePattern);
        assertThat(count, is(0L));
    }

    /**
     * 指定したメッセージパターン(正規表現)に一致するログを取得する（出力された順）。
     * <p>
     * X-Track,出力元のロガー名パターンを指定することで対象を絞り込むことが可能である。
     * </p>
     * @param xTrack リクエストを一意に特定するための値（絞り込みを行わない場合はnullを指定）
     * @param loggerNamePattern 出力元のロガー名のパターン（絞り込みを行わない場合はnullを指定）
     * @param messagePattern メッセージのパターン（必須）
     * @return　指定したメッセージパターン(正規表現)に一致するログ
     */
    public List<String> getLogByRegexMessage(String xTrack,
            String loggerNamePattern, String messagePattern) {

        StringBuilder sql = new StringBuilder();
        StringBuilder where = new StringBuilder();
        sql.append("SELECT e.formatted_message FROM logging_event e");
        where.append(" WHERE e.formatted_message REGEXP :message");
        if (StringUtils.hasText(xTrack)) {
            sql.append(
                    " JOIN logging_event_property ep ON ep.event_id = e.event_id");
            where.append(
                    " AND ep.mapped_key = 'X-Track' AND ep.mapped_value = :xTrack");
        }
        if (StringUtils.hasText(loggerNamePattern)) {
            where.append(" AND e.logger_name REGEXP :loggerName");
        }
        StringBuilder orderBy = new StringBuilder();
        orderBy.append(" ORDER BY e.event_id ASC");
        sql.append(where).append(orderBy);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("xTrack", xTrack);
        params.addValue("loggerName", loggerNamePattern);
        params.addValue("message", messagePattern);

        return jdbcOperations.queryForList(sql.toString(), params,
                String.class);
    }

    protected void assertNotContainsLevels(String xTrack, String... levels) {

        StringBuilder sql = new StringBuilder();
        StringBuilder where = new StringBuilder();
        sql.append("SELECT COUNT(e.*) FROM logging_event e");
        where.append(" WHERE e.level_string IN (");
        for (int i = 0; i < levels.length; i++) {
            if (0 < i) {
                where.append(",");
            }
            where.append(":level").append(i);
        }
        where.append(")");
        if (StringUtils.hasText(xTrack)) {
            sql.append(
                    " JOIN logging_event_property ep ON ep.event_id = e.event_id");
            where.append(
                    " AND ep.mapped_key = 'X-Track' AND ep.mapped_value = :xTrack");
        }
        sql.append(where);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("xTrack", xTrack);
        for (int i = 0; i < levels.length; i++) {
            params.addValue("level" + i, levels[i]);
        }
        Long count = jdbcOperations.queryForObject(sql.toString(), params,
                Long.class);
        assertThat(count, is(0L));
    }

    private long getCountInLogContainsByRegexMessage(String xTrack,
            String loggerNamePattern, String messagePattern) {

        StringBuilder sql = new StringBuilder();
        StringBuilder where = new StringBuilder();
        sql.append("SELECT COUNT(e.*) FROM logging_event e");
        where.append(" WHERE e.formatted_message REGEXP :message");
        if (StringUtils.hasText(xTrack)) {
            sql.append(
                    " JOIN logging_event_property ep ON ep.event_id = e.event_id");
            where.append(
                    " AND ep.mapped_key = 'X-Track' AND ep.mapped_value = :xTrack");
        }
        if (StringUtils.hasText(loggerNamePattern)) {
            where.append(" AND e.logger_name REGEXP :loggerName");
        }
        sql.append(where);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("xTrack", xTrack);
        params.addValue("loggerName", loggerNamePattern);
        params.addValue("message", messagePattern);

        return jdbcOperations.queryForObject(sql.toString(), params,
                Long.class);

    }

    private long getCountInLogContainsByMessage(String xTrack,
            String loggerName, String message) {
        StringBuilder sql = new StringBuilder();
        StringBuilder where = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM logging_event e");
        where.append(" WHERE e.formatted_message = :message");
        if (StringUtils.hasText(xTrack)) {
            sql.append(
                    "JOIN logging_event_property ep ON ep.event_id = e.event_id AND ep.mapped");
            where.append(
                    " AND ep.mapped_key = 'X-Track' AND ep.mapped_value = :xTrack");
        }
        if (StringUtils.hasText(loggerName)) {
            where.append(" AND e.logger_name = :loggerName");
        }
        sql.append(where);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("xTrack", xTrack);
        params.addValue("loggerName", loggerName);
        params.addValue("message", message);
        return jdbcOperations.queryForObject(sql.toString(), params,
                Long.class);
    }
}
