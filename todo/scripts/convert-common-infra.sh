#!/bin/bash

# infra.propertiesのDB設定を書き換える
find . -type f -name '*infra.properties' | xargs sed -i 's/DB_CLOSE_DELAY=-1*/DB_CLOSE_DELAY=-1;INIT=create table if not exists todo(todo_id varchar(36) primary key, todo_title varchar(30), finished boolean, created_at timestamp)/g'
