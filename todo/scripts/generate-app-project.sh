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


# make app
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

# cp common sources
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./todo-mybatis3/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./todo-mybatis3-multi/todo-mybatis3-multi-web/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./todo-jpa/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./todo-jpa-multi/todo-jpa-multi-web/src/main/java/todo/app/todo

cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./todo-mybatis3/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./todo-mybatis3-multi/todo-mybatis3-multi-web/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./todo-jpa/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./todo-jpa-multi/todo-jpa-multi-web/src/main/java/todo/app/todo

# model
# mybatis3
cp -p ./todo/src/main/java/mybatis3/todo/domain/model/Todo.java ./todo-mybatis3/src/main/java/todo/domain/model/
cp -p ./todo/src/main/java/mybatis3/todo/domain/model/Todo.java ./todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/java/todo/domain/model/
# jpa
cp -p ./todo/src/main/java/jpa/todo/domain/model/Todo.java ./todo-jpa/src/main/java/todo/domain/model/
cp -p ./todo/src/main/java/jpa/todo/domain/model/Todo.java ./todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/model/

# service 
# mybatis3
cp -p ./todo/src/main/java/todo/domain/service/todo/TodoService.java ./todo-mybatis3/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/todo/domain/service/todo/TodoService.java ./todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/todo/domain/service/todo/TodoService.java ./todo-jpa/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/todo/domain/service/todo/TodoService.java ./todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/service/todo/
# jpa
cp -p ./todo/src/main/java/mybatis3/todo/domain/service/todo/TodoServiceImpl.java ./todo-mybatis3/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/mybatis3/todo/domain/service/todo/TodoServiceImpl.java ./todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/jpa/todo/domain/service/todo/TodoServiceImpl.java ./todo-jpa/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/jpa/todo/domain/service/todo/TodoServiceImpl.java ./todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/service/todo/

# repository
# mybatis3
cp -p ./todo/src/main/java/mybatis3/todo/domain/repository/todo/TodoRepository.java ./todo-mybatis3/src/main/java/todo/domain/repository/todo/
cp -p ./todo/src/main/java/mybatis3/todo/domain/repository/todo/TodoRepository.java ./todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/java/todo/domain/repository/todo/
# jpa
cp -p ./todo/src/main/java/jpa/todo/domain/repository/todo/TodoRepository.java ./todo-jpa/src/main/java/todo/domain/repository/todo/
cp -p ./todo/src/main/java/jpa/todo/domain/repository/todo/TodoRepository.java ./todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/repository/todo/
# xml
cp -p ./todo/src/main/resources/todo/domain/repository/todo/TodoRepository.xml ./todo-mybatis3/src/main/resources/todo/domain/repository/todo/
cp -p ./todo/src/main/resources/todo/domain/repository/todo/TodoRepository.xml ./todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/resources/todo/domain/repository/todo/

# webapp
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./todo-mybatis3/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./todo-mybatis3-multi/todo-mybatis3-multi-web/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./todo-jpa/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./todo-jpa-multi/todo-jpa-multi-web/src/main/webapp/WEB-INF/views/todo/

