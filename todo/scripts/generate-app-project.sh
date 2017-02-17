#!/bin/bash

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version
#${VERSION}=tutorial pj version

# generate todo tutorial apps
case "${ARCHETYPE_VERSION:0:5}" in
  5.2* | 5.1* | 5.0* )
    bash ./todo/scripts/generate-project-from-nexus.sh;;
  * )
    if test `echo ${ARCHETYPE_VERSION} | tail -c8 ` = "RELEASE"; then
      bash ./todo/scripts/generate-project-from-maven-central.sh
    else
      bash ./todo/scripts/generate-project-from-nexus.sh
    fi;;
esac

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
case "${ARCHETYPE_VERSION:0:5}" in
  5.2* | 5.1* | 5.0* )
    cp -p ./todo/scripts/old-resources/5.2.x/src/main/java/jpa/todo/domain/model/Todo.java ./target-project/todo-jpa/src/main/java/todo/domain/model/
    cp -p ./todo/scripts/old-resources/5.2.x/src/main/java/jpa/todo/domain/model/Todo.java ./target-project/todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/model/;;
  * )
    cp -p ./todo/src/main/java/jpa/todo/domain/model/Todo.java ./target-project/todo-jpa/src/main/java/todo/domain/model/
    cp -p ./todo/src/main/java/jpa/todo/domain/model/Todo.java ./target-project/todo-jpa-multi/todo-jpa-multi-domain/src/main/java/todo/domain/model/;;
esac

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
