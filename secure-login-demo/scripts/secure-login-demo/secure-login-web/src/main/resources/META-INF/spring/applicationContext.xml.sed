# add bean definitions
__END_BEAN_DEFINITION_LINE__i\
\
	<bean id="lengthRule" class="org.passay.LengthRule">\
		<property name="minimumLength" value="${security.passwordMinimumLength}" />\
	</bean>\
\
	<bean id="upperCaseRule" class="org.passay.CharacterRule">\
		<constructor-arg name="data">\
			<util:constant static-field="org.passay.EnglishCharacterData.UpperCase" />\
		</constructor-arg>\
		<constructor-arg name="num" value="1" />\
	</bean>\
\
	<bean id="lowerCaseRule" class="org.passay.CharacterRule">\
		<constructor-arg name="data">\
			<util:constant static-field="org.passay.EnglishCharacterData.LowerCase" />\
		</constructor-arg>\
		<constructor-arg name="num" value="1" />\
	</bean>\
\
	<bean id="digitRule" class="org.passay.CharacterRule">\
		<constructor-arg name="data">\
			<util:constant static-field="org.passay.EnglishCharacterData.Digit" />\
		</constructor-arg>\
		<constructor-arg name="num" value="1" />\
	</bean>\
\
	<bean id="specialCharacterRule" class="org.passay.CharacterRule">\
		<constructor-arg name="data">\
			<util:constant static-field="org.passay.EnglishCharacterData.Special" />\
		</constructor-arg>\
		<constructor-arg name="num" value="1" />\
	</bean>\
\
	<bean id="characterCharacteristicsRule" class="org.passay.CharacterCharacteristicsRule">\
		<property name="rules">\
			<list>\
				<ref bean="upperCaseRule" />\
				<ref bean="lowerCaseRule" />\
				<ref bean="digitRule" />\
				<ref bean="specialCharacterRule" />\
			</list>\
		</property>\
		<property name="numberOfCharacteristics" value="3" />\
	</bean>\
\
	<bean id="usernameRule" class="org.passay.UsernameRule" />\
\
	<bean id="encodedPasswordHistoryRule"\
		class="org.terasoluna.securelogin.app.common.validation.rule.EncodedPasswordHistoryRule">\
		<constructor-arg name="passwordEncoder" ref="passwordEncoder" />\
	</bean>\
\
	<bean id="characteristicPasswordValidator" class="org.passay.PasswordValidator">\
		<constructor-arg name="rules">\
			<list>\
				<ref bean="lengthRule" />\
				<ref bean="characterCharacteristicsRule" />\
				<ref bean="usernameRule" />\
			</list>\
		</constructor-arg>\
	</bean>\
\
	<bean id="encodedPasswordHistoryValidator" class="org.passay.PasswordValidator">\
		<constructor-arg name="rules">\
			<list>\
				<ref bean="encodedPasswordHistoryRule" />\
			</list>\
		</constructor-arg>\
	</bean>\
\
	<bean id="passwordGenerator" class="org.passay.PasswordGenerator" />\
\
	<util:list id="passwordGenerationRules">\
		<ref bean="upperCaseRule" />\
		<ref bean="lowerCaseRule" />\
		<ref bean="digitRule" />\
	</util:list>\
\
	<bean id="expiredReissueInfoCleaner"\
		class="org.terasoluna.securelogin.domain.common.scheduled.UnnecessaryReissueInfoCleaner" />\
\
	<task:scheduler id="reissueInfoCleanupTaskScheduler" />\
\
	<task:scheduled-tasks scheduler="reissueInfoCleanupTaskScheduler">\
		<task:scheduled ref="expiredReissueInfoCleaner" method="cleanup"\
			fixed-delay="${security.reissueInfoCleanupSeconds}" />\
	</task:scheduled-tasks>

# add namespace
__END_NAMESPACE_LINE__i\
	xmlns:task="http://www.springframework.org/schema/task"\
	xmlns:util="http://www.springframework.org/schema/util"\

# add schemaLocation
__SCHEMALOCATION_LINE__i\
        http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task.xsd\
        http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd

# replace message kays
s/e.xx.fw.5001/e.sl.fw.5001/g
s/e.xx.fw.7001/e.sl.fw.7001/g
s/e.xx.fw.8001/e.sl.fw.8001/g
s/e.xx.fw.9001/e.sl.fw.9001/g
