#!/bin/bash

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version
#${VERSION}=tutorial pj version

# generate rest apps
case "${ARCHETYPE_VERSION:0:5}" in
  5.2* | 5.1* | 5.0* )
    bash ./todo-rest/scripts/generate-project-from-nexus.sh;;
  * )
    if test `echo ${ARCHETYPE_VERSION} | tail -c8 ` = "RELEASE"; then
      bash ./todo-rest/scripts/generate-project-from-maven-central.sh
    else
      bash ./todo-rest/scripts/generate-project-from-nexus.sh
    fi;;
esac

# make app
mkdir -p ./todo-api-mybatis3/src/main/java/todo/app/todo
mkdir -p ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/app/todo
mkdir -p ./todo-api-jpa/src/main/java/todo/app/todo
mkdir -p ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/app/todo
mkdir -p ./todo-api/src/main/java/todo/app/todo

# repository
mkdir -p ./todo-api-mybatis3/src/main/java/todo/domain/repository/todo
mkdir -p ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/repository/todo
mkdir -p ./todo-api-jpa/src/main/java/todo/domain/repository/todo
mkdir -p ./todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/repository/todo
mkdir -p ./todo-api/src/main/java/todo/domain/repository/todo

# repository for xml
mkdir -p ./todo-api-mybatis3/src/main/resources/todo/domain/repository/todo
mkdir -p ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/resources/todo/domain/repository/todo

#service
mkdir -p ./todo-api-mybatis3/src/main/java/todo/domain/service/todo
mkdir -p ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/service/todo
mkdir -p ./todo-api-jpa/src/main/java/todo/domain/service/todo
mkdir -p ./todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/service/todo
mkdir -p ./todo-api/src/main/java/todo/domain/service/todo

# webapp
mkdir -p ./todo-api-mybatis3/src/main/webapp/WEB-INF/views/todo/
mkdir -p ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/webapp/WEB-INF/views/todo/
mkdir -p ./todo-api-jpa/src/main/webapp/WEB-INF/views/todo/
mkdir -p ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/webapp/WEB-INF/views/todo/
mkdir -p ./todo-api/src/main/webapp/WEB-INF/views/todo/

# rest
mkdir -p ./todo-api-mybatis3/src/main/java/todo/api/todo
mkdir -p ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/todo
mkdir -p ./todo-api-jpa/src/main/java/todo/api/todo
mkdir -p ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/todo
mkdir -p ./todo-api/src/main/java/todo/api/todo

mkdir -p ./todo-api-mybatis3/src/main/java/todo/api/common/error
mkdir -p ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/common/error
mkdir -p ./todo-api-jpa/src/main/java/todo/api/common/error
mkdir -p ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/common/error
mkdir -p ./todo-api/src/main/java/todo/api/common/error

# create dir for complete app
mkdir target-project

# mv to target-project
mv ./todo-api-jpa ./target-project
mv ./todo-api-mybatis3 ./target-project
mv ./todo-api-jpa-multi ./target-project
mv ./todo-api-mybatis3-multi ./target-project
mv ./todo-api ./target-project

# cp common sources
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./target-project/todo-api/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./target-project/todo-api-mybatis3/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./target-project/todo-api-jpa/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/app/todo

cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./target-project/todo-api/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./target-project/todo-api-mybatis3/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./target-project/todo-api-jpa/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/app/todo

# model
# single without any DB configuration
cp -p ./todo/src/main/java/mybatis3/todo/domain/model/Todo.java ./target-project/todo-api/src/main/java/todo/domain/model/
# mybatis3
cp -p ./todo/src/main/java/mybatis3/todo/domain/model/Todo.java ./target-project/todo-api-mybatis3/src/main/java/todo/domain/model/
cp -p ./todo/src/main/java/mybatis3/todo/domain/model/Todo.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/model/
# jpa
case "${ARCHETYPE_VERSION:0:5}" in
  5.2* | 5.1* | 5.0* )
    cp -p ./todo/scripts/old-resources/5.2.x/src/main/java/jpa/todo/domain/model/Todo.java ./target-project/todo-api-jpa/src/main/java/todo/domain/model/
    cp -p ./todo/scripts/old-resources/5.2.x/src/main/java/jpa/todo/domain/model/Todo.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/model/ ;;
  * )
    cp -p ./todo/src/main/java/jpa/todo/domain/model/Todo.java ./target-project/todo-api-jpa/src/main/java/todo/domain/model/
    cp -p ./todo/src/main/java/jpa/todo/domain/model/Todo.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/model/ ;;
esac
# repository
# single without any DB configuration
cp -p ./todo/src/main/java/mybatis3/todo/domain/repository/todo/TodoRepository.java ./target-project/todo-api/src/main/java/todo/domain/repository/todo/
cp -p ./todo/src/main/java/todo/domain/repository/todo/TodoRepositoryImpl.java ./target-project/todo-api/src/main/java/todo/domain/repository/todo/
# mybatis3
cp -p ./todo/src/main/java/mybatis3/todo/domain/repository/todo/TodoRepository.java ./target-project/todo-api-mybatis3/src/main/java/todo/domain/repository/todo/
cp -p ./todo/src/main/java/mybatis3/todo/domain/repository/todo/TodoRepository.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/repository/todo/
# jpa
cp -p ./todo/src/main/java/jpa/todo/domain/repository/todo/TodoRepository.java ./target-project/todo-api-jpa/src/main/java/todo/domain/repository/todo/
cp -p ./todo/src/main/java/jpa/todo/domain/repository/todo/TodoRepository.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/repository/todo/
# xml
cp -p ./todo/src/main/resources/todo/domain/repository/todo/TodoRepository.xml ./target-project/todo-api-mybatis3/src/main/resources/todo/domain/repository/todo/
cp -p ./todo/src/main/resources/todo/domain/repository/todo/TodoRepository.xml ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/resources/todo/domain/repository/todo/

# webapp
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./target-project/todo-api/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./target-project/todo-api-mybatis3/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./target-project/todo-api-jpa/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/webapp/WEB-INF/views/todo/

# service for rest
cp -p ./todo-rest/src/main/java/todo/domain/service/todo/TodoService.java ./target-project/todo-api/src/main/java/todo/domain/service/todo/
cp -p ./todo-rest/src/main/java/todo/domain/service/todo/TodoService.java ./target-project/todo-api-mybatis3/src/main/java/todo/domain/service/todo/
cp -p ./todo-rest/src/main/java/todo/domain/service/todo/TodoService.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/service/todo/
cp -p ./todo-rest/src/main/java/todo/domain/service/todo/TodoService.java ./target-project/todo-api-jpa/src/main/java/todo/domain/service/todo/
cp -p ./todo-rest/src/main/java/todo/domain/service/todo/TodoService.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/service/todo/

cp -p ./todo-rest/src/main/java/mybatis3/todo/domain/service/todo/TodoServiceImpl.java ./target-project/todo-api/src/main/java/todo/domain/service/todo/
cp -p ./todo-rest/src/main/java/mybatis3/todo/domain/service/todo/TodoServiceImpl.java ./target-project/todo-api-mybatis3/src/main/java/todo/domain/service/todo/
cp -p ./todo-rest/src/main/java/mybatis3/todo/domain/service/todo/TodoServiceImpl.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/service/todo/
cp -p ./todo-rest/src/main/java/jpa/todo/domain/service/todo/TodoServiceImpl.java ./target-project/todo-api-jpa/src/main/java/todo/domain/service/todo/
cp -p ./todo-rest/src/main/java/jpa/todo/domain/service/todo/TodoServiceImpl.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/service/todo/


# rest sources
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoResource.java ./target-project/todo-api/src/main/java/todo/api/todo
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoResource.java ./target-project/todo-api-mybatis3/src/main/java/todo/api/todo
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoResource.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/todo
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoResource.java ./target-project/todo-api-jpa/src/main/java/todo/api/todo
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoResource.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/todo

cp -p ./todo-rest/src/main/java/todo/api/todo/TodoRestController.java ./target-project/todo-api/src/main/java/todo/api/todo
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoRestController.java ./target-project/todo-api-mybatis3/src/main/java/todo/api/todo
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoRestController.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/todo
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoRestController.java ./target-project/todo-api-jpa/src/main/java/todo/api/todo
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoRestController.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/todo

cp -p ./todo-rest/src/main/java/todo/api/common/error/ApiError.java ./target-project/todo-api/src/main/java/todo/api/common/error
cp -p ./todo-rest/src/main/java/todo/api/common/error/ApiError.java ./target-project/todo-api-mybatis3/src/main/java/todo/api/common/error
cp -p ./todo-rest/src/main/java/todo/api/common/error/ApiError.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/common/error
cp -p ./todo-rest/src/main/java/todo/api/common/error/ApiError.java ./target-project/todo-api-jpa/src/main/java/todo/api/common/error
cp -p ./todo-rest/src/main/java/todo/api/common/error/ApiError.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/common/error

cp -p ./todo-rest/src/main/java/todo/api/common/error/RestGlobalExceptionHandler.java ./target-project/todo-api/src/main/java/todo/api/common/error
cp -p ./todo-rest/src/main/java/todo/api/common/error/RestGlobalExceptionHandler.java ./target-project/todo-api-mybatis3/src/main/java/todo/api/common/error
cp -p ./todo-rest/src/main/java/todo/api/common/error/RestGlobalExceptionHandler.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/common/error
cp -p ./todo-rest/src/main/java/todo/api/common/error/RestGlobalExceptionHandler.java ./target-project/todo-api-jpa/src/main/java/todo/api/common/error
cp -p ./todo-rest/src/main/java/todo/api/common/error/RestGlobalExceptionHandler.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/common/error

