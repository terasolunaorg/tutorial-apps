package com.example.session.app.goods;
 
import javax.inject.Inject;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessages;
 
import com.example.session.domain.model.Cart;
import com.example.session.domain.model.CartItem;
import com.example.session.domain.model.Goods;
import com.example.session.domain.service.goods.GoodsService;
 
@Controller
@RequestMapping("goods")
public class GoodsController {
 
	@Inject
	GoodsService goodsService;

    
    @Inject
    Cart cart;

    // (1)
    @Inject
    GoodsSearchCriteria criteria;
 
	@ModelAttribute(value = "goodViewForm")
	public GoodViewForm setUpCategoryId() {
		return new GoodViewForm();
	}
 
	// (2)
    @RequestMapping(value = "", method = RequestMethod.GET)
	String showGoods(GoodViewForm form, Model model) {
        Pageable pageable = new PageRequest(criteria.getPage(), 3);
        form.setCategoryId(criteria.getCategoryId());
        return showGoods(pageable, model);
    }

    // (3)
    @RequestMapping(value = "", method = RequestMethod.GET, params = "categoryId")
    String changeCategoryId(GoodViewForm form, Pageable pageable, Model model) {
        criteria.setPage(pageable.getPageNumber());
        criteria.setCategoryId(form.getCategoryId());
        return showGoods(pageable, model);
    }

    // (4)
    @RequestMapping(value = "", method = RequestMethod.GET, params = "page")
    String changePage(GoodViewForm form, Pageable pageable, Model model) {
        criteria.setPage(pageable.getPageNumber());
        form.setCategoryId(criteria.getCategoryId());
        return showGoods(pageable, model);
    }

    // (5)
    String showGoods(Pageable pageable, Model model) {
 
		Page<Goods> page = goodsService.findByCategoryId(
				criteria.getCategoryId(), pageable);
		model.addAttribute("page", page);
		return "goods/showGoods";
	}
 
	@RequestMapping(value = "/{goodsId}", method = RequestMethod.GET)
	public String showGoodsDetail(@PathVariable String goodsId, Model model) {
 
		Goods goods = goodsService.findOne(goodsId);
		model.addAttribute(goods);
 
		return "/goods/showGoodsDetail";
    }

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public String addToCart(@Validated GoodAddForm form, BindingResult result,
            RedirectAttributes attributes) {

        if (result.hasErrors()) {
            ResultMessages messages = ResultMessages.error()
                    .add("e.st.go.5001");
            attributes.addFlashAttribute(messages);
            return "redirect:/goods";
        }

        Goods goods = goodsService.findOne(form.getGoodsId());
        CartItem cartItem = new CartItem();
        cartItem.setGoods(goods);
        cartItem.setQuantity(form.getQuantity());
        cart.add(cartItem);

        return "redirect:/goods";
	}
}