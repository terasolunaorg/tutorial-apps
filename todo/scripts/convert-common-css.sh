#!/bin/bash

# settings target project
if test ${TARGET_TUTORIAL} = "todo"; then
target[0]=./target-project/todo
target[1]=./target-project/todo-mybatis3
target[2]=./target-project/todo-mybatis3-multi
target[3]=./target-project/todo-jpa
target[4]=./target-project/todo-jpa-multi
fi
if test ${TARGET_TUTORIAL} = "todo-rest"; then
target[0]=./target-project/todo-api
target[1]=./target-project/todo-api-mybatis3
target[2]=./target-project/todo-api-mybatis3-multi
target[3]=./target-project/todo-api-jpa
target[4]=./target-project/todo-api-jpa-multi
fi
if test ${TARGET_TUTORIAL} = "first-spring-security"; then
target[0]=./target-project/first-spring-security-mybatis3
target[1]=./target-project/first-spring-security-mybatis3-multi
fi

# styles.css
for i in ` find ${target[*]} -type f -name 'styles.css' `; do echo -e ' 
.strike {
    text-decoration: line-through;
}

.alert {
    border: 1px solid;
    margin-bottom: 5px;
}

.alert-error {
    background-color: #c60f13;
    border-color: #970b0e;
    color: white;
}

.alert-success {
    background-color: #5da423;
    border-color: #457a1a;
    color: white;
}

.text-error {
    color: #c60f13;
}

.alert ul {
    margin: 15px 0px 15px 0px;
}

#todoList li {
    margin-top: 5px;
}
' >> $i ;done