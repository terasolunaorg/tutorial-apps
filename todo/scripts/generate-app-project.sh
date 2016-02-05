#!/bin/bash

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version
#${VERSION}=tutorial pj version


# mybatis single, mybatis multi, jpa single, jpa multiの型を作成
# mybatis app single
mvn archetype:generate -B \
 -DarchetypeCatalog=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-mybatis3 \
 -Dversion=${VERSION}

# mybatis app multi
mvn archetype:generate -B \
 -DarchetypeCatalog=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-multi-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-mybatis3-multi \
 -Dversion=${VERSION}

# jpa app single
mvn archetype:generate -B \
 -DarchetypeCatalog=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-web-blank-jpa-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-jpa \
 -Dversion=${VERSION}

# jpa multi single
mvn archetype:generate -B \
 -DarchetypeCatalog=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-multi-web-blank-jpa-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-jpa-multi \
 -Dversion=${VERSION}


# チュートリアルで作成するディレクトリを事前にmake app
mkdir -p ./todo-mybatis3/src/main/java/todo/app/todo
mkdir -p ./todo-mybatis3-multi/todo-mybatis3-multi-web/src/main/java/todo/app/todo
mkdir -p ./todo-jpa/src/main/java/todo/app/todo
mkdir -p ./todo-jpa-multi/todo-jpa-multi-web/src/main/java/todo/app/todo


# repository
mkdir -p ./todo-mybatis3/src/main/java/todo/domain/repository/todo
mkdir -p ./todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/java/todo/domain/repository/todo
mkdir -p ./todo-jpa/src/main/java/todo/domain/repository/todo
mkdir -p ./todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/repository/todo

# repository for xml
mkdir -p ./todo-mybatis3/src/main/resources/todo/domain/repository/todo
mkdir -p ./todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/resources/todo/domain/repository/todo


#service
mkdir -p ./todo-mybatis3/src/main/java/todo/domain/service/todo
mkdir -p ./todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/java/todo/domain/service/todo
mkdir -p ./todo-jpa/src/main/java/todo/domain/service/todo
mkdir -p ./todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/service/todo

# app
mkdir -p ./todo-mybatis3/src/main/webapp/WEB-INF/views/todo/
mkdir -p ./todo-mybatis3-multi/todo-mybatis3-multi-web/src/main/webapp/WEB-INF/views/todo/
mkdir -p ./todo-jpa/src/main/webapp/WEB-INF/views/todo/
mkdir -p ./todo-jpa-multi/todo-jpa-multi-web/src/main/webapp/WEB-INF/views/todo/



# Gitで落としてきた各種完成版ソースをコピー
cp -p ./todo/java/TodoForm.java ./todo-mybatis3/src/main/java/todo/app/todo
cp -p ./todo/java/TodoForm.java ./todo-mybatis3-multi/todo-mybatis3-multi-web/src/main/java/todo/app/todo
cp -p ./todo/java/TodoForm.java ./todo-jpa/src/main/java/todo/app/todo
cp -p ./todo/java/TodoForm.java ./todo-jpa-multi/todo-jpa-multi-web/src/main/java/todo/app/todo

cp -p ./todo/java/TodoController.java ./todo-mybatis3/src/main/java/todo/app/todo
cp -p ./todo/java/TodoController.java ./todo-mybatis3-multi/todo-mybatis3-multi-web/src/main/java/todo/app/todo
cp -p ./todo/java/TodoController.java ./todo-jpa/src/main/java/todo/app/todo
cp -p ./todo/java/TodoController.java ./todo-jpa-multi/todo-jpa-multi-web/src/main/java/todo/app/todo


# copy domain
# model
cp -p ./todo/java/TodoForMybatis.java ./todo-mybatis3/src/main/java/todo/domain/model/Todo.java
cp -p ./todo/java/TodoForMybatis.java ./todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/java/todo/domain/model/Todo.java
cp -p ./todo/java/TodoForJpa.java ./todo-jpa/src/main/java/todo/domain/model/Todo.java
cp -p ./todo/java/TodoForJpa.java ./todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/model/Todo.java

# service 
cp -p ./todo/java/TodoService.java ./todo-mybatis3/src/main/java/todo/domain/service/todo/
cp -p ./todo/java/TodoService.java ./todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/java/todo/domain/service/todo/
cp -p ./todo/java/TodoService.java ./todo-jpa/src/main/java/todo/domain/service/todo/
cp -p ./todo/java/TodoService.java ./todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/service/todo/

cp -p ./todo/java/TodoServiceImplForMybatis.java ./todo-mybatis3/src/main/java/todo/domain/service/todo/TodoServiceImpl.java
cp -p ./todo/java/TodoServiceImplForMybatis.java ./todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/java/todo/domain/service/todo/TodoServiceImpl.java
cp -p ./todo/java/TodoServiceImplForJpa.java ./todo-jpa/src/main/java/todo/domain/service/todo/TodoServiceImpl.java
cp -p ./todo/java/TodoServiceImplForJpa.java ./todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/service/todo/TodoServiceImpl.java


# repository
# java
cp -p ./todo/java/TodoRepositoryForMybatis.java ./todo-mybatis3/src/main/java/todo/domain/repository/todo/TodoRepository.java
cp -p ./todo/java/TodoRepositoryForMybatis.java ./todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/java/todo/domain/repository/todo/TodoRepository.java
cp -p ./todo/java/TodoRepositoryForJpa.java ./todo-jpa/src/main/java/todo/domain/repository/todo/TodoRepository.java
cp -p ./todo/java/TodoRepositoryForJpa.java ./todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/repository/todo/TodoRepository.java
# xml
cp -p ./todo/xml/TodoRepository.xml ./todo-mybatis3/src/main/resources/todo/domain/repository/todo/
cp -p ./todo/xml/TodoRepository.xml ./todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/resources/todo/domain/repository/todo/

# webapp
cp -p ./todo/jsp/list.jsp ./todo-mybatis3/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/jsp/list.jsp ./todo-mybatis3-multi/todo-mybatis3-multi-web/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/jsp/list.jsp ./todo-jpa/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/jsp/list.jsp ./todo-jpa-multi/todo-jpa-multi-web/src/main/webapp/WEB-INF/views/todo/

