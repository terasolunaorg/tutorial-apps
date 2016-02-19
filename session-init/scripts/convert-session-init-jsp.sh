#!/bin/bash

# include.jsp
find ./session-tutorial-init -type f -name 'include.jsp' | xargs sed -i -e 's|<%@ page session="false"|<%@ page session="true"|'
