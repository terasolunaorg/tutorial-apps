#!/bin/bash
# Convert jsp resource(s) on initial session tutorial app.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  pushd "${TARGET_DIR}/${ARTIFACT_ID}"
fi

###################  init  ###################

# include.jsp
find ./ -type f -name 'include.jsp' | xargs sed -i -e 's|<%@ page session="false"|<%@ page session="true"|'
    
# template.jsp
find ./ -type f -name 'template.jsp' | xargs sed -i -e 's|href="${pageContext.request.contextPath}/resources/app/css/styles.css">|href="${pageContext.request.contextPath}/resources/app/css/styles.css">\
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap-3.0.0/css/bootstrap.css"\
    media="screen, projection">|'
    
###################  tutorial flow  ###################



# showGoods.jsp
find ./ -type f -name 'showGoods.jsp' | xargs sed -i -e 's|<th>Price</th>|<th>Price</th>\
            <th>Quantity</th>|'


# showGoods.jsp
find ./ -type f -name 'showGoods.jsp' | xargs sed -i -e 's|maxFractionDigits="0" /></td>|maxFractionDigits="0" /></td>\
                <td><form:form method="post"\
                        action="${pageContext.request.contextPath}/goods/addToCart"\
                        modelAttribute="goodAddForm">\
                        <input type="text" name="quantity" id="quantity${status.index}" value="1" />\
                        <input type="hidden" name="goodsId" value="${f:h(goods.id)}" />\
                        <input type="submit" id="add${status.index}" value="add" />\
                    </form:form>\
                </td>|'


# showGoods.jsp
find ./ -type f -name 'showGoods.jsp' | xargs sed -i -e '$ a <div>\
    <%-- (1) --%>\
    <spring:eval var="cart" expression="@cart" />\
    <form method="get" action="${pageContext.request.contextPath}/cart">\
        <input type="submit" id="viewCart" value="view cart" />\
    </form>\
    <table>\
        <%-- (2) --%>\
        <c:forEach items="${cart.cartItems}" var="cartItem" varStatus="status">\
            <tr>\
                <td id="itemName${status.index}">${f:h(cartItem.goods.name)}</td>\
                <td id="itemPrice${status.index}"><fmt:formatNumber value="${cartItem.goods.price}" type="CURRENCY" currencySymbol="&yen;" maxFractionDigits="0" /></td>\
                <td id="itemQuantity${status.index}">${f:h(cartItem.quantity)}</td>\
            </tr>\
        </c:forEach>\
        <tr>\
            <td>Total</td>\
            <td id="totalPrice"><fmt:formatNumber value="${f:h(cart.totalAmount)}" type="CURRENCY" currencySymbol="&yen;" maxFractionDigits="0" /></td>\
            <td></td>\
        </tr>\
    </table>\
</div>'


# showGoodsDetail.jsp
find ./ -type f -name 'showGoodsDetail.jsp' | xargs sed -i -e 's|<form method="get" action="${pageContext.request.contextPath}/goods">|    <form:form method="post"\
        action="${pageContext.request.contextPath}/goods/addToCart"\
        modelAttribute="AddToCartForm">\
        Quantity<input type="text" id="quantity" name="quantity" value="1" />\
        <input type="hidden" name="goodsId" value="${f:h(goods.id)}" />\
        <input type="submit" id="add" value="add" />\
    </form:form>\
\
        <form method="get" action="${pageContext.request.contextPath}/goods">|'


# showGoodsDetail.jsp
find ./ -type f -name 'showGoodsDetail.jsp' | xargs sed -i -e '$ a <div>\
    <spring:eval var="cart" expression="@cart" />\
    <form method="get" action="${pageContext.request.contextPath}/cart">\
        <input type="submit" value="view cart" />\
    </form>\
    <table>\
        <c:forEach items="${cart.cartItems}" var="cartItem" varStatus="status">\
            <tr>\
                <td id="itemName${status.index}">${f:h(cartItem.goods.name)}</td>\
                <td id="itemPrice${status.index}"><fmt:formatNumber value="${cartItem.goods.price}" type="CURRENCY" currencySymbol="&yen;" maxFractionDigits="0" /></td>\
                <td id="itemQuantity${status.index}">${f:h(cartItem.quantity)}</td>\
            </tr>\
        </c:forEach>\
        <tr>\
            <td>Total</td>\
            <td id="totalPrice"><fmt:formatNumber value="${f:h(cart.totalAmount)}" type="CURRENCY" currencySymbol="&yen;" maxFractionDigits="0" /></td>\
            <td></td>\
        </tr>\
    </table>\
</div>'

if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  popd
fi
