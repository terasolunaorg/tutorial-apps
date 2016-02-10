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


# cp common sources
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./todo-api-mybatis3/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./todo-api-jpa/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/app/todo

cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./todo-api-mybatis3/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./todo-api-jpa/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/app/todo

# model
# mybatis3
cp -p ./todo/src/main/java/mybatis3/todo/domain/model/Todo.java ./todo-api-mybatis3/src/main/java/todo/domain/model/
cp -p ./todo/src/main/java/mybatis3/todo/domain/model/Todo.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/model/
# jpa
cp -p ./todo/src/main/java/jpa/todo/domain/model/Todo.java ./todo-api-jpa/src/main/java/todo/domain/model/
cp -p ./todo/src/main/java/jpa/todo/domain/model/Todo.java ./todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/model/

# service for rest
cp -p ./todo/src/main/java/api/todo/domain/service/todo/TodoService.java ./todo-api-mybatis3/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/api/todo/domain/service/todo/TodoService.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/api/todo/domain/service/todo/TodoService.java ./todo-api-jpa/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/api/todo/domain/service/todo/TodoService.java ./todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/service/todo/

cp -p ./todo/src/main/java/api/mybatis3/todo/domain/service/todo/TodoServiceImpl.java ./todo-api-mybatis3/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/api/mybatis3/todo/domain/service/todo/TodoServiceImpl.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/api/jpa/todo/domain/service/todo/TodoServiceImpl.java ./todo-api-jpa/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/api/jpa/todo/domain/service/todo/TodoServiceImpl.java ./todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/service/todo/


# repository
# mybatis3
cp -p ./todo/src/main/java/mybatis3/todo/domain/repository/todo/TodoRepository.java ./todo-api-mybatis3/src/main/java/todo/domain/repository/todo/
cp -p ./todo/src/main/java/mybatis3/todo/domain/repository/todo/TodoRepository.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/repository/todo/
# jpa
cp -p ./todo/src/main/java/jpa/todo/domain/repository/todo/TodoRepository.java ./todo-api-jpa/src/main/java/todo/domain/repository/todo/
cp -p ./todo/src/main/java/jpa/todo/domain/repository/todo/TodoRepository.java ./todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/repository/todo/
# xml
cp -p ./todo/src/main/resources/todo/domain/repository/todo/TodoRepository.xml ./todo-api-mybatis3/src/main/resources/todo/domain/repository/todo/
cp -p ./todo/src/main/resources/todo/domain/repository/todo/TodoRepository.xml ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/resources/todo/domain/repository/todo/

# webapp
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./todo-api-mybatis3/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./todo-api-jpa/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/webapp/WEB-INF/views/todo/


# rest sources
cp -p ./todo/src/main/java/api/todo/api/todo/TodoResource.java ./todo-api-mybatis3/src/main/java/todo/api/todo
cp -p ./todo/src/main/java/api/todo/api/todo/TodoResource.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/todo
cp -p ./todo/src/main/java/api/todo/api/todo/TodoResource.java ./todo-api-jpa/src/main/java/todo/api/todo
cp -p ./todo/src/main/java/api/todo/api/todo/TodoResource.java ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/todo

cp -p ./todo/src/main/java/api/todo/api/todo/TodoRestController.java ./todo-api-mybatis3/src/main/java/todo/api/todo
cp -p ./todo/src/main/java/api/todo/api/todo/TodoRestController.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/todo
cp -p ./todo/src/main/java/api/todo/api/todo/TodoRestController.java ./todo-api-jpa/src/main/java/todo/api/todo
cp -p ./todo/src/main/java/api/todo/api/todo/TodoRestController.java ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/todo

cp -p ./todo/src/main/java/api/todo/api/common/error/ApiError.java ./todo-api-mybatis3/src/main/java/todo/api/common/error
cp -p ./todo/src/main/java/api/todo/api/common/error/ApiError.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/common/error
cp -p ./todo/src/main/java/api/todo/api/common/error/ApiError.java ./todo-api-jpa/src/main/java/todo/api/common/error
cp -p ./todo/src/main/java/api/todo/api/common/error/ApiError.java ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/common/error

cp -p ./todo/src/main/java/api/todo/api/common/error/RestGlobalExceptionHandler.java ./todo-api-mybatis3/src/main/java/todo/api/common/error
cp -p ./todo/src/main/java/api/todo/api/common/error/RestGlobalExceptionHandler.java ./todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/common/error
cp -p ./todo/src/main/java/api/todo/api/common/error/RestGlobalExceptionHandler.java ./todo-api-jpa/src/main/java/todo/api/common/error
cp -p ./todo/src/main/java/api/todo/api/common/error/RestGlobalExceptionHandler.java ./todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/common/error
