#!/bin/bash

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version
#${VERSION}=tutorial pj version

# settings of maven archetype catalog
if test `echo ${ARCHETYPE_VERSION} | tail -c8 ` = "RELEASE"; then
 ARCHETYPE_CATALOG=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases
else
 ARCHETYPE_CATALOG=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-snapshots
fi

cd target-project

# mybatis app multi
mvn archetype:generate -B \
 -DarchetypeCatalog=${ARCHETYPE_CATALOG} \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-multi-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=com.example.session \
 -DartifactId=session-tutorial-init \
 -Dversion=${VERSION}

cd ..

# make app
mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/repository/account
mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/repository/goods
mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/repository/order
mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/account
mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/goods
mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/order
mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/userdetails
mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/resources/com/example/session/domain/repository/account
mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/resources/com/example/session/domain/repository/goods
mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/resources/com/example/session/domain/repository/order

mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/account
mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/goods
mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/login
mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/validation
mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/webapp/resources/vendor/bootstrap-3.0.0/css
mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/account
mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/goods
mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/login

mkdir -p ./target-project/session-tutorial-init/session-tutorial-init-selenium/src/test/java/com/example/session/selenium/session

# cp security sources
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/Account.java ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/Cart.java ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/CartItem.java ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/Goods.java ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/Order.java ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/OrderLine.java ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/repository/account/AccountRepository.java ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/repository/account/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/repository/goods/GoodsRepository.java ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/repository/goods/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/repository/order/OrderRepository.java ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/repository/order/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/account/AccountService.java ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/account/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/goods/GoodsService.java ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/goods/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/order/EmptyCartOrderException.java ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/order/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/order/InvalidCartOrderException.java ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/order/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/order/OrderService.java ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/order/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/userdetails/AccountDetails.java ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/userdetails/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/userdetails/AccountDetailsService.java ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/userdetails/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/resources/com/example/session/domain/repository/account/AccountRepository.xml ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/resources/com/example/session/domain/repository/account/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/resources/com/example/session/domain/repository/goods/GoodsRepository.xml ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/resources/com/example/session/domain/repository/goods/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/resources/com/example/session/domain/repository/order/OrderRepository.xml ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/resources/com/example/session/domain/repository/order/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/resources/META-INF/dozer/order-mapping.xml ./target-project/session-tutorial-init/session-tutorial-init-domain/src/main/resources/META-INF/dozer/

cp -p ./session-tutorial-init/session-tutorial-init-env/src/main/resources/database/H2-dataload.sql ./target-project/session-tutorial-init/session-tutorial-init-env/src/main/resources/database/
cp -p ./session-tutorial-init/session-tutorial-init-env/src/main/resources/database/H2-schema.sql ./target-project/session-tutorial-init/session-tutorial-init-env/src/main/resources/database/

cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/account/AccountCreateController.java ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/account/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/account/AccountCreateForm.java ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/account/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/account/IllegalOperationException.java ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/account/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/account/IllegalOperationExceptionHandler.java ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/account/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/goods/GoodsController.java ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/goods/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/goods/GoodViewForm.java ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/goods/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/login/LoginController.java ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/login/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/validation/Confirm.java ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/validation/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/validation/ConfirmValidator.java ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/validation/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/webapp/resources/vendor/bootstrap-3.0.0/css/bootstrap.css ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/webapp/resources/vendor/bootstrap-3.0.0/css/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/account/createConfirm.jsp ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/account/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/account/createFinish.jsp ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/account/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/account/createForm.jsp ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/account/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/common/error/illegalOperationError.jsp ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/common/error/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/goods/showGoods.jsp ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/goods/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/goods/showGoodsDetail.jsp ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/goods/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/login/loginForm.jsp ./target-project/session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/login/


cp -p ./session-tutorial-init/session-tutorial-init-selenium/src/test/java/com/example/session/selenium/SessionTutorialTest.java ./target-project/session-tutorial-init/session-tutorial-init-selenium/src/test/java/com/example/session/selenium/session
cp -p -r ./common-test/resources/META-INF/spring ./target-project/session-tutorial-init/session-tutorial-init-selenium/src/test/resources/META-INF
cp -p -r ./common-test/java/selenium ./target-project/session-tutorial-init/session-tutorial-init-selenium/src/test/java/com/example/session
