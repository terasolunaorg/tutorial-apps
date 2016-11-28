#!/bin/bash

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version
#${VERSION}=tutorial pj version

# settings of maven archetype catalog
if test `echo ${ARCHETYPE_VERSION} | tail -c8 ` = "RELEASE"; then
 ARCHETYPE_CATALOG=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases
else
 ARCHETYPE_CATALOG=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-snapshots
fi

# single without any DB configuration, mybatis single, mybatis multi, jpa single, jpa multi
# create dir for complete map single
mkdir tmp
cd ./tmp
# single without any DB configuration
mvn archetype:generate -B \
 -DarchetypeCatalog=${ARCHETYPE_CATALOG} \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-web-blank-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo \
 -Dversion=${VERSION}
cd ..
# mybatis app single
mvn archetype:generate -B \
 -DarchetypeCatalog=${ARCHETYPE_CATALOG} \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-mybatis3 \
 -Dversion=${VERSION}

# mybatis app multi
mvn archetype:generate -B \
 -DarchetypeCatalog=${ARCHETYPE_CATALOG} \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-multi-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-mybatis3-multi \
 -Dversion=${VERSION}

# jpa app single
mvn archetype:generate -B \
 -DarchetypeCatalog=${ARCHETYPE_CATALOG} \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-web-blank-jpa-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-jpa \
 -Dversion=${VERSION}

# jpa multi single
mvn archetype:generate -B \
 -DarchetypeCatalog=${ARCHETYPE_CATALOG} \
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
mkdir -p ./tmp/todo/src/main/java/todo/app/todo


# repository
mkdir -p ./tmp/todo/src/main/java/todo/domain/repository/todo
mkdir -p ./todo-mybatis3/src/main/java/todo/domain/repository/todo
mkdir -p ./todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/java/todo/domain/repository/todo
mkdir -p ./todo-jpa/src/main/java/todo/domain/repository/todo
mkdir -p ./todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/repository/todo

# repository for xml
mkdir -p ./todo-mybatis3/src/main/resources/todo/domain/repository/todo
mkdir -p ./todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/resources/todo/domain/repository/todo

#service
mkdir -p ./tmp/todo/src/main/java/todo/domain/service/todo
mkdir -p ./todo-mybatis3/src/main/java/todo/domain/service/todo
mkdir -p ./todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/java/todo/domain/service/todo
mkdir -p ./todo-jpa/src/main/java/todo/domain/service/todo
mkdir -p ./todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/service/todo

# app
mkdir -p ./tmp/todo/src/main/webapp/WEB-INF/views/todo/
mkdir -p ./todo-mybatis3/src/main/webapp/WEB-INF/views/todo/
mkdir -p ./todo-mybatis3-multi/todo-mybatis3-multi-web/src/main/webapp/WEB-INF/views/todo/
mkdir -p ./todo-jpa/src/main/webapp/WEB-INF/views/todo/
mkdir -p ./todo-jpa-multi/todo-jpa-multi-web/src/main/webapp/WEB-INF/views/todo/

# create dir for complete app
mkdir target-project

#mv to target-project
mv ./tmp/todo ./target-project
mv ./todo-mybatis3 ./target-project
mv ./todo-mybatis3-multi ./target-project
mv ./todo-jpa ./target-project
mv ./todo-jpa-multi ./target-project

rm -r ./tmp

# cp common sources
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./target-project/todo/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./target-project/todo-mybatis3/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-web/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./target-project/todo-jpa/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoForm.java ./target-project/todo-jpa-multi/todo-jpa-multi-web/src/main/java/todo/app/todo

cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./target-project/todo/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./target-project/todo-mybatis3/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-web/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./target-project/todo-jpa/src/main/java/todo/app/todo
cp -p ./todo/src/main/java/todo/app/todo/TodoController.java ./target-project/todo-jpa-multi/todo-jpa-multi-web/src/main/java/todo/app/todo

# model
# single without any DB configuration
cp -p ./todo/src/main/java/mybatis3/todo/domain/model/Todo.java ./target-project/todo/src/main/java/todo/domain/model/
# mybatis3
cp -p ./todo/src/main/java/mybatis3/todo/domain/model/Todo.java ./target-project/todo-mybatis3/src/main/java/todo/domain/model/
cp -p ./todo/src/main/java/mybatis3/todo/domain/model/Todo.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/java/todo/domain/model/
# jpa
cp -p ./todo/src/main/java/jpa/todo/domain/model/Todo.java ./target-project/todo-jpa/src/main/java/todo/domain/model/
cp -p ./todo/src/main/java/jpa/todo/domain/model/Todo.java ./target-project/todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/model/

# service
# TodoService
cp -p ./todo/src/main/java/todo/domain/service/todo/TodoService.java ./target-project/todo/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/todo/domain/service/todo/TodoService.java ./target-project/todo-mybatis3/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/todo/domain/service/todo/TodoService.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/todo/domain/service/todo/TodoService.java ./target-project/todo-jpa/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/todo/domain/service/todo/TodoService.java ./target-project/todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/service/todo/
# TodoServiceImpl
cp -p ./todo/src/main/java/mybatis3/todo/domain/service/todo/TodoServiceImpl.java ./target-project/todo/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/mybatis3/todo/domain/service/todo/TodoServiceImpl.java ./target-project/todo-mybatis3/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/mybatis3/todo/domain/service/todo/TodoServiceImpl.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/jpa/todo/domain/service/todo/TodoServiceImpl.java ./target-project/todo-jpa/src/main/java/todo/domain/service/todo/
cp -p ./todo/src/main/java/jpa/todo/domain/service/todo/TodoServiceImpl.java ./target-project/todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/service/todo/

# repository
# single without any DB configuration
cp -p ./todo/src/main/java/mybatis3/todo/domain/repository/todo/TodoRepository.java ./target-project/todo/src/main/java/todo/domain/repository/todo/
cp -p ./todo/src/main/java/todo/domain/repository/todo/TodoRepositoryImpl.java ./target-project/todo/src/main/java/todo/domain/repository/todo/
# mybatis3
cp -p ./todo/src/main/java/mybatis3/todo/domain/repository/todo/TodoRepository.java ./target-project/todo-mybatis3/src/main/java/todo/domain/repository/todo/
cp -p ./todo/src/main/java/mybatis3/todo/domain/repository/todo/TodoRepository.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/java/todo/domain/repository/todo/
# jpa
cp -p ./todo/src/main/java/jpa/todo/domain/repository/todo/TodoRepository.java ./target-project/todo-jpa/src/main/java/todo/domain/repository/todo/
cp -p ./todo/src/main/java/jpa/todo/domain/repository/todo/TodoRepository.java ./target-project/todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/repository/todo/
# xml
cp -p ./todo/src/main/resources/todo/domain/repository/todo/TodoRepository.xml ./target-project/todo-mybatis3/src/main/resources/todo/domain/repository/todo/
cp -p ./todo/src/main/resources/todo/domain/repository/todo/TodoRepository.xml ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-domain/src/main/resources/todo/domain/repository/todo/

# webapp
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./target-project/todo/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./target-project/todo-mybatis3/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-web/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./target-project/todo-jpa/src/main/webapp/WEB-INF/views/todo/
cp -p ./todo/src/main/webapp/WEB-INF/views/todo/list.jsp ./target-project/todo-jpa-multi/todo-jpa-multi-web/src/main/webapp/WEB-INF/views/todo/
