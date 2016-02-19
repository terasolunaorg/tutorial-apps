#!/bin/bash

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version
#${VERSION}=tutorial pj version

# mybatis app multi
mvn archetype:generate -B \
 -DarchetypeCatalog=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-multi-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=com.example.session \
 -DartifactId=session-tutorial-complete \
 -Dversion=${VERSION}

###################  init  ###################

# make app
mkdir -p ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/repository/account
mkdir -p ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/repository/goods
mkdir -p ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/repository/order
mkdir -p ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/account
mkdir -p ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/goods
mkdir -p ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/order
mkdir -p ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/userdetails
mkdir -p ./session-tutorial-complete/session-tutorial-complete-domain/src/main/resources/com/example/session/domain/repository/account
mkdir -p ./session-tutorial-complete/session-tutorial-complete-domain/src/main/resources/com/example/session/domain/repository/goods
mkdir -p ./session-tutorial-complete/session-tutorial-complete-domain/src/main/resources/com/example/session/domain/repository/order

mkdir -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account
mkdir -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/goods
mkdir -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/login
mkdir -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/validation
mkdir -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/resources/vendor/bootstrap-3.0.0/css
mkdir -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account
mkdir -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/goods
mkdir -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/login

###################  tutorial flow  ###################

mkdir -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account
mkdir -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/cart
mkdir -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/config
mkdir -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/order
mkdir -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/goods
mkdir -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account
mkdir -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/cart
mkdir -p ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/order


###################  init  ###################

# cp security sources
cp -p ./session-init/src/main/java/com/example/session/domain/model/Account.java ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-init/src/main/java/com/example/session/domain/model/Cart.java ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-init/src/main/java/com/example/session/domain/model/CartItem.java ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-init/src/main/java/com/example/session/domain/model/Goods.java ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-init/src/main/java/com/example/session/domain/model/Order.java ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-init/src/main/java/com/example/session/domain/model/OrderLine.java ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/model/
cp -p ./session-init/src/main/java/com/example/session/domain/repository/account/AccountRepository.java ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/repository/account/
cp -p ./session-init/src/main/java/com/example/session/domain/repository/goods/GoodsRepository.java ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/repository/goods/
cp -p ./session-init/src/main/java/com/example/session/domain/repository/order/OrderRepository.java ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/repository/order/
cp -p ./session-init/src/main/java/com/example/session/domain/service/account/AccountService.java ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/account/
cp -p ./session-init/src/main/java/com/example/session/domain/service/goods/GoodsService.java ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/goods/
cp -p ./session-init/src/main/java/com/example/session/domain/service/order/EmptyCartOrderException.java ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/order/
cp -p ./session-init/src/main/java/com/example/session/domain/service/order/InvalidCartOrderException.java ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/order/
cp -p ./session-init/src/main/java/com/example/session/domain/service/order/OrderService.java ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/order/
cp -p ./session-init/src/main/java/com/example/session/domain/service/userdetails/AccountDetails.java ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/userdetails/
cp -p ./session-init/src/main/java/com/example/session/domain/service/userdetails/AccountDetailsService.java ./session-tutorial-complete/session-tutorial-complete-domain/src/main/java/com/example/session/domain/service/userdetails/
cp -p ./session-init/src/main/resources/com/example/session/domain/repository/account/AccountRepository.xml ./session-tutorial-complete/session-tutorial-complete-domain/src/main/resources/com/example/session/domain/repository/account/
cp -p ./session-init/src/main/resources/com/example/session/domain/repository/goods/GoodsRepository.xml ./session-tutorial-complete/session-tutorial-complete-domain/src/main/resources/com/example/session/domain/repository/goods/
cp -p ./session-init/src/main/resources/com/example/session/domain/repository/order/OrderRepository.xml ./session-tutorial-complete/session-tutorial-complete-domain/src/main/resources/com/example/session/domain/repository/order/
cp -p ./session-init/src/main/resources/META-INF/dozer/order-mapping.xml ./session-tutorial-complete/session-tutorial-complete-domain/src/main/resources/META-INF/dozer/

cp -p ./session-init/src/main/resources/database/H2-dataload.sql ./session-tutorial-complete/session-tutorial-complete-env/src/main/resources/database/
cp -p ./session-init/src/main/resources/database/H2-schema.sql ./session-tutorial-complete/session-tutorial-complete-env/src/main/resources/database/

cp -p ./session-init/src/main/java/com/example/session/app/account/AccountCreateController.java ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account/
cp -p ./session-init/src/main/java/com/example/session/app/account/AccountCreateForm.java ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account/
cp -p ./session-init/src/main/java/com/example/session/app/account/IllegalOperationException.java ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account/
cp -p ./session-init/src/main/java/com/example/session/app/account/IllegalOperationExceptionHandler.java ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account/
cp -p ./session-init/src/main/java/com/example/session/app/goods/GoodsController.java ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/goods/
cp -p ./session-init/src/main/java/com/example/session/app/goods/GoodViewForm.java ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/goods/
cp -p ./session-init/src/main/java/com/example/session/app/login/LoginController.java ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/login/
cp -p ./session-init/src/main/java/com/example/session/app/validation/Confirm.java ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/validation/
cp -p ./session-init/src/main/java/com/example/session/app/validation/ConfirmValidator.java ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/validation/
cp -p ./session-init/src/main/webapp/resources/vendor/bootstrap-3.0.0/css/bootstrap.css ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/resources/vendor/bootstrap-3.0.0/css/
cp -p ./session-init/src/main/webapp/WEB-INF/views/account/createConfirm.jsp ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account/
cp -p ./session-init/src/main/webapp/WEB-INF/views/account/createFinish.jsp ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account/
cp -p ./session-init/src/main/webapp/WEB-INF/views/account/createForm.jsp ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account/
cp -p ./session-init/src/main/webapp/WEB-INF/views/common/error/illegalOperationError.jsp ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/common/error/
cp -p ./session-init/src/main/webapp/WEB-INF/views/goods/showGoods.jsp ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/goods/
cp -p ./session-init/src/main/webapp/WEB-INF/views/goods/showGoodsDetail.jsp ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/goods/
cp -p ./session-init/src/main/webapp/WEB-INF/views/login/loginForm.jsp ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/login/

###################  tutorial flow  ###################

cp -p ./session-complete/src/main/java/com/example/session/app/account/AccountUpdateController.java ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account
cp -p ./session-complete/src/main/java/com/example/session/app/account/AccountUpdateForm.java ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/account
cp -p ./session-complete/src/main/java/com/example/session/app/cart/CartController.java ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/cart
cp -p ./session-complete/src/main/java/com/example/session/app/cart/CartForm.java ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/cart
cp -p ./session-complete/src/main/java/com/example/session/app/config/EnableSynchronizeOnSessionPostProcessor.java ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/config
cp -p ./session-complete/src/main/java/com/example/session/app/goods/GoodAddForm.java ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/goods
cp -p ./session-complete/src/main/java/com/example/session/app/goods/GoodsSearchCriteria.java ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/goods
cp -p ./session-complete/src/main/java/com/example/session/app/order/OrderController.java ./session-tutorial-complete/session-tutorial-complete-web/src/main/java/com/example/session/app/order
cp -p ./session-complete/src/main/webapp/WEB-INF/views/account/updateConfirm.jsp ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account
cp -p ./session-complete/src/main/webapp/WEB-INF/views/account/updateFinish.jsp ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account
cp -p ./session-complete/src/main/webapp/WEB-INF/views/account/updateForm1.jsp ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account
cp -p ./session-complete/src/main/webapp/WEB-INF/views/account/updateForm2.jsp ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/account
cp -p ./session-complete/src/main/webapp/WEB-INF/views/cart/viewCart.jsp ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/cart
cp -p ./session-complete/src/main/webapp/WEB-INF/views/order/confirm.jsp ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/order
cp -p ./session-complete/src/main/webapp/WEB-INF/views/order/finish.jsp ./session-tutorial-complete/session-tutorial-complete-web/src/main/webapp/WEB-INF/views/order

cp -p ./session-complete/src/test/java/com/example/session/FunctionTestSupport.java ./session-tutorial-complete/session-tutorial-complete-selenium/src/test/java/com/example/session/
cp -p ./session-complete/src/test/java/com/example/session/selenium/SessionTutorialTest.java ./session-tutorial-complete/session-tutorial-complete-selenium/src/test/java/com/example/session/selenium/
