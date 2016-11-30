#!/bin/bash


# application-messages.properties
for i in ` find ./target-project/session-tutorial-complete -type f -name 'application-messages.properties' `; do echo -e '

#error.SessionTutorial.order
e.st.od.5001=Your cart is empty
e.st.od.8001=Your cart is empty
e.st.od.8002=Status of your cart has changed

#error.SessionTutorial.goods
e.st.go.5001=Quantity must be 1 or over

#error.SessionTutorial.cart
e.st.ca.5001=Remove check boxes must be selected at least 1

#error.SessionTutorial.account
e.st.ac.5001=Your operation is illegal
' >>$i ; done

# ValidationMessages.properties
for i in ` find ./target-project/session-tutorial-complete -type f -name 'ValidationMessages.properties' `; do echo -e '
session.app.validation.Confirm.message= password must be same
' >>$i ; done