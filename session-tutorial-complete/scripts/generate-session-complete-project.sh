#!/bin/bash
# create complete project from init project

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version
#${VERSION}=tutorial pj version

cd target-project

# mybatis app multi
mvn archetype:generate -B \
 -DarchetypeCatalog=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-multi-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=com.example.session \
 -DartifactId=session-tutorial-complete \
 -Dversion=${VERSION}

cd ..

###################  init  ###################

# make app
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/repository/account
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/repository/goods
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/repository/order
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/account
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/goods
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/order
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/userdetails
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/resources/com/example/session/domain/repository/account
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/resources/com/example/session/domain/repository/goods
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/resources/com/example/session/domain/repository/order

mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/goods
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/login
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/validation
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/resources/vendor/bootstrap-3.0.0/css
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/goods
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/login

mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-selenium/src/test/java/com/example/session/selenium/session

###################  tutorial flow  ###################

mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/cart
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/config
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/order
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/goods
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/cart
mkdir -p ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/order


###################  init  ###################

# cp security sources
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/Account.java ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/Cart.java ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/CartItem.java ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/Goods.java ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/Order.java ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/model/OrderLine.java ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/repository/account/AccountRepository.java ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/repository/account/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/repository/goods/GoodsRepository.java ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/repository/goods/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/repository/order/OrderRepository.java ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/repository/order/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/account/AccountService.java ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/account/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/goods/GoodsService.java ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/goods/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/order/EmptyCartOrderException.java ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/order/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/order/InvalidCartOrderException.java ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/order/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/order/OrderService.java ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/order/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/userdetails/AccountDetails.java ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/userdetails/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/java/com/example/session/domain/service/userdetails/AccountDetailsService.java ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/userdetails/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/resources/com/example/session/domain/repository/account/AccountRepository.xml ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/resources/com/example/session/domain/repository/account/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/resources/com/example/session/domain/repository/goods/GoodsRepository.xml ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/resources/com/example/session/domain/repository/goods/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/resources/com/example/session/domain/repository/order/OrderRepository.xml ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/resources/com/example/session/domain/repository/order/
cp -p ./session-tutorial-init/session-tutorial-init-domain/src/main/resources/META-INF/dozer/order-mapping.xml ./target-project/session-tutorial-complete/session-tutorial-complete-domain/src/main/resources/META-INF/dozer/

cp -p ./session-tutorial-init/session-tutorial-init-env/src/main/resources/database/H2-dataload.sql ./target-project/session-tutorial-complete/session-tutorial-complete-env/src/main/resources/database/
cp -p ./session-tutorial-init/session-tutorial-init-env/src/main/resources/database/H2-schema.sql ./target-project/session-tutorial-complete/session-tutorial-complete-env/src/main/resources/database/

cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/account/AccountCreateController.java ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/account/AccountCreateForm.java ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/account/IllegalOperationException.java ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/account/IllegalOperationExceptionHandler.java ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/goods/GoodsController.java ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/goods/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/goods/GoodViewForm.java ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/goods/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/login/LoginController.java ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/login/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/validation/Confirm.java ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/validation/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/java/com/example/session/app/validation/ConfirmValidator.java ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/validation/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/webapp/resources/vendor/bootstrap-3.0.0/css/bootstrap.css ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/resources/vendor/bootstrap-3.0.0/css/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/account/createConfirm.jsp ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/account/createFinish.jsp ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/account/createForm.jsp ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/common/error/illegalOperationError.jsp ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/common/error/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/goods/showGoods.jsp ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/goods/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/goods/showGoodsDetail.jsp ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/goods/
cp -p ./session-tutorial-init/session-tutorial-init-web/src/main/webapp/WEB-INF/views/login/loginForm.jsp ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/login/

###################  tutorial flow  ###################

cp -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account/AccountUpdateController.java ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account
cp -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account/AccountUpdateForm.java ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account
cp -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/cart/CartController.java ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/cart
cp -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/cart/CartForm.java ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/cart
cp -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/config/EnableSynchronizeOnSessionPostProcessor.java ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/config
cp -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/goods/GoodAddForm.java ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/goods
cp -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/goods/GoodsSearchCriteria.java ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/goods
cp -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/order/OrderController.java ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/order
cp -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account/updateConfirm.jsp ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account
cp -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account/updateFinish.jsp ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account
cp -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account/updateForm1.jsp ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account
cp -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account/updateForm2.jsp ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account
cp -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/cart/viewCart.jsp ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/cart
cp -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/order/confirm.jsp ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/order
cp -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/order/finish.jsp ./target-project/session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/order

cp -p ./session-tutorial-complete/session-tutorial-complete-selenium/src/test/java/com/example/session/selenium/SessionTutorialTest.java ./target-project/session-tutorial-complete/session-tutorial-complete-selenium/src/test/java/com/example/session/selenium/session
cp -p ./common-test/resources/META-INF/spring/selenium.properties ./target-project/session-tutorial-complete/session-tutorial-complete-selenium/src/test/resources/META-INF/spring
cp -p ./common-test/resources/META-INF/spring/seleniumContext.xml ./target-project/session-tutorial-complete/session-tutorial-complete-selenium/src/test/resources/META-INF/spring
cp -p ./common-test/java/selenium/FirefoxDriverFactoryBean.java ./target-project/session-tutorial-complete/session-tutorial-complete-selenium/src/test/java/com/example/session/selenium
cp -p ./common-test/java/selenium/FunctionTestSupport.java ./target-project/session-tutorial-complete/session-tutorial-complete-selenium/src/test/java/com/example/session/selenium
cp -p ./common-test/java/selenium/PageSource.java ./target-project/session-tutorial-complete/session-tutorial-complete-selenium/src/test/java/com/example/session/selenium
cp -p ./common-test/java/selenium/ScreenCapture.java ./target-project/session-tutorial-complete/session-tutorial-complete-selenium/src/test/java/com/example/session/selenium
cp -p ./common-test/java/selenium/WebDriverCreator.java ./target-project/session-tutorial-complete/session-tutorial-complete-selenium/src/test/java/com/example/session/selenium
cp -p ./common-test/java/selenium/WebDriverInputFieldAccessor.java ./target-project/session-tutorial-complete/session-tutorial-complete-selenium/src/test/java/com/example/session/selenium
cp -p ./common-test/java/selenium/WebDriverOperations.java ./target-project/session-tutorial-complete/session-tutorial-complete-selenium/src/test/java/com/example/session/selenium

