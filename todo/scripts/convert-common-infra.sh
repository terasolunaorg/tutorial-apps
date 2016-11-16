#!/bin/bash

# settings target project
if test ${TARGET_TUTORIAL} = "todo"; then
target[0]=./target-project/todo-mybatis3
target[1]=./target-project/todo-mybatis3-multi
target[2]=./target-project/todo-jpa
target[3]=./target-project/todo-jpa-multi
fi

# -infra.properties
for i in "${target[*]}";do
find $i -type f -name '*infra.properties' | xargs sed -i 's/DB_CLOSE_DELAY=-1*/DB_CLOSE_DELAY=-1;INIT=create table if not exists todo(todo_id varchar(36) primary key, todo_title varchar(30), finished boolean, created_at timestamp)/g'
done