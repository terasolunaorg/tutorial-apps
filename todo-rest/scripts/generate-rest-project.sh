#!/bin/bash

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version
#${VERSION}=tutorial pj version


# single without any DB configuration, mybatis single, mybatis multi, jpa single, jpa multi
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

# single without any DB configuration
mvn archetype:generate -B \
 -DarchetypeCatalog=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-web-blank-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-api \
 -Dversion=${VERSION}

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
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./target-project/todo-api-mybatis3/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./target-project/todo-api-jpa/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./target-project/todo-api/src/main/java/todo/app/todo

cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./target-project/todo-api-mybatis3/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./target-project/todo-api-jpa/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./target-project/todo-api/src/main/java/todo/app/todo

# model
# mybatis3
cp -p ./todo/src/main/java/mybatis3/todo/domain/model/Todo.java ./target-project/todo-api-mybatis3/src/main/java/todo/domain/model/
cp -p ./todo/src/main/java/mybatis3/todo/domain/model/Todo.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/model/
# jpa
cp -p ./todo/src/main/java/jpa/todo/domain/model/Todo.java ./target-project/todo-api-jpa/src/main/java/todo/domain/model/
cp -p ./todo/src/main/java/jpa/todo/domain/model/Todo.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/model/
# single without any DB configuration
cp -p ./todo/src/main/java/mybatis3/todo/domain/model/Todo.java ./target-project/todo-api/src/main/java/todo/domain/model/
# repository
# mybatis3
cp -p ./todo/src/main/java/mybatis3/todo/domain/repository/todo/TodoRepository.java ./target-project/todo-api-mybatis3/src/main/java/todo/domain/repository/todo/
cp -p ./todo/src/main/java/mybatis3/todo/domain/repository/todo/TodoRepository.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/repository/todo/
# jpa
cp -p ./todo/src/main/java/jpa/todo/domain/repository/todo/TodoRepository.java ./target-project/todo-api-jpa/src/main/java/todo/domain/repository/todo/
cp -p ./todo/src/main/java/jpa/todo/domain/repository/todo/TodoRepository.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/repository/todo/
# xml
cp -p ./todo/src/main/resources/todo/domain/repository/todo/TodoRepository.xml ./target-project/todo-api-mybatis3/src/main/resources/todo/domain/repository/todo/
cp -p ./todo/src/main/resources/todo/domain/repository/todo/TodoRepository.xml ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/resources/todo/domain/repository/todo/
# single without any DB configuration
cp -p ./todo/src/main/java/mybatis3/todo/domain/repository/todo/TodoRepository.java ./target-project/todo-api/src/main/java/todo/domain/repository/todo/
cp -p ./todo/src/main/java/todo/domain/repository/todo/TodoRepositoryImpl.java ./target-project/todo-api/src/main/java/todo/domain/repository/todo/

# webapp
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./target-project/todo-api-mybatis3/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./target-project/todo-api-jpa/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./target-project/todo-api/src/main/webapp/WEB-INF/views/todo/

# service for rest
cp -p ./todo-rest/src/main/java/todo/domain/service/todo/TodoService.java ./target-project/todo-api-mybatis3/src/main/java/todo/domain/service/todo/
cp -p ./todo-rest/src/main/java/todo/domain/service/todo/TodoService.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/service/todo/
cp -p ./todo-rest/src/main/java/todo/domain/service/todo/TodoService.java ./target-project/todo-api-jpa/src/main/java/todo/domain/service/todo/
cp -p ./todo-rest/src/main/java/todo/domain/service/todo/TodoService.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/service/todo/
cp -p ./todo-rest/src/main/java/todo/domain/service/todo/TodoService.java ./target-project/todo-api/src/main/java/todo/domain/service/todo/

cp -p ./todo-rest/src/main/java/mybatis3/todo/domain/service/todo/TodoServiceImpl.java ./target-project/todo-api-mybatis3/src/main/java/todo/domain/service/todo/
cp -p ./todo-rest/src/main/java/mybatis3/todo/domain/service/todo/TodoServiceImpl.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-domain/src/main/java/todo/domain/service/todo/
cp -p ./todo-rest/src/main/java/jpa/todo/domain/service/todo/TodoServiceImpl.java ./target-project/todo-api-jpa/src/main/java/todo/domain/service/todo/
cp -p ./todo-rest/src/main/java/jpa/todo/domain/service/todo/TodoServiceImpl.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-domain/src/main/java/todo/domain/service/todo/
cp -p ./todo-rest/src/main/java/mybatis3/todo/domain/service/todo/TodoServiceImpl.java ./target-project/todo-api/src/main/java/todo/domain/service/todo/


# rest sources
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoResource.java ./target-project/todo-api-mybatis3/src/main/java/todo/api/todo
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoResource.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/todo
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoResource.java ./target-project/todo-api-jpa/src/main/java/todo/api/todo
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoResource.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/todo
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoResource.java ./target-project/todo-api/src/main/java/todo/api/todo

cp -p ./todo-rest/src/main/java/todo/api/todo/TodoRestController.java ./target-project/todo-api-mybatis3/src/main/java/todo/api/todo
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoRestController.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/todo
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoRestController.java ./target-project/todo-api-jpa/src/main/java/todo/api/todo
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoRestController.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/todo
cp -p ./todo-rest/src/main/java/todo/api/todo/TodoRestController.java ./target-project/todo-api/src/main/java/todo/api/todo

cp -p ./todo-rest/src/main/java/todo/api/common/error/ApiError.java ./target-project/todo-api-mybatis3/src/main/java/todo/api/common/error
cp -p ./todo-rest/src/main/java/todo/api/common/error/ApiError.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/common/error
cp -p ./todo-rest/src/main/java/todo/api/common/error/ApiError.java ./target-project/todo-api-jpa/src/main/java/todo/api/common/error
cp -p ./todo-rest/src/main/java/todo/api/common/error/ApiError.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/common/error
cp -p ./todo-rest/src/main/java/todo/api/common/error/ApiError.java ./target-project/todo-api/src/main/java/todo/api/common/error

cp -p ./todo-rest/src/main/java/todo/api/common/error/RestGlobalExceptionHandler.java ./target-project/todo-api-mybatis3/src/main/java/todo/api/common/error
cp -p ./todo-rest/src/main/java/todo/api/common/error/RestGlobalExceptionHandler.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-web/src/main/java/todo/api/common/error
cp -p ./todo-rest/src/main/java/todo/api/common/error/RestGlobalExceptionHandler.java ./target-project/todo-api-jpa/src/main/java/todo/api/common/error
cp -p ./todo-rest/src/main/java/todo/api/common/error/RestGlobalExceptionHandler.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-web/src/main/java/todo/api/common/error
cp -p ./todo-rest/src/main/java/todo/api/common/error/RestGlobalExceptionHandler.java ./target-project/todo-api/src/main/java/todo/api/common/error
