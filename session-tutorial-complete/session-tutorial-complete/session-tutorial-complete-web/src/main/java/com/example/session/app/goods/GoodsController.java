/*
 * Copyright(c) 2013 NTT DATA Corporation. Copyright(c) 2013 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.example.session.app.goods;
 
import javax.inject.Inject;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @GetMapping
	String showGoods(GoodViewForm form, Model model) {
        Pageable pageable = PageRequest.of(criteria.getPage(), 3);
        form.setCategoryId(criteria.getCategoryId());
        return showGoods(pageable, model);
    }

    // (3)
    @GetMapping(params = "categoryId")
    String changeCategoryId(GoodViewForm form, Pageable pageable, Model model) {
        criteria.setPage(pageable.getPageNumber());
        criteria.setCategoryId(form.getCategoryId());
        return showGoods(pageable, model);
    }

    // (4)
    @GetMapping(params = "page")
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
 
	@GetMapping("/{goodsId}")
	public String showGoodsDetail(@PathVariable String goodsId, Model model) {
 
		Goods goods = goodsService.findOne(goodsId);
		model.addAttribute(goods);
 
		return "goods/showGoodsDetail";
    }

    @PostMapping("/addToCart")
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