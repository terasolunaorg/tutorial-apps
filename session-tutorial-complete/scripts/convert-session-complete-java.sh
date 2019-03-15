#!/bin/bash
# Convert java source(s) on initial session tutorial app.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  pushd "${TARGET_DIR}/${ARTIFACT_ID}"
fi

######### Cart.java #########

# Cart.java
find ./ -type f -name 'Cart.java' | xargs sed -i -e 's|import org.springframework.util.SerializationUtils;|import org.springframework.context.annotation.Scope;\
import org.springframework.context.annotation.ScopedProxyMode;\
import org.springframework.stereotype.Component;\
import org.springframework.util.SerializationUtils;|'

# Cart.java
find ./ -type f -name 'Cart.java' | xargs sed -i -e 's|public class Cart implements Serializable {|@Component // (1)\
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS) // (2)\
public class Cart implements Serializable {|'


######### GoodsController.java #########

# GoodsController.java
find ./ -type f -name 'GoodsController.java' | xargs sed -i -e 's|import org.springframework.ui.Model;|import org.springframework.ui.Model;\
import org.springframework.validation.BindingResult;\
import org.springframework.validation.annotation.Validated;|'



# GoodsController.java
find ./ -type f -name 'GoodsController.java' | xargs sed -i -e 's|import org.springframework.web.bind.annotation.PathVariable;|import org.springframework.web.bind.annotation.PathVariable;\
import org.springframework.web.bind.annotation.PostMapping;|'



# GoodsController.java
find ./ -type f -name 'GoodsController.java' | xargs sed -i -e 's|import org.springframework.web.bind.annotation.RequestMapping;|import org.springframework.web.bind.annotation.RequestMapping;\
import org.springframework.web.servlet.mvc.support.RedirectAttributes;\
import org.terasoluna.gfw.common.message.ResultMessages;|'



# GoodsController.java
find ./ -type f -name 'GoodsController.java' | xargs sed -i -e 's|import com.example.session.domain.model.Goods;|import com.example.session.domain.model.Cart;\
import com.example.session.domain.model.CartItem;\
import com.example.session.domain.model.Goods;|'



# GoodsController.java
find ./ -type f -name 'GoodsController.java' | xargs sed -i -e 's|GoodsService goodsService;|GoodsService goodsService;\
\
    // (1)\
    @Inject\
    Cart cart;|'



# GoodsController.java
find ./ -type f -name 'GoodsController.java' | xargs sed -i -e 's|return "goods/showGoodsDetail";|return "goods/showGoodsDetail";\
    }\
\
    @PostMapping("/addToCart")\
    public String addToCart(@Validated GoodAddForm form, BindingResult result,\
            RedirectAttributes attributes) {\
\
        if (result.hasErrors()) {\
            ResultMessages messages = ResultMessages.error()\
                    .add("e.st.go.5001");\
            attributes.addFlashAttribute(messages);\
            return "redirect:/goods";\
        }\
\
        Goods goods = goodsService.findOne(form.getGoodsId());\
        CartItem cartItem = new CartItem();\
        cartItem.setGoods(goods);\
        cartItem.setQuantity(form.getQuantity());\
        cart.add(cartItem); // (2)\
\
        return "redirect:/goods";|'




# GoodsController.java
find ./ -type f -name 'GoodsController.java' | xargs sed -i -e 's|import org.springframework.data.domain.Page;|import org.springframework.data.domain.Page;\
import org.springframework.data.domain.PageRequest;|'



# GoodsController.java
find ./ -type f -name 'GoodsController.java' | xargs sed -i -e 's|// (1)||'



# GoodsController.java
find ./ -type f -name 'GoodsController.java' | xargs sed -i -e 's|Cart cart;|Cart cart;\
\
    // (1)\
    @Inject\
    GoodsSearchCriteria criteria;|'



# GoodsController.java
find ./ -type f -name 'GoodsController.java' | xargs sed -i -e 's|@GetMapping$|// (2)\
    @GetMapping|'



# GoodsController.java
find ./ -type f -name 'GoodsController.java' | xargs sed -i -e 's|String showGoods(GoodViewForm form, Pageable pageable, Model model) {|String showGoods(GoodViewForm form, Model model) {\
        Pageable pageable = PageRequest.of(criteria.getPage(), 3);\
        form.setCategoryId(criteria.getCategoryId());\
        return showGoods(pageable, model);\
    }\
\
    // (3)\
    @GetMapping(params = "categoryId")\
    String changeCategoryId(GoodViewForm form, Pageable pageable, Model model) {\
        criteria.setPage(pageable.getPageNumber());\
        criteria.setCategoryId(form.getCategoryId());\
        return showGoods(pageable, model);\
    }\
\
    // (4)\
    @GetMapping(params = "page")\
    String changePage(GoodViewForm form, Pageable pageable, Model model) {\
        criteria.setPage(pageable.getPageNumber());\
        form.setCategoryId(criteria.getCategoryId());\
        return showGoods(pageable, model);\
    }\
\
    // (5)\
    String showGoods(Pageable pageable, Model model) {|'



# GoodsController.java
find ./ -type f -name 'GoodsController.java' | xargs sed -i -e 's|Page<Goods> page = goodsService.findByCategoryId(form.getCategoryId(),|Page<Goods> page = goodsService.findByCategoryId(|'



# GoodsController.java
find ./ -type f -name 'GoodsController.java' | xargs sed -i -e 's|pageable);|criteria.getCategoryId(), pageable);|'
# GoodsController.java
find ./ -type f -name 'GoodsController.java' | xargs sed -i -e 's|cart.add(cartItem); // (2)|cart.add(cartItem);|'

if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  popd
fi
