#!/bin/bash

###################  init  ###################

# include.jsp
find ./session-tutorial-complete -type f -name 'include.jsp' | xargs sed -i -e 's|<%@ page session="false"|<%@ page session="true"|'
    
###################  tutorial flow  ###################



# showGoods.jsp
find ./session-tutorial-complete -type f -name 'showGoods.jsp' | xargs sed -i -e 's|<th>Price</th>|<th>Price</th>\
                <th>Quantity</th>|'


# showGoods.jsp
find ./session-tutorial-complete -type f -name 'showGoods.jsp' | xargs sed -i -e 's|maxFractionDigits="0" /></td>|maxFractionDigits="0" /></td>\
                    <td><form:form method="post"\
                            action="${pageContext.request.contextPath}/goods/addToCart"\
                            modelAttribute="goodAddForm">\
                            <input type="text" name="quantity" id="quantity${status.index}" value="1" />\
                            <input type="hidden" name="goodsId" value="${f:h(goods.id)}" />\
                            <input type="submit" id="add${status.index}" value="add" />\
                        </form:form></td>|'


# showGoods.jsp
find ./session-tutorial-complete -type f -name 'showGoods.jsp' | xargs sed -i -e 's|</body>|    <div>\
        <%-- (1) --%>\
        <spring:eval var="cart" expression="@cart" />\
        <form method="get" action="${pageContext.request.contextPath}/cart">\
            <input type="submit" id="viewCart" value="view cart" />\
        </form>\
        <table>\
            <%-- (2) --%>\
            <c:forEach items="${cart.cartItems}" var="cartItem" varStatus="status">\
                <tr>\
                    <td><span id="itemName${status.index}">${f:h(cartItem.goods.name)}</span></td>\
                    <td><span id="itemPrice${status.index}"><fmt:formatNumber\
                                value="${cartItem.goods.price}" type="CURRENCY"\
                                currencySymbol="\&yen;" maxFractionDigits="0" /></span></td>\
                    <td><span id="itemQuantity${status.index}">${f:h(cartItem.quantity)}</span></td>\
                </tr>\
            </c:forEach>\
            <tr>\
                <td>Total</td>\
                <td><span id="totalPrice"><fmt:formatNumber\
                            value="${f:h(cart.totalAmount)}" type="CURRENCY"\
                            currencySymbol="\&yen;" maxFractionDigits="0" /></span></td>\
                <td></td>\
            </tr>\
        </table>\
    </div>\
\
</body>|'


# showGoodsDetail.jsp
find ./session-tutorial-complete -type f -name 'showGoodsDetail.jsp' | xargs sed -i -e 's|<form method="get" action="${pageContext.request.contextPath}/goods">|<form:form method="post"\
            action="${pageContext.request.contextPath}/goods/addToCart"\
            modelAttribute="AddToCartForm">\
            Quantity<input type="text" id="quantity" name="quantity"\
                value="1" />\
            <input type="hidden" name="goodsId" value="${f:h(goods.id)}" />\
            <input type="submit" id="add" value="add" />\
        </form:form>\
\
        <form method="get" action="${pageContext.request.contextPath}/goods">|'


# showGoodsDetail.jsp
find ./session-tutorial-complete -type f -name 'showGoodsDetail.jsp' | xargs sed -i -e 's|</body>|    <div>\
        <spring:eval var="cart" expression="@cart" />\
        <form method="get" action="${pageContext.request.contextPath}/cart">\
            <input type="submit" value="view cart" />\
        </form>\
        <table>\
            <c:forEach items="${cart.cartItems}" var="cartItem"\
                varStatus="status">\
                <tr>\
                    <td><span id="itemName${status.index}">${f:h(cartItem.goods.name)}</span></td>\
                    <td><span id="itemPrice${status.index}"><fmt:formatNumber\
                                value="${cartItem.goods.price}" type="CURRENCY"\
                                currencySymbol="\&yen;" maxFractionDigits="0" /></span></td>\
                    <td><span id="itemQuantity${status.index}">${f:h(cartItem.quantity)}</span></td>\
                </tr>\
            </c:forEach>\
            <tr>\
                <td>Total</td>\
                <td><span id="totalPrice"><fmt:formatNumber\
                            value="${f:h(cart.totalAmount)}" type="CURRENCY"\
                            currencySymbol="\&yen;" maxFractionDigits="0" /></span></td>\
                <td></td>\
            </tr>\
        </table>\
    </div>\
</body>|'

