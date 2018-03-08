#!/bin/bash
# Convert java source(s) on todo app.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n $TARGET_DIR; then
  pushd "${TARGET_DIR}/${ARTIFACT_ID}"
fi

######### TodoService.java #########

# TodoService.java
find ./ -type f -name 'TodoService.java' | xargs sed -i -e 's|Collection<Todo> findAll();|Collection<Todo> findAll();\
\
    Todo findOne(String todoId);|'


######### TodoServiceImpl.java #########

# TodoServiceImpl.java
find ./ -type f -name 'TodoServiceImpl.java' | xargs sed -i -e 's|private Todo findOne(String todoId) {|@Override\
    @Transactional(readOnly = true)\
    public Todo findOne(String todoId) {|'

# TodoServiceImpl.java
find ./ -type f -name 'TodoServiceImpl.java' | xargs sed -i -e '/messages.add(ResultMessage/,/+ todoId + ")"));\|+ MAX_UNFINISHED_COUNT + "."));/d'

# TodoServiceImpl.java
find ./ -type f -name 'TodoServiceImpl.java' | xargs sed -i -e '/public Todo findOne(String todoId) {/,/public Collection<Todo> findAll() {/s/ResultMessages messages = ResultMessages.error();/ResultMessages messages = ResultMessages.error();\
            messages.add("E404", todoId);/'

# TodoServiceImpl.java
find ./ -type f -name 'TodoServiceImpl.java' | xargs sed -i -e '/public Todo create(Todo todo) {/,/public Todo finish(String todoId) {/s/ResultMessages messages = ResultMessages.error();/ResultMessages messages = ResultMessages.error();\
            messages.add("E001", MAX_UNFINISHED_COUNT);/'

# TodoServiceImpl.java
find ./ -type f -name 'TodoServiceImpl.java' | xargs sed -i -e '/public Todo finish(String todoId) {/,/public void delete(String todoId) {/s/ResultMessages messages = ResultMessages.error();/ResultMessages messages = ResultMessages.error();\
            messages.add("E002", todoId);/'


if test -n $TARGET_DIR; then
  popd
fi
