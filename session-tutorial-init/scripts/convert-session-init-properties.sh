#!/bin/bash
# Convert message resource(s) on blank project.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n $TARGET_DIR; then
  pushd "$TARGET_DIR"
fi

# application-messages.properties
for i in ` find ./ -type f -name 'application-messages.properties' `; do echo -e '

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

#title.SessionTutorial
title.login.loginForm=Login Page
title.account.createForm=Account Create Page
title.account.createConfirm=Account Create Page
title.account.createFinish=Account Create Page
title.account.updateForm1=Account Update Page
title.account.updateForm2=Account Update Page
title.account.updateConfirm=Account Update Page
title.account.updateFinish=Account Update Page
title.goods.showGoods=Item List Page
title.goods.showGoodsDetail=Item List Page
title.cart.viewCart=View Cart Page
title.order.confirm=Order Page
title.order.finish=Order Page
' >>$i ; done

# ValidationMessages.properties
for i in ` find ./ -type f -name 'ValidationMessages.properties' `; do echo -e '
session.app.validation.Confirm.message= password must be same
' >>$i ; done

if test -n $TARGET_DIR; then
  popd
fi
