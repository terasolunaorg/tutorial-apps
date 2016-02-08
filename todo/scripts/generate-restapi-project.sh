#!/bin/bash

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version
#${VERSION}=tutorial pj version


# mybatis single, mybatis multi, jpa single, jpa multi
# mybatis app single
mvn archetype:generate -B \
 -DarchetypeCatalog=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-api-mybatis3 \
 -Dversion=${VERSION}

# mybatis app multi
mvn archetype:generate -B \
 -DarchetypeCatalog=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-multi-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-api-mybatis3-multi \
 -Dversion=${VERSION}

# jpa app single
mvn archetype:generate -B \
 -DarchetypeCatalog=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-web-blank-jpa-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-api-jpa \
 -Dversion=${VERSION}

# jpa multi single
mvn archetype:generate -B \
 -DarchetypeCatalog=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-multi-web-blank-jpa-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-api-jpa-multi \
 -Dversion=${VERSION}



# make app
mkdir -p ./todo-api-mybatis3/src/main/java/todo/app/todo
mkdir -p ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/app/todo
mkdir -p ./todo-api-jpa/src/main/java/todo/app/todo
mkdir -p ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/app/todo


# repository
mkdir -p ./todo-api-mybatis3/src/main/java/todo/domain/repository/todo
mkdir -p ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/repository/todo
mkdir -p ./todo-api-jpa/src/main/java/todo/domain/repository/todo
mkdir -p ./todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/repository/todo

# repository for xml
mkdir -p ./todo-api-mybatis3/src/main/resources/todo/domain/repository/todo
mkdir -p ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/resources/todo/domain/repository/todo

#service
mkdir -p ./todo-api-mybatis3/src/main/java/todo/domain/service/todo
mkdir -p ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/service/todo
mkdir -p ./todo-api-jpa/src/main/java/todo/domain/service/todo
mkdir -p ./todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/service/todo

# app
mkdir -p ./todo-api-mybatis3/src/main/webapp/WEB-INF/views/todo/
mkdir -p ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/webapp/WEB-INF/views/todo/
mkdir -p ./todo-api-jpa/src/main/webapp/WEB-INF/views/todo/
mkdir -p ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/webapp/WEB-INF/views/todo/

# reast
mkdir -p ./todo-api-mybatis3/src/main/java/todo/api/todo
mkdir -p ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/todo
mkdir -p ./todo-api-jpa/src/main/java/todo/api/todo
mkdir -p ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/todo

mkdir -p ./todo-api-mybatis3/src/main/java/todo/api/common/error
mkdir -p ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/common/error
mkdir -p ./todo-api-jpa/src/main/java/todo/api/common/error
mkdir -p ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/common/error


# cp sources
cp -p ./todo/java/TodoForm.java ./todo-api-mybatis3/src/main/java/todo/app/todo
cp -p ./todo/java/TodoForm.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/app/todo
cp -p ./todo/java/TodoForm.java ./todo-api-jpa/src/main/java/todo/app/todo
cp -p ./todo/java/TodoForm.java ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/app/todo

cp -p ./todo/java/TodoController.java ./todo-api-mybatis3/src/main/java/todo/app/todo
cp -p ./todo/java/TodoController.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/app/todo
cp -p ./todo/java/TodoController.java ./todo-api-jpa/src/main/java/todo/app/todo
cp -p ./todo/java/TodoController.java ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/app/todo

# model
cp -p ./todo/java/TodoForMybatis.java ./todo-api-mybatis3/src/main/java/todo/domain/model/Todo.java
cp -p ./todo/java/TodoForMybatis.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/model/Todo.java
cp -p ./todo/java/TodoForJpa.java ./todo-api-jpa/src/main/java/todo/domain/model/Todo.java
cp -p ./todo/java/TodoForJpa.java ./todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/model/Todo.java

# service for rest
cp -p ./todo/java/TodoServiceForRest.java ./todo-api-mybatis3/src/main/java/todo/domain/service/todo/TodoService.java
cp -p ./todo/java/TodoServiceForRest.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/service/todo/TodoService.java
cp -p ./todo/java/TodoServiceForRest.java ./todo-api-jpa/src/main/java/todo/domain/service/todo/TodoService.java
cp -p ./todo/java/TodoServiceForRest.java ./todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/service/todo/TodoService.java

cp -p ./todo/java/TodoServiceImplForRestMybatis.java ./todo-api-mybatis3/src/main/java/todo/domain/service/todo/TodoServiceImpl.java
cp -p ./todo/java/TodoServiceImplForRestMybatis.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/service/todo/TodoServiceImpl.java
cp -p ./todo/java/TodoServiceImplForRestJpa.java ./todo-api-jpa/src/main/java/todo/domain/service/todo/TodoServiceImpl.java
cp -p ./todo/java/TodoServiceImplForRestJpa.java ./todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/service/todo/TodoServiceImpl.java


# repository
# java
cp -p ./todo/java/TodoRepositoryForMybatis.java ./todo-api-mybatis3/src/main/java/todo/domain/repository/todo/TodoRepository.java
cp -p ./todo/java/TodoRepositoryForMybatis.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/repository/todo/TodoRepository.java
cp -p ./todo/java/TodoRepositoryForJpa.java ./todo-api-jpa/src/main/java/todo/domain/repository/todo/TodoRepository.java
cp -p ./todo/java/TodoRepositoryForJpa.java ./todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/repository/todo/TodoRepository.java
# xml
cp -p ./todo/xml/TodoRepository.xml ./todo-api-mybatis3/src/main/resources/todo/domain/repository/todo/
cp -p ./todo/xml/TodoRepository.xml ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/resources/todo/domain/repository/todo/

# webapp
cp -p ./todo/jsp/list.jsp ./todo-api-mybatis3/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/jsp/list.jsp ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/jsp/list.jsp ./todo-api-jpa/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/jsp/list.jsp ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/webapp/WEB-INF/views/todo/

# rest sources
cp -p ./todo/java/TodoResource.java ./todo-api-mybatis3/src/main/java/todo/api/todo
cp -p ./todo/java/TodoResource.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/todo
cp -p ./todo/java/TodoResource.java ./todo-api-jpa/src/main/java/todo/api/todo
cp -p ./todo/java/TodoResource.java ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/todo

cp -p ./todo/java/TodoRestController.java ./todo-api-mybatis3/src/main/java/todo/api/todo
cp -p ./todo/java/TodoRestController.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/todo
cp -p ./todo/java/TodoRestController.java ./todo-api-jpa/src/main/java/todo/api/todo
cp -p ./todo/java/TodoRestController.java ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/todo

cp -p ./todo/java/ApiError.java ./todo-api-mybatis3/src/main/java/todo/api/common/error
cp -p ./todo/java/ApiError.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/common/error
cp -p ./todo/java/ApiError.java ./todo-api-jpa/src/main/java/todo/api/common/error
cp -p ./todo/java/ApiError.java ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/common/error

cp -p ./todo/java/RestGlobalExceptionHandler.java ./todo-api-mybatis3/src/main/java/todo/api/common/error
cp -p ./todo/java/RestGlobalExceptionHandler.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/common/error
cp -p ./todo/java/RestGlobalExceptionHandler.java ./todo-api-jpa/src/main/java/todo/api/common/error
cp -p ./todo/java/RestGlobalExceptionHandler.java ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/common/error
